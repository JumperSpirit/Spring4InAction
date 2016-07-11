package job.configuration;

import listerners.JobCompletionNotificationListener;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserJobConfig {

	@Value("${JOB_USER_IMPORT_NAME}")
	private String jobName;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public Step userStep;

	@Autowired
	public Step userEncrypterStep;

	
    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionNotificationListener();
    }
	
	@Bean
	public Job importPersonJob() {
		return jobBuilderFactory.get(jobName).listener(listener())
				.incrementer(new RunIdIncrementer()).start(userStep).on("COMPLETED").to(userEncrypterStep).end()
				.build();
	}

}
