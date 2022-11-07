package orange.book.beanlifecycle.model;

import org.springframework.stereotype.Component;

@Component
public class Frog implements Animal {
  @Override
  public void makeSound() {
    System.out.println("rebbit-rebbit");
  }

}
