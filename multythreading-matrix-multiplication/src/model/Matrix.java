package model;

public class Matrix {

  private int rows;
  private int columns;
  private int[][] values;

  public Matrix(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    this.values = new int[rows][columns];
  }

  public int[][] getValues() {
    return values;
  }

  public void setValues(int[][] values) {
    this.values = values;
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }
}
