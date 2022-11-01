package orange.book.config;

import orange.book.model.CarService;
import orange.book.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Config {

  @Bean
  public Owner owner() {
    return new Owner();
  }

  @Bean
  public CarService carService() {
    return new CarService(owner());
  }



}
