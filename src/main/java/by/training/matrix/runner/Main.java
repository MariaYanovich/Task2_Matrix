package by.training.matrix.runner;

import by.training.matrix.entity.Matrix;
import by.training.matrix.thread.RunnerOfThread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Matrix matrix = Matrix.getInstance();
        ExecutorService executor = Executors.newFixedThreadPool(matrix.getSize());
        List<RunnerOfThread> threadsList;
        for (int i = 0; i < matrix.getNumberOfIterations(); i++) {
            threadsList = new LinkedList<>();
            for (int j = 0; j < matrix.getSize(); j++) {
                threadsList.add(new RunnerOfThread(i * matrix.getSize() + j));
            }
            executor.invokeAll(threadsList);
        }
        executor.shutdown();
    }
}

