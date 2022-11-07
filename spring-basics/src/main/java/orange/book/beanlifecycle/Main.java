package orange.book.beanlifecycle;

import orange.book.beanlifecycle.model.Animal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext("orange.book.beanlifecycle");
    Animal animal = applicationContext.getBean("owl", Animal.class);
    animal.makeSound();

  }

}
