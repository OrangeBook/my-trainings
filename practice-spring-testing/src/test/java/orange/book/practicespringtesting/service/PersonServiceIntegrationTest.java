package orange.book.practicespringtesting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import orange.book.practicespringtesting.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class PersonServiceIntegrationTest {

  @Autowired
  PersonService personService;

  @Test
  void getPerson_whenTatoStringIsPassed_thenTatoPersonIsReturned() {
    Person tato = personService.getPerson("Tato");
    assertEquals("Tato", tato.getName());
  }

  @Test
  void getPerson_whenUnknownNameIsPassed_thenPersonWithNameNoPersonReturned() {
    Person unknown = personService.getPerson("Unknown");
    assertEquals("No Person", unknown.getName());

  }
}
