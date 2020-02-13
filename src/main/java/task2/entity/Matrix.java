package task2.entity;

import java.util.Arrays;

public class Matrix {

    private static final int DEFAULT_SIZE = 0;
    private int[][] matrix;

    public Matrix() {
        this.matrix = new int[DEFAULT_SIZE][DEFAULT_SIZE];
    }

    public Matrix(int size) {
        this.matrix = new int[size][size];
    }

    public void setSize(int size) {
        this.matrix = new int[size][size];
    }

    public int getSize() {
        return matrix.length;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setElMatrix(int n, int m, int value) {
        matrix[n][m] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;
        Matrix matrix1 = (Matrix) o;
        return Arrays.equals(getMatrix(), matrix1.getMatrix());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getMatrix());
    }

    @Override
    public String toString() {
        String strMatrix = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                strMatrix = strMatrix.concat(String.valueOf(matrix[i][j])).concat(" ");
            }
            strMatrix = strMatrix.concat("\n");
        }
        return "Matrix(size=" + matrix.length + "):\n" + strMatrix;
    }
}
