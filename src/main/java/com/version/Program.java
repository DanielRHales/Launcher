package com.version;

import com.config.Constants;
import com.data.StreamHash;
import com.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Program {

    private String fileKey = null;

    private final String type;
    private final double version;
    private final String identifier;

    @SuppressWarnings("unused")
    public Program(String type, double version, String identifier) {
        this.type = type;
        this.version = version;
        this.identifier = identifier;
    }

    public File getFile() {
        return new File(Constants.PROGRAM_DIRECTORY, String.format("%s-%s.jar", type.replace(" ", "_"), Double.toString(version).replace(".", "-")));
    }

    public String getUrl() {
        return String.format("%s%s", Constants.REMOTE_DATA_LINK, getFile().getName());
    }

    public String toString() {
        return String.format("%s v%.1f", type, version);
    }

    public String getDescription() {
        return String.format("%s - %s", type, getFile().getName());
    }

    public boolean updateRequired() {
        return fileKey == null
                || identifier == null
                || !fileKey.equals(identifier)
                || !getFile().exists();
    }

    void reload() {
        try {
            refresh();
        } catch (IOException ex) {
            Logger.log(Program.class, Level.WARNING, "Error refreshing file Identifier", ex);
        }
    }

    private void refresh() throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(getFile());
        fileKey = StreamHash.getStreamHash(fileInputStream);
        fileInputStream.close();
    }
}
