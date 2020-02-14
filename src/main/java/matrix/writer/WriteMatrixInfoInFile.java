package matrix.writer;

import matrix.entity.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

    public void writeInFile(Matrix matrix) {
        try {
            writer.write(matrix.toString());
            writer.close();
        } catch (IOException e) {
            LOGGER.error("Error in writing in the file");
        }
    }

    public void writeInFile(int n1, int n2) {
        try {
            writer.write(n1 + " " + n2 + "\n");
            writer.close();
        } catch (IOException e) {
            LOGGER.error("Error in writing in the file");
        }
    }
}
