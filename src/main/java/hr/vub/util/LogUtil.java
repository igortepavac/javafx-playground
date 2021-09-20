package hr.vub.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtil {

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
