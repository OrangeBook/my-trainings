package orange.book.profiletest.model;

import org.springframework.stereotype.Component;

@Component
public class Person {

  private Thing thing;

  public Person(Thing thing) {
    this.thing = thing;
  }

  public void performAction() {
    thing.doAction();
  }


}
