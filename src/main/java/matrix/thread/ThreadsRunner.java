package matrix.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import matrix.service.MatrixService;
import matrix.writer.WriteMatrixInfoInFile;

import java.util.*;
import java.util.concurrent.Semaphore;

public class ThreadsRunner extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(ThreadsRunner.class.getName());
    private static int counter;
    private static Map<Integer, Integer> mapOfSums;
    private static Set<Integer> threadsId;

    static {
        counter = 0;
        mapOfSums = new HashMap<>();
        threadsId = new HashSet<>();
        threadsId.add(0);
    }

    private MatrixService matrix;
    private Semaphore semaphore;
    private int matrixSize;
    private int id;
    private Random random;

    public ThreadsRunner() {

    }

    public ThreadsRunner(Semaphore sem, MatrixService matrix, int id) {
        this.semaphore = sem;
        this.matrix = matrix;
        this.matrixSize = matrix.getMatrix().getSize();
        this.id = id;
        this.random = new Random();
    }

    public void run() {
        try {
            semaphore.acquire();
            LOGGER.debug("Start thread: " + id);
            int element = 0;
            while (threadsId.contains(element)) {
                element = random.nextInt(matrix.getNumberOfThreads() + 1);
            }
            threadsId.add(element);
            matrix.setElement(counter, counter, element);
            boolean isInitialized = false;
            int i = random.nextInt(matrixSize);
            if (!matrix.getElementOfMatrixOfInitializationBool(counter, i) && i != counter) {
                matrix.setElement(counter, i, element);
                isInitialized = true;
            }
            if (!isInitialized) {
                while (matrix.getElementOfMatrixOfInitializationBool(i, counter) || i == counter) {
                    i = random.nextInt(matrixSize);
                }
                matrix.setElement(i, counter, element);
            }
            LOGGER.debug("Sum:" + matrix.getSum(counter));
            mapOfSums.put(id, matrix.getSum(counter));
            counter++;
            semaphore.release();
            doAfterNThreads();
            LOGGER.debug(matrix.getMatrix());
            LOGGER.debug("End ID:" + id);

        } catch (InterruptedException e) {
            System.out.println(counter + "error");
        }
    }

    public void doAfterNThreads() {
        WriteMatrixInfoInFile writer = new WriteMatrixInfoInFile();
        if (counter == matrixSize) {
            writer.writeInFile(matrix.getMatrix(), mapOfSums);
            matrix.setMatrixOfInitializationBool(new boolean[matrixSize][matrixSize]);
            counter = 0;
        }
    }

}

