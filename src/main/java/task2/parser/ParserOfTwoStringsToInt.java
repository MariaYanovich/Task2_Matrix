package task2.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task2.exception.ParserOfTwoStringsToIntException;
import task2.exception.ReaderOfTwoStringsException;
import task2.reader.ReaderOfTwoStrings;

public class ParserOfTwoStringsToInt {
    private static final Logger LOGGER = LogManager.getLogger(ParserOfTwoStringsToInt.class.getName());

    private ReaderOfTwoStrings reader;
    private int firstInt;
    private int secondInt;

    public ParserOfTwoStringsToInt() throws ParserOfTwoStringsToIntException {
        try {
            reader = new ReaderOfTwoStrings();
            parseStrParametersToInt();
        } catch (ReaderOfTwoStringsException e) {
            LOGGER.debug("Catch " + e);
        }

    }

    private void parseStrParametersToInt() throws ParserOfTwoStringsToIntException {
        try {
            firstInt = Integer.parseInt(reader.getFirstStr());
            secondInt = Integer.parseInt(reader.getSecondStr());
        } catch (NumberFormatException e) {
            LOGGER.error("Error in parsing string to int");
            throw new ParserOfTwoStringsToIntException();
        }

    }

    public int getFirstInt() {
        return firstInt;
    }

    public int getSecondInt() {
        return secondInt;
    }
}
