package com.action.impl;

import com.action.Process;
import com.config.Environment;
import com.io.Connector;
import com.logging.Logger;
import com.version.Program;
import com.version.VersionHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Invoker extends Process {

    private Program program = null;

    public void preProcess() {
        program = (Program) getComboBox().getSelectedItem();
        toggle(false);
    }

    public void process() {
        VersionHandler.getInstance().refresh(program);
        if (program.updateRequired()) {
            Environment.remove(program.getFile());
            try {
                download();
            } catch (IOException ex) {
                Logger.log(Invoker.class, Level.WARNING, String.format("Error downloading file %s", program.getUrl()), ex);
            }
        }
    }

    public void postProcess() {
        try {
            new ProcessBuilder("java", "-jar", "-Xmx256m", program.getFile().getAbsolutePath()).start();
        } catch (IOException ex) {
            Logger.log(Invoker.class, Level.SEVERE, "Error invoking external program", ex);
        }
        toggle(true);
    }

    public String getDescription() {
        return "Invoking...";
    }

    private void download() throws IOException {
        Environment.createFiles(program.getFile());
        final InputStream input = Connector.getUrlInputStream(program.getUrl());
        final OutputStream output = new FileOutputStream(program.getFile());
        final byte[] buffer = new byte[1024];
        int length;
        while (input != null && (length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        assert input != null;
        input.close();
        output.flush();
        output.close();
        process();
    }

}