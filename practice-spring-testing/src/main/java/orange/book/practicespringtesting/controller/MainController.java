package orange.book.practicespringtesting.controller;

import orange.book.practicespringtesting.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @GetMapping("/persons")
  public Person getPersons() {

    Person person = new Person("Kycios");
    return person;
  }
}
