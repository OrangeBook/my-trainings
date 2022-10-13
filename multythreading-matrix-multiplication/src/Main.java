import java.util.concurrent.ExecutionException;
import model.Matrix;
import service.MatrixMultiplicator;

public class Main {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    MatrixMultiplicator multiplicator = new MatrixMultiplicator();
    System.out.println("Number of processors " + Runtime.getRuntime().availableProcessors());
    int[][] valuesForLeftMatrix = {{5, 2, 3}, {3, 4, 3}, {3, 4, 5}, {3, 4, 5}, {5, 2, 3}, {3, 4, 3}, {3, 4, 5}, {3, 4, 5}};

    int[][] valuesForRightMatrix = {{1, 2, 3, 4, 3, 5, 3, 5}, {1, 2, 3, 4, 3, 5, 3, 5}, {1, 2, 3, 4, 3, 5, 3, 5}};

    Matrix leftMatrix = new Matrix(valuesForLeftMatrix.length,valuesForLeftMatrix[0].length);
    leftMatrix.setValues(valuesForLeftMatrix);
    Matrix rightMatrix = new Matrix(valuesForRightMatrix.length,valuesForRightMatrix[0].length);
    rightMatrix.setValues(valuesForRightMatrix);

    int[][] result = multiplicator.multiply(leftMatrix, rightMatrix);

    System.out.println("\nresulting matrix:");
    for (int i = 0; i < result.length; i++) {
      for( int j = 0; j < result[i].length; j++) {
        System.out.print( result[i][j] + " ");
      }
      System.out.println();
    }
  }

}
