package orange.book.practicespringtesting.controller;

import java.util.List;
import orange.book.practicespringtesting.model.Person;
import orange.book.practicespringtesting.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {

  PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping("/persons")
  public String getPersons(@RequestParam String name, Model model) {
    List<Person> persons = personService.getAllPersons();
    model.addAttribute("persons", persons);
    return "index";
  }
}
