package orange.book.beanlifecycle.model;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Frog implements Animal {

  @PostConstruct
  public void init() {
    System.out.println("I am a Frog");
  }

  @Override
  public void makeSound() {
    System.out.println("rebbit-rebbit");
  }

}
