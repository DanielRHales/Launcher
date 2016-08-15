package com.logging;

import com.config.Constants;
import com.config.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Daniel
 */
public class Logger {

    public static void log(final Class clazz, final java.util.logging.Level level, final String message, final Exception exception) {
        java.util.logging.Logger.getLogger(clazz.getName()).log(level, message, exception);
        write(exception);
    }

    private static void write(Exception exception) {
        final File file = new File(Constants.ERROR_DIRECTORY, String.format("%d.txt", System.currentTimeMillis()));
        Environment.create(file, false);
        try {
            FileWriter writer = new FileWriter(file);
            exception.printStackTrace(new PrintWriter(writer));
            writer.flush();
            writer.close();
        } catch (IOException ignore) {
        }
    }

}
