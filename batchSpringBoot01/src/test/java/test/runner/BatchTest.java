package test.runner;

import job.configuration.JobsInfrastructureConfiguration;
import job.configuration.UserJobConfig;
import job.configuration.UserStepConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import datasource.configuration.DatabaseConfig;

@ContextConfiguration(classes = { UserJobConfig.class,JobsInfrastructureConfiguration.class,UserStepConfig.class , DatabaseConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BatchTest {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Test
	public void startBatch() throws Exception {
		jobLauncher.run(job, new JobParameters());
	}

}