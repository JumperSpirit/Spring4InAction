package job.configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import processors.UserEncrypterItemProcessor;
import readers.UserEncrypterItemReader;
import writers.UserEncrypterItemWriter;
import entities.UserEntity;

@Configuration
public class UserEncryptStepConfig {

	
	@Value("${STEP_USER_ENCRYPTE_NAME}")
	private String stepName;
	
	@Value("${USER_FILE_NAME}")
	private String userfileName;
	
	@Value("${SQL_INSERT_USER}")
	private String SQLinsertUser;
	
	@Value("${SQL_UPDATE_USER_PASS}")
	private String SQLUpdateUser;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;


	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;
	
	@Resource
	public DataSource dataSource;

	@Bean
	public UserEncrypterItemReader userEncrypterReaderBean() {
		return new UserEncrypterItemReader(entityManagerFactoryBean);
	}

	@Bean
	public UserEncrypterItemProcessor  userEncrypterProcessorBean() {
		return new UserEncrypterItemProcessor();
	}

	@Bean
	public UserEncrypterItemWriter userEncrypterWriterBean(){
		return new UserEncrypterItemWriter(SQLUpdateUser);
	}


	@Bean
	public Step userEncrypterStep() {
		return stepBuilderFactory.get(stepName).<UserEntity, UserEntity> chunk(10)
				.reader(userEncrypterReaderBean()).processor(userEncrypterProcessorBean())
				.writer(userEncrypterWriterBean()).build();
	}

}
