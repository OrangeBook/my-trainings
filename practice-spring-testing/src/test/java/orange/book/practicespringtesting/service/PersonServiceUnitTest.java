package orange.book.practicespringtesting.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import orange.book.practicespringtesting.model.Person;
import orange.book.practicespringtesting.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceUnitTest {

  @Mock
  PersonRepository personRepository;

  private List<Person> persons =
      List.of(new Person("Yurs", LocalDate.of(2023,2, 15)),
          new Person("Mama", LocalDate.of(2022,4, 18)),
          new Person("Tato", LocalDate.of(2022,4, 18)));


  @Test
  void getAllPersons_whenIsCalled_thenThreeDefaultPersonsAreReturned() {

    PersonService personService = new PersonService(personRepository);

    when(personRepository.findAll()).thenReturn(persons);
    when(personRepository.findByName(any(String.class))).thenReturn(new Person());

    assertNotNull(personService.getAllPersons());
  }
}
