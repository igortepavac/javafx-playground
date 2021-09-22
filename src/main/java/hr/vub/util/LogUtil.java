package hr.vub.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtil {

    private static final String DEFAULT_LOGGER_NAME = "hr.vub.eventxyz";
    private static final String DEFAULT_LOG_FILE_NAME = "EventApp.log";

    private static Logger INSTANCE = null;
    private static FileHandler FILE_HANDLER = null;

    public static Logger getLogger() {
        return getLogger(DEFAULT_LOGGER_NAME, DEFAULT_LOG_FILE_NAME);
    }

    public static Logger getLogger(String loggerName, String fileName) {
        if (INSTANCE == null) {
            final Logger logger = Logger.getLogger(loggerName);
            try {
                final FileHandler fileHandler = new FileHandler(fileName);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);
                FILE_HANDLER = fileHandler;
            } catch (IOException e) {
                e.printStackTrace();
            }
            INSTANCE = logger;
        }
        return INSTANCE;
    }

    public static void clear() {
        FILE_HANDLER.close();
        FILE_HANDLER = null;
        INSTANCE = null;
    }
}
