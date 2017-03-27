package com.config;

import com.frame.impl.Dialog;
import com.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Environment {

    static {
        createDirectories(Constants.MAIN_DIRECTORY, Constants.PROGRAM_DIRECTORY);
    }

    public static void createFiles(File... files) {
        for (File file : files) {
            create(file, false);
        }
    }

    private static void createDirectories(File... files) {
        for (File file : files) {
            create(file, true);
        }
    }

    private static void create(final File file, final boolean directory) {
        if (directory ? !createDirectory(file) : !createFile(file)) {
            if (Dialog.acceptedChoice(String.format("Try to create \'%s\' again?", file.getName()), String.format("Error creating %s '%s'.", directory ? "directory" : "file", file.getName()))) {
                create(file, directory);
            } else {
                exit();
            }
        }
    }

    public static void remove(final File file) {
        if (!removeFile(file)) {
            if (Dialog.acceptedChoice(String.format("Try to remove \'%s\' again?", file.getName()), String.format("Error removing file '%s'.", file.getName()))) {
                remove(file);
            } else {
                exit();
            }
        }
        createFile(file);
    }

    public static void exit() {
        Dialog.displayMessage();
        System.exit(0);
    }

    private static boolean createDirectory(final File directory) {
        return directory.isDirectory() || directory.mkdirs();
    }

    private static boolean createFile(final File file) {
        if (file.exists()) {
            return true;
        }
        try {
            return file.createNewFile();
        } catch (IOException ex) {
            Logger.log(Environment.class, Level.WARNING, String.format("Error creating file \'%s\'", file.getAbsolutePath()), ex);
            return false;
        }
    }

    private static boolean removeFile(final File file) {
        return !file.exists() || file.delete();
    }

}
