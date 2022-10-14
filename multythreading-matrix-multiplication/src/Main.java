import java.util.concurrent.ExecutionException;
import model.Matrix;
import service.MatrixMultiplicator;

public class Main {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    MatrixMultiplicator multiplicator = new MatrixMultiplicator();
    int processors = Runtime.getRuntime().availableProcessors();
    System.out.println("Number of processors " + processors);
    int[][] valuesForLeftMatrix =
        multiplicator.generateRandomMatrix(2,100000000);

    int[][] valuesForRightMatrix =
        multiplicator.generateRandomMatrix(100000000, 2);

    Matrix leftMatrix = new Matrix(valuesForLeftMatrix.length,valuesForLeftMatrix[0].length);
    leftMatrix.setValues(valuesForLeftMatrix);
    Matrix rightMatrix = new Matrix(valuesForRightMatrix.length,valuesForRightMatrix[0].length);
    rightMatrix.setValues(valuesForRightMatrix);

    long start = System.nanoTime();
    int[][] result = multiplicator.multiply(leftMatrix, rightMatrix);
    long finish = System.nanoTime();
    float parallelTime = (finish - start) / 1_000_000_000f;
    System.out.println("Parallel multiplication consumed " + parallelTime + " seconds");

    long start1 = System.nanoTime();
    int[][] result1 = multiplicator.sequentialMultiply(leftMatrix, rightMatrix);
    long finish1 = System.nanoTime();
    float sequentialTime = (finish1 - start1) / 1_000_000_000f;
    System.out.println("Sequential multiplication consumed " + sequentialTime + " seconds");

    float speedUp = sequentialTime / parallelTime;

    System.out.println("SpeedUP = " + speedUp);
    System.out.println("Efficiency = " + speedUp / processors * 100 +"%");

//      System.out.println("\nresulting matrix:");
//      for (int i = 0; i < result.length; i++) {
//        for (int j = 0; j < result[i].length; j++) {
//          if(result[i][j] == result1[i][j] ) {
//            System.out.print(result[i][j] + " ");
//          } else {
//            throw new RuntimeException("Results of calculation are wrong");
//          }
//        }
//        System.out.println();
//      }

  }

}
