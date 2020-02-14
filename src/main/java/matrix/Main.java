package matrix;

import matrix.entity.Matrix;
import matrix.thread.RunnerOfThread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Matrix matrix = Matrix.getInstance();
        ExecutorService ex = Executors.newFixedThreadPool(matrix.getSize());
        List<RunnerOfThread> threads;
        for (int i = 0; i < matrix.getNumberOfThreads(); i++) {
            threads = new LinkedList<>();
            for (int j = 0; j < matrix.getSize(); j++) {
                threads.add(new RunnerOfThread(i * matrix.getSize() + j));
            }
            ex.invokeAll(threads);
        }
        ex.shutdown();
    }
}

