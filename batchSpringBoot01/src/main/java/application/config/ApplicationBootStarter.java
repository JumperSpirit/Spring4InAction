package application.config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"datasource.configuration","job.configuration"})
@Import(AuterConfig.class)
public class ApplicationBootStarter {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationBootStarter.class, args);
    }
}
