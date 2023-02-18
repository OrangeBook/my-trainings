package orange.book.practicespringtesting.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import orange.book.practicespringtesting.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class PersonRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private PersonRepository personRepository;

  @Test
  void findByName_whenTestPersonIsPersisted_thenPersonRepositoryReturnsTestPerson() {

    Person testPerson = new Person();
    testPerson.setName("test");

    entityManager.persist(testPerson);

    Person foundPerson = personRepository.findByName("test");

    assertEquals("test", foundPerson.getName());
  }

  @Test
  void findByName_whenBobPersonPreviouslySavedInDb_thenPersonRepositoryReturnsBobPerson() {

  }
}
