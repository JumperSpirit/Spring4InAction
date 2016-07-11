package readers;

import mappers.UserMapper;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.ClassPathResource;

import beans.User;

public class UserItemReader extends FlatFileItemReader<User>{


	
	public UserItemReader(String userfileName){
		 setResource(new ClassPathResource(userfileName));
		 setLineMapper(new UserMapper());
	}
}
