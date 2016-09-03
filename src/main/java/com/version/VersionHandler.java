package com.version;

/**
 * @author Daniel
 */
public class VersionHandler {

    private Version[] versions = null;

    private VersionHandler() {
    }

    public Version[] getVersions() {
        return versions;
    }

    public Version[] reload() {
        return versions = VersionManager.loadVersions();
    }

    public Version getVersion(int index) {
        return versions[index];
    }

    public boolean isEmpty() {
        return versions == null || versions.length <= 0;
    }

    public void refresh() {
        for (Version version : versions) {
            refresh(version);
        }
    }

    public void refresh(Version version) {
        version.refresh();
    }

    public static VersionHandler getInstance() {
        return InstanceHolder.instance != null ? InstanceHolder.instance : (InstanceHolder.instance = new VersionHandler());
    }

    private static class InstanceHolder {
        private static VersionHandler instance;
    }

}
