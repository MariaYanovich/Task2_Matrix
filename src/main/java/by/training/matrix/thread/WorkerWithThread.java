package by.training.matrix.thread;

import by.training.matrix.entity.Matrix;
import by.training.matrix.writer.WriteMatrixInfoInFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class WorkerWithThread {
    private static final Logger LOGGER = LogManager.getLogger(WorkerWithThread.class.getName());
    private static int counter;

    static {
        counter = 0;
    }

    private Matrix matrix;
    private Random random;
    private ReentrantLock locker;
    private Set<Integer> setOfThreadsId;

    private WorkerWithThread() {
        this.matrix = Matrix.getInstance();
        this.random = new Random();
        this.locker = new ReentrantLock();
        this.setOfThreadsId = new HashSet<>();
        this.setOfThreadsId.add(0);
    }

    public static WorkerWithThread getInstance() {
        return WorkerWithThreadHolder.INSTANCE;
    }

    public String writeIntoMatrix(final int id) {
        locker.lock();
        WriteMatrixInfoInFile writer = new WriteMatrixInfoInFile();
        LOGGER.debug("Start writing to thread: " + id);
        int element = 0;
        while (setOfThreadsId.contains(element)) {
            element = generateRandom(matrix.getNumberOfIterations()
                    * matrix.getSize() + 1);
        }
        setOfThreadsId.add(element);
        setElementInBooleanAndIntMatrices(counter, counter, element);
        initializeMatrix(element);
        LOGGER.debug("Sum: " + matrix.getSum(counter));
        writer.writeInFile(id, matrix.getSum(counter));
        counter++;
        doAfterEachIterationOfCycle();
        LOGGER.debug(matrix.toString());
        LOGGER.debug("End writing to thread: " + id);
        locker.unlock();
        return matrix.toString();
    }

    private void initializeMatrix(int element) {
        boolean isInitialized = false;
        int i = generateRandom(matrix.getSize());
        if (!matrix.getElementOfMatrixOfInitializationBool(counter, i) && i != counter) {
            setElementInBooleanAndIntMatrices(counter, i, element);
            isInitialized = true;
        }
        if (!isInitialized) {
            while (matrix.getElementOfMatrixOfInitializationBool(i, counter) || i == counter) {
                i = generateRandom(matrix.getSize());
            }
            setElementInBooleanAndIntMatrices(i, counter, element);
        }
    }

    private void doAfterEachIterationOfCycle() {
        WriteMatrixInfoInFile writer = new WriteMatrixInfoInFile();
        if (counter == matrix.getSize()) {
            writer.writeInFile(matrix);
            matrix.setBooleanMatrixOfInitialization(
                    new boolean[matrix.getSize()][matrix.getSize()]);
            counter = 0;
        }
    }

    private int generateRandom(int bound) {
        return random.nextInt(bound);
    }

    private void setElementInBooleanAndIntMatrices(int i, int j, int value) {
        matrix.setElementInIntMatrix(i, j, value);
        matrix.setElementInBooleanMatrix(i, j);
    }

    private static class WorkerWithThreadHolder {
        private static final WorkerWithThread INSTANCE = new WorkerWithThread();
    }

}
