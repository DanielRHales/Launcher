package com.version;

import com.config.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.io.Connector;
import com.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.logging.Level;

/**
 * @author Daniel
 */
class VersionManager {

    static Version[] loadVersions() {
        final InputStream stream = Connector.getUrlInputStream(Constants.REMOTE_VERSIONS_LINK);
        if (stream == null) {
            return new Version[]{};
        }
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
            final Version[] versions = new Gson().fromJson(new JsonParser().parse(reader), new TypeToken<Version[]>() {
            }.getType());
            reader.close();
            return versions;
        } catch (Exception ex) {
            Logger.log(VersionManager.class, Level.SEVERE, "Error loading Client Versions", ex);
            return new Version[]{};
        }
    }

}
