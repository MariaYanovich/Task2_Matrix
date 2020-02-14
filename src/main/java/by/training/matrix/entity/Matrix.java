package by.training.matrix.entity;

import by.training.matrix.exception.InitializerOfMatrixSizeAndThreadsNumberException;
import by.training.matrix.initializer.InitializerOfMatrixSizeAndThreadsNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Matrix {

    private static final int DEFAULT_SIZE = 0;
    private static final Logger LOGGER = LogManager.getLogger(Matrix.class.getName());
    private static final int DEFAULT_NUMBER_OF_THREADS = 0;
    private int[][] matrix;
    private int numberOfThreads;
    private boolean[][] matrixOfInitializationBool;

    private Matrix() {
        this.matrix = new int[DEFAULT_SIZE][DEFAULT_SIZE];
        numberOfThreads = DEFAULT_NUMBER_OF_THREADS;
    }

    public Matrix(int size) {
        this.matrix = new int[size][size];
    }

    public static Matrix getInstance() {
        Matrix instance = MatrixHolder.INSTANCE;
        try {
            InitializerOfMatrixSizeAndThreadsNumber initializer = new InitializerOfMatrixSizeAndThreadsNumber();
            if (initializer.initializeMatrixSize() <= initializer.initializeNumberOfThreads()) {
                int size = initializer.initializeMatrixSize();
                instance.setSize(size);
                instance.numberOfThreads = initializer.initializeNumberOfThreads();
                instance.matrixOfInitializationBool = new boolean[size][size];
            }
        } catch (InitializerOfMatrixSizeAndThreadsNumberException e) {
            LOGGER.error("Catch " + e);
        }
        return instance;
    }

    public int getSize() {
        return matrix.length;
    }

    public void setSize(int size) {
        this.matrix = new int[size][size];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setElement(int n, int m, int value) {
        matrix[n][m] = value;
        matrixOfInitializationBool[n][m] = true;
    }

    public int getElement(int n, int m) {
        return matrix[n][m];
    }

    public boolean getElementOfMatrixOfInitializationBool(int n, int m) {
        return matrixOfInitializationBool[n][m];
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setMatrixOfInitializationBool(boolean[][] matrixOfInitializationBool) {
        this.matrixOfInitializationBool = matrixOfInitializationBool;
    }

    public int getSum(int n) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += getElement(n, i);
            if (getElement(n, i) != getElement(i, n)) {
                sum += getElement(i, n);
            }
        }
        return sum;
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
        return "Matrix(size=" + matrix.length + "):\n"
                + strMatrix.substring(0, strMatrix.length() - 1);
    }

    private static class MatrixHolder {
        private static final Matrix INSTANCE = new Matrix();
    }
}
