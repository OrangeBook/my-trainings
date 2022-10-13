package service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import model.Element;
import model.Matrix;

public class MatrixMultiplicator {

  private final int availableThreads = Runtime.getRuntime().availableProcessors();
  private final ExecutorService executorService = Executors.newFixedThreadPool(availableThreads);

  public int[][] multiply(Matrix leftMatrix, Matrix rightMatrix)
      throws InterruptedException, ExecutionException {

    if (leftMatrix.getColumns() != rightMatrix.getRows()) {
      throw new RuntimeException("Wrong dimension of matrices");
    }
    CountDownLatch latch = new CountDownLatch(leftMatrix.getRows() * rightMatrix.getColumns());
    Element[][] resultMatrix = new Element[leftMatrix.getRows()][rightMatrix.getColumns()];
    for(int i = 0; i < leftMatrix.getRows(); i++) {
      for(int j =0; j < rightMatrix.getColumns(); j++) {
        int[] row = new int[leftMatrix.getColumns()];
        int[] column = new int[rightMatrix.getRows()];

        for(int n = 0; n < leftMatrix.getColumns(); n++) {
          row[n] = leftMatrix.getValues()[i][n];
        }
        for(int m = 0; m < rightMatrix.getRows(); m++) {
          column[m] = rightMatrix.getValues()[m][j];
        }
        Element futureElement = new Element();

        futureElement.setValue(executorService.submit( () -> {
          System.out.println(Thread.currentThread().getName() + " received a task");
            int element = this.multiplyRowByColumn(row,column);
            latch.countDown();
            return element;
            })
        );

        resultMatrix[i][j] = futureElement;
      }
    }
    executorService.shutdown();
    latch.await();

    int result[][] = new int[leftMatrix.getRows()][rightMatrix.getColumns()];
    for(int i = 0; i < leftMatrix.getRows(); i++) {
      for(int j =0; j < rightMatrix.getColumns(); j++) {
        result[i][j] = resultMatrix[i][j].getValue().get();
      }
    }

    return result;
  }

  private int multiplyRowByColumn(int[] row, int[] column) {
    int sum = 0;
    for(int i = 0; i < row.length; i++) {
      sum += row[i] * column[i];
    }
    return sum;
  }

}
