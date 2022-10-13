import java.util.concurrent.CompletableFuture;

public class Test {
  static Object obj = new Object();
  static Integer counter = 0;
  static Runnable thread = () -> {
    for(int i = 0; i < 10_000_000; i++) {
      synchronized (obj) {
        counter++;
      }
    }
  };
  public static void main(String[] args) {
    CompletableFuture<Void> one = CompletableFuture.runAsync(thread);
    CompletableFuture<Void> two = CompletableFuture.runAsync(thread);
    CompletableFuture<Void> three = CompletableFuture.runAsync(thread);
    CompletableFuture<Void> four = CompletableFuture.runAsync(thread);
    CompletableFuture<Void> five = CompletableFuture.runAsync(thread);

    one.join();
    two.join();
    three.join();
    four.join();
    five.join();
    System.out.println(counter);
  }
}
