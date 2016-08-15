package com.io;

import com.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Stream {

    private InputStream inputStream = null;

    private OutputStream outputStream = null;

    public Stream() {
    }

    public InputStream setInputStream(InputStream inputStream) {
        closeInputStream();
        return this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream setOutputStream(OutputStream outputStream) {
        closeOutputStream();
        return this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    private void closeInputStream() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.log(Stream.class, Level.WARNING, "Error closing Input Stream", ex);
            }
            inputStream = null;
        }
    }

    private void closeOutputStream() {
        if (outputStream != null) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException ex) {
                Logger.log(Stream.class, Level.WARNING, "Error closing Output Stream", ex);
            }
            outputStream = null;
        }
    }

    public void close() {
        closeInputStream();
        closeOutputStream();
    }
}
