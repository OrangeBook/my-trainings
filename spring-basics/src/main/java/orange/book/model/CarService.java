package orange.book.model;

public class CarService {

  private Owner owner;

  public CarService(Owner owner) {
    this.owner = owner;
  }

  public CarService() {
  }

  public void sayNameOfTheOwner() {
    System.out.println(owner.getName());
  }

}
