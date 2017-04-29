package com.version;

import com.config.Configuration;
import com.config.Constants;
import com.config.Environment;
import com.data.StreamHash;
import com.frame.util.Terminal;
import com.io.Connector;
import com.logging.Logger;

import java.io.*;
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

    private String getUrl() {
        return String.format("%s%s", Constants.REMOTE_DATA_LINK, getFile().getName());
    }

    public String toString() {
        return String.format("%s v%.2f", type, version);
    }

    public String getDescription() {
        return String.format("%s", type);
    }

    public boolean updateRequired() {
        return fileKey == null || identifier == null || !fileKey.equals(identifier) || !getFile().exists();
    }

    void reload() {
        try {
            refresh();
        } catch (IOException ex) {
            Logger.log(Program.class, Level.WARNING, "Error refreshing file Identifier", ex);
        }
    }

    private void refresh() throws IOException {
        if (getFile().exists()) {
            final FileInputStream fileInputStream = new FileInputStream(getFile());
            fileKey = StreamHash.getStreamHash(fileInputStream);
            fileInputStream.close();
        } else {
            fileKey = null;
        }
    }

    public void invoke() {
        try {
            final ProcessBuilder builder = new ProcessBuilder("java", "-jar", "-Xmx256m", getFile().getAbsolutePath());
            final Process process = builder.start();
            if (Configuration.terminal) {
                new Terminal(this, process.getInputStream(), process.getErrorStream()).setVisible(true);
            }
        } catch (Exception ex) {
            Logger.log(Program.class, Level.SEVERE, "Error invoking external program", ex);
        }
    }

    public void get() {
        Environment.remove(getFile());
        try {
            download();
        } catch (IOException ex) {
            Logger.log(Program.class, Level.SEVERE, String.format("Unable to download file %s", getFile().getAbsolutePath()), ex);
        }
    }

    private void download() throws IOException {
        Environment.createFiles(getFile());
        final InputStream input = Connector.getUrlInputStream(getUrl());
        final OutputStream output = new FileOutputStream(getFile());
        final byte[] buffer = new byte[1024];
        int length;
        while (input != null && (length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        assert input != null;
        input.close();
        output.flush();
        output.close();
    }
}
