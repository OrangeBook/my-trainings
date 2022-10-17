import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

  public static void main(String[] args) {
    int length = 10_000_000;
    int[] array = generateArray(length);
    long start = System.nanoTime();
    int[] sortedArray = sort(array);
    long finish = System.nanoTime();
//    for(int i = 0; i < sortedArray.length; i++) {
//      System.out.print(sortedArray[i] + " ");
//    }
    System.out.println("Array length: " + length);
    System.out.println("time spent: " + (finish - start)/ 1_000_000_000f + " seconds");
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
    return null;
  }
}
