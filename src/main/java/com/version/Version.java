package com.version;

import com.config.Configuration;
import com.config.Constants;
import com.data.StreamHash;
import com.io.Connector;

import java.io.File;

/**
 * @author Daniel
 */
public class Version {

    private final String name;
    private final double currentVersion;
    private final String jarName;

    private String fileKey = null;
    private String urlKey = null;

    @SuppressWarnings("unused")
    public Version(String name, double currentVersion, String jarName) {
        this.name = name;
        this.currentVersion = currentVersion;
        this.jarName = jarName;
    }

    public File getFile() {
        return new File(Constants.PROGRAM_DIRECTORY, String.format("%s-%s.jar", jarName, Double.toString(currentVersion).replace(".", "_")));
    }

    public String getUrl() {
        return String.format("%s%s", Constants.REMOTE_ROOT_LINK, getFile().getName());
    }

    public String toString() {
        return String.format("%s Client v%.1f", name, currentVersion);
    }

    public String getDescription() {
        return String.format("%s - %s", name, getFile().getName());
    }

    public boolean updateRequired() {
        return fileKey == null
                || urlKey == null
                || !fileKey.equals(urlKey)
                || !getFile().exists();
    }

    public void refresh() {
        fileKey = (StreamHash.getStreamHash(Configuration.getFileStream().setInputStream(Connector.getFileInputStream(getFile()))));
        if (urlKey == null) {
            urlKey = (StreamHash.getStreamHash(Configuration.getUrlStream().setInputStream(Connector.getUrlInputStream(getUrl()))));
        }
        Configuration.getFileStream().close();
    }
}
