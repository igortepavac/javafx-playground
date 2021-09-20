package hr.vub.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtil {

    private static final String DEFAULT_LOG_FILE_NAME = "EventApp.log";

    public static Logger getLogger(String loggerName) {
        return getLogger(loggerName, DEFAULT_LOG_FILE_NAME);
    }

    public static Logger getLogger(String loggerName, String fileName) {
        final Logger logger = Logger.getLogger(loggerName);
        try {
            final FileHandler fileHandler = new FileHandler(fileName);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logger;
    }
}
