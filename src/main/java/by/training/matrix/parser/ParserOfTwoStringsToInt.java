package by.training.matrix.parser;

import by.training.matrix.exception.ParserOfTwoStringsToIntException;
import by.training.matrix.exception.ReaderOfTwoStringsException;
import by.training.matrix.reader.ReaderOfTwoStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParserOfTwoStringsToInt {

    private static final Logger LOGGER = LogManager.getLogger(ParserOfTwoStringsToInt.class.getName());

    private ReaderOfTwoStrings reader;
    private int firstInt;
    private int secondInt;

    public ParserOfTwoStringsToInt() throws ParserOfTwoStringsToIntException {
        try {
            this.reader = new ReaderOfTwoStrings();
            parseStrValuesToInt();
        } catch (ReaderOfTwoStringsException e) {
            LOGGER.debug("Catch " + e);
        }
    }

    private void parseStrValuesToInt() throws ParserOfTwoStringsToIntException {
        try {
            this.firstInt = Integer.parseInt(this.reader.getFirstStr());
            this.secondInt = Integer.parseInt(this.reader.getSecondStr());
        } catch (NumberFormatException e) {
            LOGGER.error("Error in parsing string to int");
            throw new ParserOfTwoStringsToIntException();
        }

    }

    public int getFirstInt() {
        return this.firstInt;
    }

    public int getSecondInt() {
        return this.secondInt;
    }
}
