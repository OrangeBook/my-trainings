package orange.book.lifecycle.practice.model;

import javax.annotation.PostConstruct;
import orange.book.lifecycle.practice.annotation.EnableLogging;
import orange.book.lifecycle.practice.annotation.RandomInt;
import org.springframework.stereotype.Component;

@Component
public class Car {

  @RandomInt("&wheals&")
  int wheals;

  @PostConstruct
  public void engage() {
    System.out.println("I am starting the engine");
  }

  @EnableLogging
  public void go() {

  }
}
