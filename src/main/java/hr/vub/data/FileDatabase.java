package hr.vub.data;

import hr.vub.util.LogUtil;

import java.io.*;
import java.util.logging.Logger;

public class FileDatabase {

    private static final Logger logger = LogUtil.getLogger();

    private final File file;

    public FileDatabase(String fileName) {
        file = new File(fileName);
    }

    public boolean exists() {
        return file.isFile() && file.exists();
    }

    public void write(String content) {
        try (FileOutputStream outputStream = new FileOutputStream(file, false);
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
            writer.write(content);
        } catch (Exception e) {
            logger.warning("Failed to save to file database!\n" + e.getMessage());
        }
    }

    public String read() {
        try (FileInputStream inputStream = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        } catch (Exception e) {
            logger.warning("Failed to read from file database!\n" + e.getMessage());
            return null;
        }
    }
}
