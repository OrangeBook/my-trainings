import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

  public static void main(String[] args) {
    int length = 100_000_000;
    int[] array = generateArray(length);
    long start = System.nanoTime();
    int[] sortedArray = sort(array);
    long finish = System.nanoTime();
    System.out.println("Array length: " + length);
    float sequantialTime = (finish - start)/ 1_000_000_000f;
    System.out.println("time spent for sequential calculation: " + sequantialTime + " seconds");

    long start1 = System.nanoTime();
    int[] parallelSortedArray = parallelSort(array);
    long finish1 = System.nanoTime();

    float parallelTime = (finish1 - start1)/ 1_000_000_000f;
    System.out.println("time spent for parallel calculation: " + parallelTime + " seconds");

    boolean isEqual = Arrays.equals(sortedArray, parallelSortedArray);

    System.out.println("Arrays are equal: " + isEqual);

    System.out.println("Speed Up: " + sequantialTime / parallelTime);

    System.out.println("Efficiency: " + sequantialTime / parallelTime / Runtime.getRuntime().availableProcessors() * 100 * 2 + " %");

  }

  private static int[] generateArray(int length) {
    Random random = new Random(147);
    int array[] = new int[length];
    for (int i = 0; i < length; i++) {
      array[i] = random.nextInt(100);
    }
    return array;
  }

  private static int[] sort(int[] array) {
    if (array.length == 1) {
      return array;
    }

    int middle = array.length / 2;

    int[] leftArray = sort(Arrays.copyOfRange(array, 0, middle));
    int[] rightArray = sort(Arrays.copyOfRange(array, middle, array.length));

    return merge(leftArray, rightArray);
  }

  private static int[] merge(int[] leftArray, int[] rightArray) {
    int[] sortedArray = new int[leftArray.length + rightArray.length];
    int i = 0;
    int j = 0;
    while(true) {
      if (leftArray[i] < rightArray[j]) {
        sortedArray[i + j] = leftArray[i];
        i++;
        if(i == leftArray.length) {
          for(int m = j; m < rightArray.length; m++) {
            sortedArray[i + m] = rightArray[m];
          }
          break;
        }
      } else {
        sortedArray[i +j] = rightArray[j];
        j++;
        if(j == rightArray.length) {
          for(int n = i; n < leftArray.length; n++) {
            sortedArray[n + j] = leftArray[n];
          }
          break;
        }
      }
    }

    return sortedArray;
  }

  public static int[] parallelSort(int[] array) {
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    Sorter sorter = new Sorter(array);

    return forkJoinPool.invoke(sorter);
  }
}
