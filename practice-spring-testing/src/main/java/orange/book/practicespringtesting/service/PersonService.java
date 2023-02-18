package orange.book.practicespringtesting.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import orange.book.practicespringtesting.model.Person;
import orange.book.practicespringtesting.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person getPerson(String name) {

    Person person = Optional.ofNullable(personRepository.findByName(name)).orElse(new Person("No Person", LocalDate.now()));
    return person;
  }

  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }
}
