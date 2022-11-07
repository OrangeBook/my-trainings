package orange.book.profiletest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "orange/book/profiletest")
@PropertySource("classpath:application.properties")
public class Config1 {

}
