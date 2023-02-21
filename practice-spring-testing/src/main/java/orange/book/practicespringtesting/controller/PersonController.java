package orange.book.practicespringtesting.controller;

import java.time.LocalDate;
import java.util.List;
import orange.book.practicespringtesting.model.Person;
import orange.book.practicespringtesting.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {

  PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @ResponseBody
  @GetMapping("/persons")
  public List<Person> getPersons() {
    return personService.getAllPersons();
  }

  @GetMapping("/")
  public String index(Model model) {
    List<Person> persons = personService.getAllPersons();
    model.addAttribute("persons", persons);
    return "myPage";
  }

  @GetMapping("/isYourBirthday")
  public String isYourBirthday(@RequestParam(required = false) String name, Model model) {
    String message = "Today is not your day";
    if(name != null) {
      if (personService.isTodayPersonsBirthday(name)) {
        message = "Congratulations, Happy birthday";
      }
      model.addAttribute("message", message);
    }

    List<Person> persons = personService.getAllPersons();
    model.addAttribute("persons", persons);
    return "myPage";
  }

  @GetMapping("/test")
  public String test() {
    return "test";
  }

}
