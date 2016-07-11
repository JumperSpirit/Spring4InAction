package mappers;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import beans.User;

public class UserMapper extends DefaultLineMapper<User> {

	public static final String[] FIELDNAMES = new String[]{"firstName", "lastName","login","passeWord"}; 
	
	public UserMapper(){
		setLineTokenizer(new DelimitedLineTokenizer(){
			{
				setNames(FIELDNAMES);
			}
		});
		setFieldSetMapper(new BeanWrapperFieldSetMapper<User>(){
			{
				setTargetType(User.class);
			}
		});
	}
	
	
}
