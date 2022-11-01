package orange.book.model;

import javax.annotation.PostConstruct;

public class Owner {

  private String name;

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
}
