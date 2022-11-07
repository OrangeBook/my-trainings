package orange.book.environmenttest.config;

import orange.book.environmenttest.model.CarService;
import orange.book.environmenttest.model.Owner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

  @Value("${owner.surname}")//1 - from idea env vars, 2 - from real env vars, 3 - from application.properties
  private String surname;

  @Bean
  public Owner owner() {
    return new Owner(surname);
  }

  @Bean
  public CarService carService() {
    return new CarService(owner());
  }



}
