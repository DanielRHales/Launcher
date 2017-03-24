package com.config;

import java.io.File;

/**
 * @author Daniel
 */
public class Constants {

    public static final String SERVER_NAME = "ArteroPk";

    public static final String TITLE = String.format("%s Launcher", SERVER_NAME);

    static final File MAIN_DIRECTORY = new File(System.getProperty("user.home"), String.format("%s Files", SERVER_NAME));
    public static final File PROGRAM_DIRECTORY = new File(MAIN_DIRECTORY, "Programs");

    private static final String REMOTE_ROOT_LINK = String.format("http://cache.%s.com/", SERVER_NAME);
    public static final String REMOTE_VERSIONS_LINK = String.format("%sFiles/JSON/Programs.json", REMOTE_ROOT_LINK);
    public static final String REMOTE_DATA_LINK = String.format("%sData/", REMOTE_ROOT_LINK);

    private static final String[] ALGORITHMS = new String[]{"MD5", "SHA-1", "SHA-256"};

    static final String ALGORITHM = ALGORITHMS[2];

}
