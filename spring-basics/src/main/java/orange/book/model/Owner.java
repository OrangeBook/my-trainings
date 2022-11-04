package orange.book.model;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

public class Owner {


  private String surname;

  private String name;

  public Owner(String surname) {
    this.surname = surname;
  }

  @PostConstruct
  public void setName() {
    this.name = "Bob";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }
}
