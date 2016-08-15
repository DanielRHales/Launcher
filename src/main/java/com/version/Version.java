package com.version;

import com.config.Configuration;
import com.config.Constants;
import com.data.CheckSum;
import com.io.Connector;

import java.io.File;

/**
 * @author Daniel
 */
public class Version {

    private final String name;
    private final double currentVersion;
    private final String jarName;

    private String fileHash = null;
    private String urlHash = null;

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
        return fileHash == null
                || urlHash == null
                || !fileHash.equals(urlHash)
                || !getFile().exists();
    }

    public void refresh() {
        fileHash = (CheckSum.getStreamHash(Configuration.getFileStream().setInputStream(Connector.getFileInputStream(getFile()))));
        if (urlHash == null) {
            urlHash = (CheckSum.getStreamHash(Configuration.getUrlStream().setInputStream(Connector.getUrlInputStream(getUrl()))));
        }
        Configuration.getFileStream().close();
    }
}
