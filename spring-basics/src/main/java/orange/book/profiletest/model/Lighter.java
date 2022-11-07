package orange.book.profiletest.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class Lighter implements Thing{
  public void doAction() {
    System.out.println("let it be light");
  }

}
