package com.config;

import java.io.File;

/**
 * @author Daniel
 */
public class Constants {

    public static final String SERVER_NAME = "Noxious";

    public static final String TITLE = String.format("%s Launcher", SERVER_NAME);

    static final File MAIN_DIRECTORY = new File(System.getProperty("user.home"), SERVER_NAME);
    public static final File ERROR_DIRECTORY = new File(MAIN_DIRECTORY, ".LauncherErrors");
    public static final File PROGRAM_DIRECTORY = new File(MAIN_DIRECTORY, ".Clients");

    public static final String REMOTE_ROOT_LINK = String.format("http://cache.%sPs.com/", SERVER_NAME);
    public static final String REMOTE_VERSIONS_LINK = String.format("%sClients.json", REMOTE_ROOT_LINK);

    private static final String[] ALGORITHMS = new String[]{"MD5", "SHA-1", "SHA-256"};

    static final String ALGORITHM = ALGORITHMS[2];

}
