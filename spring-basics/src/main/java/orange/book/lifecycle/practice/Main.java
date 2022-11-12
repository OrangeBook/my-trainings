package orange.book.lifecycle.practice;

import orange.book.lifecycle.practice.model.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext app = new AnnotationConfigApplicationContext("orange.book.lifecycle.practice");
    Car car = app.getBean(Car.class);
    car.go();
  }

}
