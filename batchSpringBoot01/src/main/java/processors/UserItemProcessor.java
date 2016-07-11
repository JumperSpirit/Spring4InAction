package processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import beans.User;

public class UserItemProcessor implements ItemProcessor<User, User> {

    private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public User process(final User person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();
        final String login = person.getLogin();
        final String passeWord = person.getPasseWord();
        
        
        final User transformedPerson = new User(firstName, lastName,login,passeWord);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        
        
        return transformedPerson;
    }
}
