package writers;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import beans.User;

public class UserItemWriter extends JdbcBatchItemWriter<User> {
	
	@Resource
	public DataSource dataSource;
	


	@PostConstruct
	public void Init(){
		this.setDataSource(dataSource);
	}
	
	public UserItemWriter(String SQLinsertUser) {
		super();
		this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
		this.setSql(SQLinsertUser);
	}

		
	
}
