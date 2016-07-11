package datasource.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;


@Configuration
@Profile("PROD")
public class SqlServerDataSourceConfig {

	
	@Value("${DATABASE_URL}")
	private String url;
	@Value("${DATABASE_NAME}")
	private String databaseName;
	@Value("${DATABASE_DRIVER}")
	private String driverClassName;
	@Value("${DATABASE_USER}")
	private String databaseUser;
	@Value("${DATABASE_PASSWORD}")
	private String databasePassword;
	@Value("${SQL_SERVER_SCHEMA}")
	private String sqlServerSchema;
	
	@Bean
	  public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(driverClassName);
	    dataSource.setUrl(url);
	    dataSource.setUsername(databaseUser);
	    dataSource.setPassword(databasePassword);
	    return dataSource;
	  }
	
	@Bean
	public ResourceDatabasePopulator databasePopulator(){
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource(sqlServerSchema));
		return populator;
	}
	
	@Bean
	public DataSourceInitializer dataSourceInitializer(){
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDatabasePopulator(databasePopulator());
		dataSourceInitializer.setDataSource(dataSource());
		return dataSourceInitializer;
	}
}
