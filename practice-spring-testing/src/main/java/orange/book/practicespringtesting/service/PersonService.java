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

  private List<Person> persons =
      List.of(new Person("Yurs", LocalDate.of(2023,2, 15)),
          new Person("Mama", LocalDate.of(2022,4, 18)),
          new Person("Tato", LocalDate.of(2022,4, 18)));


  PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person getPerson(String name) {

    persons.forEach((Person person) -> {
        if(Optional.ofNullable(personRepository.findByName(person.getName())).isEmpty()) {
            personRepository.save (new Person(person.getName(),person.getBirthday()));
        }
    });

    Person person = Optional.ofNullable(personRepository.findByName(name)).orElse(new Person("No Person", LocalDate.now()));
    return person;
  }

  public List<Person> getAllPersons() {
    persons.forEach((Person person) -> {
      if(Optional.ofNullable(personRepository.findByName(person.getName())).isEmpty()) {
        personRepository.save (new Person(person.getName(),person.getBirthday()));
      }
    });
    return personRepository.findAll();
  }
}
