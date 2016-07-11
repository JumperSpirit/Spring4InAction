package datasource.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import datasource.configuration.H2DataSourceConfig;

	@Configuration
	@EnableAspectJAutoProxy(proxyTargetClass = true)
	@EnableTransactionManagement
	@Import({ H2DataSourceConfig.class})
	public class DatabaseConfig {

		@Value("${PERSISTENCE_UNIT_NAME}")
		private String persistenceUnitName;
		
	    @Bean
	    public JpaTransactionManager transactionManager() {
	        return new JpaTransactionManager();
	    }

	    @Bean
	    public HibernateJpaVendorAdapter jpaVendorAdapter() {
	        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	        adapter.setDatabase(Database.SQL_SERVER);
	        adapter.setShowSql(false);
	        adapter.setGenerateDdl(false);
	        return adapter;
	    }

	    @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
	        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	        emf.setDataSource(dataSource);
	        emf.setJpaVendorAdapter(jpaVendorAdapter);
	        emf.setPersistenceUnitName(persistenceUnitName);
	        emf.setPackagesToScan("entities");
	        return emf;
	    }
}