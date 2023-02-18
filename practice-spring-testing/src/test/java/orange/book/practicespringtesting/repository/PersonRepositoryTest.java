package orange.book.practicespringtesting.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import orange.book.practicespringtesting.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)

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
    entityManager.persist(testPerson);

    Person foundPerson = personRepository.findByName("test");

    assertEquals("test", foundPerson.getName());
  }


}
