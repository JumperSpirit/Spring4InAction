package writers;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import entities.UserEntity;

public class UserEncrypterItemWriter extends JdbcBatchItemWriter<UserEntity>{

	@Resource
	public DataSource dataSource;
	


	@PostConstruct
	public void Init(){
		this.setDataSource(dataSource);
	}
	public UserEncrypterItemWriter(String SQLUpdatetUser) {
		super();
		this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<UserEntity>());
		this.setSql(SQLUpdatetUser);
	}

	
}
