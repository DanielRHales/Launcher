package com.action.impl;

import com.action.Process;
import com.config.Environment;
import com.io.Connector;
import com.logging.Logger;
import com.version.Version;
import com.version.VersionHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Invoker extends Process {

    private Version version = null;

    public void preProcess() {
        version = (Version) getComboBox().getSelectedItem();
        toggle(false);
    }

    public void process() {
        VersionHandler.getInstance().refresh(version);
        if (version.updateRequired()) {
            Environment.remove(version.getFile());
            try {
                download();
            } catch (IOException ex) {
                Logger.log(Invoker.class, Level.WARNING, String.format("Error downloading file %s", version.getUrl()), ex);
            }
        }
    }

    public void postProcess() {
        try {
            Runtime.getRuntime().exec(new String[]{"java", "-jar", version.getFile().getAbsolutePath()});
        } catch (IOException ex) {
            Logger.log(Invoker.class, Level.SEVERE, "Error invoking external program", ex);
        }
        Environment.exit(false);
    }

    public String getDescription() {
        return "Invoking...";
    }

    private void download() throws IOException {
        final InputStream input = Connector.getUrlInputStream(version.getUrl());
        final OutputStream output = Connector.getFileOutputStream(version.getFile());
        final byte[] buffer = new byte[1024];
        int length;
        while ((input != null
                && output != null)
                && (length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        assert input != null;
        input.close();
        assert output != null;
        output.close();
        process();
    }

}