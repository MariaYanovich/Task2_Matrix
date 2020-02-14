package by.training.matrix.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;


public class RunnerOfThread implements Callable<String> {
    private static final Logger LOGGER = LogManager.getLogger(RunnerOfThread.class.getName());

    private WorkerWithThread workerWithThread;
    private int id;

    public RunnerOfThread(int id) {
        this.id = ++id;
        workerWithThread = WorkerWithThread.getInstance();
    }

    public String call() {
        LOGGER.debug("Start thread: " + id);
        String result = workerWithThread.writeIntoMatrix(id);
        LOGGER.debug("End thread: " + id);
        return result;
    }
}