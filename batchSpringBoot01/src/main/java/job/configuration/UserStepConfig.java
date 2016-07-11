package job.configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import processors.UserItemProcessor;
import readers.UserItemReader;
import writers.UserItemWriter;
import beans.User;

@Configuration
public class UserStepConfig {

	
	@Value("${STEP_USER_IMPORT_NAME}")
	private String stepName;
	
	@Value("${USER_FILE_NAME}")
	private String userfileName;
	
	@Value("${SQL_INSERT_USER}")
	private String SQLinsertUser;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Resource
	public DataSource dataSource;

	@Bean
	public UserItemReader personReaderBean() {
		return new UserItemReader(userfileName);
	}

	@Bean
	public UserItemProcessor personProcessorBean() {
		return new UserItemProcessor();
	}

	@Bean
	public UserItemWriter personWriterBean(){
		return new UserItemWriter(SQLinsertUser);
	}


	@Bean
	public Step userStep() {
		return stepBuilderFactory.get(stepName).<User, User> chunk(10)
				.reader(personReaderBean()).processor(personProcessorBean())
				.writer(personWriterBean()).build();
	}

}
