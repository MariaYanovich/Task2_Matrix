package task2.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task2.exception.ReaderOfTwoStringsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class ReaderOfTwoStrings {
    private static final Logger LOGGER = LogManager.getLogger(ReaderOfTwoStrings.class.getName());

    private String firstStr;
    private String secondStr;
    private File file;

    public ReaderOfTwoStrings() throws ReaderOfTwoStringsException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try {
            file = new File(Objects.requireNonNull(classLoader.getResource("TwoInts.txt")).getFile());
            readValues();
        } catch (NullPointerException e) {
            LOGGER.error("Error in reading from txt file");
            throw new ReaderOfTwoStringsException(e);
        }
    }

    private void readValues() throws ReaderOfTwoStringsException {
        try {
            Scanner sc = new Scanner(file);
            firstStr = sc.nextLine();
            secondStr = sc.nextLine();
        } catch (FileNotFoundException e) {
            LOGGER.error("Can't find txt file");
            throw new ReaderOfTwoStringsException(e);
        }
    }

    public String getFirstStr() {
        return firstStr;
    }

    public String getSecondStr() {
        return secondStr;
    }
}
