package by.training.matrix.entity;

import by.training.matrix.exception.InitializerOfMatrixSizeAndThreadsNumberException;
import by.training.matrix.initializer.InitializerOfMatrixSizeAndThreadsNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Matrix {

    private static final Logger LOGGER = LogManager.getLogger(Matrix.class.getName());
    private static final int DEFAULT_SIZE = 0;
    private static final int DEFAULT_NUMBER_OF_THREADS = 0;
    private int[][] intMatrix;
    private boolean[][] booleanMatrixOfInitialization;
    private int numberOfIterations;
    private int size;

    private Matrix() {
        this.size = DEFAULT_SIZE;
        this.intMatrix = new int[size][size];
        this.booleanMatrixOfInitialization = new boolean[size][size];
        this.numberOfIterations = DEFAULT_NUMBER_OF_THREADS;
    }

    public Matrix(int size) {
        this.intMatrix = new int[size][size];
    }

    public static Matrix getInstance() {
        Matrix instance = MatrixHolder.INSTANCE;
        try {
            InitializerOfMatrixSizeAndThreadsNumber initializer = new InitializerOfMatrixSizeAndThreadsNumber();
            int size = initializer.initializeMatrixSize();
            instance.setSize(size);
            instance.setSizeToIntMatrix(size);
            instance.setSizeToBooleanMatrix(size);
            instance.setNumberOfIterations(initializer.initializeNumberOfIterations());
        } catch (InitializerOfMatrixSizeAndThreadsNumberException e) {
            LOGGER.error("Catch " + e);
        }
        return instance;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public void setSizeToIntMatrix(int size) {
        this.intMatrix = new int[size][size];
    }

    public void setSizeToBooleanMatrix(int size) {
        this.booleanMatrixOfInitialization = new boolean[size][size];
    }


    public void setElementInIntMatrix(int n, int m, int value) {
        intMatrix[n][m] = value;
    }

    public void setElementInBooleanMatrix(int n, int m) {
        booleanMatrixOfInitialization[n][m] = true;
    }

    public int getElementOfIntMatrix(int n, int m) {
        return intMatrix[n][m];
    }

    public boolean getElementOfMatrixOfInitializationBool(int n, int m) {
        return booleanMatrixOfInitialization[n][m];
    }

    public int[][] getIntMatrix() {
        return this.intMatrix;
    }

    public void setBooleanMatrixOfInitialization(boolean[][] booleanMatrixOfInitialization) {
        this.booleanMatrixOfInitialization = booleanMatrixOfInitialization;
    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public void setNumberOfIterations(int numberOfThreads) {
        this.numberOfIterations = numberOfThreads;
    }

    public int getSum(int n) {
        int sum = 0;
        for (int i = 0; i < intMatrix.length; i++) {
            sum += getElementOfIntMatrix(n, i);
            if (getElementOfIntMatrix(n, i) != getElementOfIntMatrix(i, n)) {
                sum += getElementOfIntMatrix(i, n);
            }
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;
        Matrix matrix1 = (Matrix) o;
        return Arrays.equals(getIntMatrix(), matrix1.getIntMatrix());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getIntMatrix());
    }

    @Override
    public String toString() {
        String strMatrix = "";
        for (int[] matrix : intMatrix) {
            for (int j = 0; j < intMatrix.length; j++) {
                strMatrix = strMatrix.concat(String.valueOf(matrix[j])).concat(" ");
            }
            strMatrix = strMatrix.concat("\n");
        }
        return "Matrix(size=" + intMatrix.length + "):\n"
                + strMatrix.substring(0, strMatrix.length() - 1);
    }

    private static class MatrixHolder {
        private static final Matrix INSTANCE = new Matrix();
    }
}
