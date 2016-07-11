package listerners;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import beans.User;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private JdbcTemplate jdbcTemplate;

	@Value("${eMail.mailTo}")
	private String mailTo;
	
	@Value("${eMail.mailFrom}")
	private String mailFrom;
	
	@Value("${SQL_SELECT_USER}")
	private String SQLSelectUSer;
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Resource
	public DataSource dataSource;
	
	@PostConstruct
	public void Init(){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			List<User> results = jdbcTemplate.query(SQLSelectUSer, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int row) throws SQLException {
					return new User(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4));
				}
			});

			for (User user : results) {
				log.info("Found <" + user + "> in the database.");
			}
			
			sendMail(results);

		}
	}


	private void sendMail(List<User> results) {
		MimeMessage mimemessage = mailSender.createMimeMessage();
		MimeMessageHelper message;
		try {
			message = new MimeMessageHelper(mimemessage, true);
			message.setTo(mailTo);
			message.setFrom(mailFrom);
			Multipart multipart = new MimeMultipart("mixed");
			MimeBodyPart multipartText = new MimeBodyPart();
			multipartText.setHeader("Content-Type", "text/html");
			multipartText.setContent("Found <" + results.size() + "> in the database.", "text/html");
			multipart.addBodyPart(multipartText);
			message.setSubject("And of interging users job");
			mimemessage.setContent(multipart);
			mailSender.send(mimemessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.error( e.getMessage() );
		}

		
	}
}