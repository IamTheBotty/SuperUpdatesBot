package utils;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.logging.Logger;

/**
 * Created by pavel on 02.11.17.
 */
public class Helpers {

    /**
     * Возвращает файл {@link File} из ресурсов.
     *
     * @param filePath - относительный путь к файлу
     * @return строку с содержим текстового файла
     */
    public static File getFileFromResource(String filePath) {
        try (InputStream inputStream = Helpers.class.getClassLoader().getResourceAsStream(filePath)) {
            final File tempFile = File.createTempFile(filePath, ".tmp");
            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                IOUtils.copy(inputStream, out);
            }
            return tempFile;
        } catch (IOException e) {
            return new File("");
        }
    }

}
