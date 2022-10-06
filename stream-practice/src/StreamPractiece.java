import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamPractiece {

  private static class Employee {
    protected String name;
    protected Integer age;
    protected Float salary;
    protected String jobTitle;

    public Employee(String name, Integer age, Float salary, String jobTitle) {
      this.name = name;
      this.age = age;
      this.salary = salary;
      this.jobTitle = jobTitle;
    }
  }
  public static void main(String[] args) {
    List<Employee> employees = List.of(
        new Employee("Bob", 34, 40_000f, "developer"),
        new Employee("Bobi", 34, 60_000f, "writer"),
        new Employee("Bobu", 34, 70_000f, "writer"),
        new Employee("Bobo", 34, 60_000f, "developer"),
        new Employee("Boba", 34, 40_000f, "sales executor"),
        new Employee("Bobe", 34, 50_000f, "developer"),
        new Employee("Bobub", 34, 60_000f, "sales executor")
    );

    Map<String, Float> map = employees.stream()
        .collect(Collectors.groupingBy(employee -> employee.jobTitle))
        .entrySet().stream()
        .collect(Collectors.toMap((Map.Entry entry) -> (String) entry.getKey(), (Map.Entry entry) -> {

          Float sum = ((List<Employee>) entry.getValue()).stream()
              .map( employee -> employee.salary)
              .reduce(0f, (acc, x) -> acc + x);
          return sum / ((List<Map.Entry>)entry.getValue()).stream().count();
        }));
    System.out.println(map);

  }

}
