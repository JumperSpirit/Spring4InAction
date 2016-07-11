package readers;

import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import entities.UserEntity;

public class UserEncrypterItemReader extends JpaPagingItemReader<UserEntity> {

	
	public UserEncrypterItemReader(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		this.setEntityManagerFactory(entityManagerFactoryBean.nativeEntityManagerFactory);
		this.setQueryString("FROM UserEntity");
	}
	
	
	
}
