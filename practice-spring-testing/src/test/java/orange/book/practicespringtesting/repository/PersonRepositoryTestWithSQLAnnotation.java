package orange.book.practicespringtesting.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import orange.book.practicespringtesting.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
public class PersonRepositoryTestWithSQLAnnotation {

  @Autowired
  PersonRepository personRepository;

  @Test
  @Sql("/testdataAndriy.sql")
  void findByName_whenAndriyPersonPreviouslySavedInDbUsingSQLAnnotation_thenPersonRepositoryReturnsAndriyPerson() {
    Person person = personRepository.findByName("Andriy");

    List<Person> persons = personRepository.findAll();
    persons.forEach(p -> System.out.println(p.getName()));

    assertEquals("Andriy", person.getName());
  }

}
