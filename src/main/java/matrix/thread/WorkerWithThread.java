package matrix.thread;

import matrix.entity.Matrix;
import matrix.writer.WriteMatrixInfoInFile;
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

    private static class WorkerWithThreadHolder {
        private static final WorkerWithThread INSTANCE = new WorkerWithThread();
    }

    public static WorkerWithThread getInstance() {
        return WorkerWithThreadHolder.INSTANCE;
    }

    private void initializeMatrix(int element) {
        boolean isInitialized = false;
        int i = generateRandom(matrix.getSize());
        if (!matrix.getElementOfMatrixOfInitializationBool(counter, i) && i != counter) {
            matrix.setElement(counter, i, element);
            isInitialized = true;
        }
        if (!isInitialized) {
            while (matrix.getElementOfMatrixOfInitializationBool(i, counter) || i == counter) {
                i = generateRandom(matrix.getSize());
            }
            matrix.setElement(i, counter, element);
        }
    }

    private void doAfterNThreads() {
        WriteMatrixInfoInFile writer = new WriteMatrixInfoInFile();
        if (counter == matrix.getSize()) {
            writer.writeInFile(matrix);
            matrix.setMatrixOfInitializationBool(new boolean[matrix.getSize()][matrix.getSize()]);
            counter = 0;
        }
    }

    private int generateRandom(int bound) {
        return random.nextInt(bound);
    }

    public String writeIntoMatrix(final int id) {
        locker.lock();
        WriteMatrixInfoInFile writer = new WriteMatrixInfoInFile();
        LOGGER.debug("Start writing to thread: " + id);
        int element = 0;
        while (setOfThreadsId.contains(element)) {
            element = generateRandom(matrix.getNumberOfThreads() * matrix.getSize() + 1);
        }
        setOfThreadsId.add(element);
        matrix.setElement(counter, counter, element);
        initializeMatrix(element);
        LOGGER.debug("Sum:" + matrix.getSum(counter));
        writer.writeInFile(id, matrix.getSum(counter));
        counter++;
        doAfterNThreads();
        LOGGER.debug(matrix.toString());
        LOGGER.debug("End writing to thread: " + id);
        locker.unlock();
        return matrix.toString();
    }
}
