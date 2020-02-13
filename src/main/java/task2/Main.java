package task2;

import task2.service.MatrixService;
import task2.thread.ThreadsRunner;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MatrixService matrixService = MatrixService.getInstance();
        Semaphore semaphore = new Semaphore(MatrixService.getInstance().getMatrix().getSize());
        for (int i = 1; i <= matrixService.getNumberOfThreads(); i++) {
            new ThreadsRunner(semaphore, matrixService, i).start();
            TimeUnit.MILLISECONDS.sleep(100);
        }

    }
}

