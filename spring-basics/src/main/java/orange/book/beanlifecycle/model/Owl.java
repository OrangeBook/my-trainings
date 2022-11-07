package orange.book.beanlifecycle.model;

import org.springframework.stereotype.Component;

@Component
public class Owl implements Animal {

  @Override
  public void makeSound() {
    System.out.println("u-u");
  }
}
