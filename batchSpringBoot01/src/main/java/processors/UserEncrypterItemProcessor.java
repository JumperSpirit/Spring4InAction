package processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import entities.UserEntity;

public class UserEncrypterItemProcessor implements
		ItemProcessor<UserEntity, UserEntity> {

	private static final Logger log = LoggerFactory
			.getLogger(UserEncrypterItemProcessor.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserEntity process(UserEntity item) throws Exception {
		final String firstName = item.getFirstName().toUpperCase();
		final String lastName = item.getLastName().toUpperCase();
		final String login = item.getLogin();
		final String passeWord = passwordEncoder.encode(item.getPasseWord());
		final UserEntity transformed = new UserEntity(firstName, lastName,
				login, passeWord);
		transformed.setId(item.getId());
		log.info("Converting (" + item + ") into (" + transformed + ")");
		return transformed;
	}
}
