package task2.writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task2.entity.Matrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteMatrixInfoInFile {
    private static final Logger LOGGER = LogManager.getLogger(WriteMatrixInfoInFile.class.getName());
    BufferedWriter writer;

    public WriteMatrixInfoInFile() {
        try {
            writer = new BufferedWriter(new FileWriter("out.txt", true));
        } catch (IOException e) {
            LOGGER.error("Error in writing in the file");
        }

    }

    public void writeInFile(Matrix matrix, Map<Integer, Integer> map) {
        try {
            writer.write(map.toString() + "\n" + matrix.toString());
            writer.close();
        } catch (IOException e) {
            LOGGER.error("Error in writing in the file");
        }

    }
}
