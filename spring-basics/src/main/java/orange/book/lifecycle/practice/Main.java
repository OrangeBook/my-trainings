package orange.book.lifecycle.practice;

import orange.book.lifecycle.practice.model.Vehicle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext app = new AnnotationConfigApplicationContext("orange.book.lifecycle.practice");
    Vehicle car = app.getBean(Vehicle.class);
    car.go();
  }

}
