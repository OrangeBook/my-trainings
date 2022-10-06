import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MemoryLeak {


//  public  List<Double> list = new ArrayList<>();
//
//  public void fulfillTheArray() {
//
//    for( int i = 0; i < 1_000_000_00; i++) {
//      list.add((double) i);
//    }
//    System.out.println("End of the fulfilling");
//  }
//
//  public static void main(String[] args) {
//    new Scanner(System.in).nextInt();
//    System.out.println("Starting the program");
//    new MemoryLeak().fulfillTheArray();
//    System.out.println("Program is finished");
//    new Scanner(System.in).nextInt();
//
//  }

  public List<Double> list = new ArrayList<>();

  public void populateList() {
    for (int i = 0; i < 10000000; i++) {
      list.add(Math.random());
    }
    System.out.println("Debug Point 2");
  }

  public static void main(String[] args) {
    System.out.println("Debug Point 1");
    new Scanner(System.in).nextInt();
    new MemoryLeak().populateList();
    System.out.println("Debug Point 3");
    new Scanner(System.in).nextInt();

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MemoryLeak that = (MemoryLeak) o;
    return Objects.equals(list, that.list);
  }

  @Override
  public int hashCode() {
    return Objects.hash(list);
  }
}
