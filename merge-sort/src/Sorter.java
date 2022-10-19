import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class Sorter extends RecursiveTask<int[]> {

  private int[] array;

  public Sorter(int[] array) {
    this.array = array;
  }

  @Override
  protected int[] compute() {
    if (array.length == 1) {
      return array;
    } else {
      int middle = array.length / 2;

      int[] leftArray = Arrays.copyOfRange(array, 0, middle);
      int[] rightArray = Arrays.copyOfRange(array, middle, array.length);

      Sorter left = new Sorter(leftArray);
      Sorter right = new Sorter(rightArray);

      left.fork();

//      System.out.println(Thread.currentThread().getName());
      return merge(right.compute(), left.join());
    }
  }

  private int[] merge(int[] leftArray, int[] rightArray) {
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

  public int[] getArray() {
    return array;
  }

  public void setArray(int[] array) {
    this.array = array;
  }
}
