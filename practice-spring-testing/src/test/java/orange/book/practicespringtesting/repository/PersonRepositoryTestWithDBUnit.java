package orange.book.practicespringtesting.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.List;
import orange.book.practicespringtesting.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    TransactionDbUnitTestExecutionListener.class
})
@DataJpaTest
public class PersonRepositoryTestWithDBUnit {

  @Autowired
  private PersonRepository personRepository;

  @Test
  @DatabaseSetup("/testdataTaras.xml")
  void findByName_whenTarasPersonPreviouslySavedInDbUsingDBUnit_thenPersonRepositoryReturnsTarasPerson() {
    Person foundPerson = personRepository.findByName("Taras");

    List<Person> persons = personRepository.findAll();
    persons.forEach(p -> System.out.println(p.getName()));

    assertEquals("Taras", foundPerson.getName());
  }
}
