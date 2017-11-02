package utils;

import org.apache.commons.io.IOUtils;

import java.io.*;

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
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return new File(classLoader.getResource(filePath).getFile());
    }

}
