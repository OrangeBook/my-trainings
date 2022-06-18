import lombok.Data;

public class SimpleJacksonTest {

  public static void main(String[] args) {

    var initialJson = """
        {
          "name":"bob",
          "id":"34",
          "email":"bob@gmail.com"
        }
        """;


  }

  private static <T> T convertToObject(String initialJson, Class<T> clazz) {
   return null;
  }

  @Data
  static class Bob {
    private String name;
    private String id;
    private String email;
  }

}
