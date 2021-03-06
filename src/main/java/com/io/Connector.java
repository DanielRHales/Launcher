package com.io;

import com.config.Environment;
import com.frame.impl.Dialog;
import com.logging.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Connector {

    public static InputStream getUrlInputStream(String url) {
        try {
            return getUrlInputStream(getUrlConnection(url));
        } catch (IOException ex) {
            Logger.log(Connector.class, Level.WARNING, "Error Getting URL Input Stream", ex);
            if (Dialog.acceptedChoice("Failed to Connect", "Try to Reconnect?")) {
                return getUrlInputStream(url);
            } else {
                Environment.exit();
            }
        }
        return null;
    }

    private static InputStream getUrlInputStream(URLConnection connection) throws IOException {
        connection.setReadTimeout(300000);
        return connection.getInputStream();
    }

    private static URLConnection getUrlConnection(String url) {
        try {
            return getUrlConnection(new URL(url));
        } catch (IOException ex) {
            Logger.log(Connector.class, Level.WARNING, "Error Getting URL Connection", ex);
            return null;
        }
    }

    private static URLConnection getUrlConnection(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(300000);
        return connection;
    }

}
