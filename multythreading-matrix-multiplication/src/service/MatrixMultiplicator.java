package service;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import model.Matrix;

public class MatrixMultiplicator {

  private final int availableThreads = Runtime.getRuntime().availableProcessors();
  private final ExecutorService executorService = Executors.newFixedThreadPool(availableThreads);

  public int[][] sequentialMultiply(Matrix leftMatrix, Matrix rightMatrix) {
    System.out.println("Sequential multiplication have started");
    int[][] result = new int[leftMatrix.getRows()][rightMatrix.getColumns()];
    for (int i = 0; i < leftMatrix.getRows(); i++) {
      for(int j = 0; j < rightMatrix.getColumns(); j++ ) {

        for (int k = 0; k < leftMatrix.getColumns(); k++) {
          result [i][j] += leftMatrix.getValues()[i][k] * rightMatrix.getValues()[k][j];
        }
        for(int c = 0 ; c < 1_000_000_00; c++) {
          System.nanoTime();
        }
      }
    }

    return result;
  }

  public int[][] multiply(Matrix leftMatrix, Matrix rightMatrix)
      throws InterruptedException, ExecutionException {
    System.out.println("Parallel multiplication have started");

    if (leftMatrix.getColumns() != rightMatrix.getRows()) {
      throw new RuntimeException("Wrong dimension of matrices");
    }
    CountDownLatch latch = new CountDownLatch(leftMatrix.getRows() * rightMatrix.getColumns());
    Future<Integer>[][] resultMatrix = new Future[leftMatrix.getRows()][rightMatrix.getColumns()];
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
        resultMatrix[i][j] = (executorService.submit( () -> {
          System.out.println(Thread.currentThread().getName() + " received a task");
            int element = this.multiplyRowByColumn(row,column);
            latch.countDown();
            return element;
            })
        );
      }
    }
    executorService.shutdown();
    latch.await();
    int result[][] = new int[leftMatrix.getRows()][rightMatrix.getColumns()];
    for(int i = 0; i < leftMatrix.getRows(); i++) {
      for(int j =0; j < rightMatrix.getColumns(); j++) {
        result[i][j] = resultMatrix[i][j].get();
      }
    }

    return result;
  }

  public int[][] generateRandomMatrix(int numberOfRows, int numberOfColumns) {
    int[][] matrix = new int[numberOfRows][numberOfColumns];
    Random random = new Random();
    for(int i = 0; i < numberOfRows; i++ ) {
      for(int j = 0; j < numberOfColumns; j++) {
        matrix[i][j] = random.nextInt(10);
      }
    }
    return matrix;
  }
  private int multiplyRowByColumn(int[] row, int[] column) {
    int sum = 0;
    for(int i = 0; i < row.length; i++) {
      sum += row[i] * column[i];
    }
    for(int c = 0 ; c < 1_000_000_00; c++) {
      System.nanoTime();
    }
    return sum;
  }

}
