import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;

public class SimpleJacksonTest {

  public static void main(String[] args)
      throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

    var initialJson = """
        {
          "name":"bob",
          "id":"34",
          "email":"bob@gmail.com",
          "country":"UK"
        }
        """;
    Bob bob = convertToObject(initialJson, Bob.class);
    System.out.println(bob);

  }

  private static <T> T convertToObject(String initialJson, Class<T> clazz)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Field[] fields = clazz.getDeclaredFields();
    Map<String, String> fieldsOfObjects = new HashMap<>();

    String regexp = "\".+\":\".+\"" ;
    Pattern pattern = Pattern.compile(regexp);
    Matcher matcher = pattern.matcher(initialJson);
    while (matcher.find()) {
      String[] pair = matcher.group().split(":");
      fieldsOfObjects.put(pair[0].substring(1,pair[0].length() - 1),pair[1].substring(1,pair[1].length() - 1));
    }
    T object = clazz.getConstructor().newInstance();
    Arrays.stream(fields).forEach(field -> {
      try {
        field.set(object, fieldsOfObjects.get(field.getName()));
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    });
   return object;
  }

  @Data
  static class Bob {
    private String name;
    private String id;
    private String email;
    private String country;

    @Override
    public String toString() {
      return "Bob{" +
          "name='" + name + '\'' +
          ", id='" + id + '\'' +
          ", email='" + email + '\'' +
          ", country='" + country + '\'' +
          '}';
    }
  }

}
