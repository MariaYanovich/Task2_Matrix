package matrix.initializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import matrix.exception.InitializerOfMatrixSizeAndThreadsNumberException;
import matrix.exception.ParserOfTwoStringsToIntException;
import matrix.parser.ParserOfTwoStringsToInt;


public class InitializerOfMatrixSizeAndThreadsNumber {

    private static final Logger LOGGER = LogManager.getLogger(InitializerOfMatrixSizeAndThreadsNumber.class.getName());

    private ParserOfTwoStringsToInt parser;

    public InitializerOfMatrixSizeAndThreadsNumber() {
        try {
            parser = new ParserOfTwoStringsToInt();
        } catch (ParserOfTwoStringsToIntException e) {
            LOGGER.debug("Catch " + e);
        }
    }

    public int initializeMatrixSize() throws InitializerOfMatrixSizeAndThreadsNumberException {
        int sizeToReturn;
        try {
            sizeToReturn = parser.getFirstInt();
        } catch (NullPointerException e) {
            throw new InitializerOfMatrixSizeAndThreadsNumberException();
        }
        return sizeToReturn;
    }


    public int initializeNumberOfThreads() throws InitializerOfMatrixSizeAndThreadsNumberException {
        int numberOfThreadsToReturn;
        try {
            numberOfThreadsToReturn = parser.getSecondInt();
        } catch (NullPointerException e) {
            throw new InitializerOfMatrixSizeAndThreadsNumberException();
        }
        return numberOfThreadsToReturn;
    }
}
