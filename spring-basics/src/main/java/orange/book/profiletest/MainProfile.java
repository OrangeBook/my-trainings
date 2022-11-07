package orange.book.profiletest;

import orange.book.profiletest.config.Config1;
import orange.book.profiletest.model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainProfile {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config1.class);



    Person person = applicationContext.getBean(Person.class);
    person.performAction();
  }

}
