package orange.book.beanlifecycle.model;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Owl implements Animal {

  @PostConstruct
  public void init() {
    System.out.println("I am an owl");
  }

  @Override
  public void makeSound() {
    System.out.println("u-u");
  }
}
