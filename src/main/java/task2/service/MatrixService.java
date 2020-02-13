package task2.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task2.entity.Matrix;
import task2.exception.InitializerOfMatrixSizeAndThreadsNumberException;
import task2.initializer.InitializerOfMatrixSizeAndThreadsNumber;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixService {

    private static final Logger LOGGER = LogManager.getLogger(MatrixService.class.getName());
    private static final int DEFAULT_NUMBER_OF_THREADS = 0;
    Lock lock;
    private Matrix matrix;
    private int numberOfThreads;
    private boolean[][] matrixOfInitializationBool;

    private MatrixService() {
        matrix = new Matrix();
        numberOfThreads = DEFAULT_NUMBER_OF_THREADS;
        lock = new ReentrantLock();
    }


    private static class MatrixServiceHolder {
        private static final MatrixService INSTANCE = new MatrixService();
    }

    public static MatrixService getInstance() {
        MatrixService instance = MatrixServiceHolder.INSTANCE;
        try {
            InitializerOfMatrixSizeAndThreadsNumber initializer = new InitializerOfMatrixSizeAndThreadsNumber();
            if (initializer.initializeMatrixSize() <= initializer.initializeNumberOfThreads()) {
                int size = initializer.initializeMatrixSize();
                instance.matrix.setSize(size);
                instance.numberOfThreads = initializer.initializeNumberOfThreads() *
                        instance.matrix.getSize();
                instance.matrixOfInitializationBool = new boolean[size][size];
            }
        } catch (InitializerOfMatrixSizeAndThreadsNumberException e) {
            LOGGER.error("Catch " + e);
        }
        return instance;
    }

    public int getElement(int n, int m) {
        return matrix.getMatrix()[n][m];
    }

    public void setElement(int n, int m, int value) {
        matrix.setElMatrix(n, m, value);
        matrixOfInitializationBool[n][m] = true;
    }

    public boolean getElementOfMatrixOfInitializationBool(int n, int m) {
        return matrixOfInitializationBool[n][m];
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setMatrixOfInitializationBool(boolean[][] matrixOfInitializationBool) {
        this.matrixOfInitializationBool = matrixOfInitializationBool;
    }

    public int getSum(int n) {
        int sum = 0;
        for (int i = 0; i < matrix.getSize(); i++) {
            sum += getElement(n, i);
            if (getElement(n, i) != getElement(i, n)) {
                sum += getElement(i, n);
            }
        }
        return sum;
    }

}
