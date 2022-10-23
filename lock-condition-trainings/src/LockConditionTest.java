import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.w3c.dom.ls.LSOutput;

public class LockConditionTest {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService pool = Executors.newFixedThreadPool(3);

    pool.submit(new MultiThreadCounter(0));
    pool.submit(new MultiThreadCounter(1));
    pool.submit(new MultiThreadCounter(2));

  }

  private static class MultiThreadCounter implements Runnable{
    private static int counter = 10;
    private static int numberOfThreads = 3;
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    private int threadOrder;

    public MultiThreadCounter(int threadOrder) {
      this.threadOrder = threadOrder;
    }
    @Override
    public void run() {
      while(counter > 0) {
        lock.lock();
        while(counter % numberOfThreads != threadOrder) {
          try {
            System.out.println(Thread.currentThread().getName() + " is waiting");
            condition.await();
          } catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
          }
        }
        counter--;
        System.out.println(Thread.currentThread().getName() + " decreased the counter");
        System.out.println("Counter = " + counter);
        condition.signalAll();
        lock.unlock();
      }
      System.out.println("Done, counter = " + counter);
    }
  }
}
