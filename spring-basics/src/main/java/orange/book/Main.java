package orange.book;

import orange.book.config.Config;
import orange.book.model.CarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    System.out.println("I am alive");

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
    CarService carService = (CarService) applicationContext.getBean("carService");
    carService.sayNameOfTheOwner();
  }

}
