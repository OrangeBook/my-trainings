package orange.book.practicespringtesting.repository;

import java.util.List;
import orange.book.practicespringtesting.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
  Person findByName(String name);
  Person save(Person person);
  List<Person> findAll();
}
