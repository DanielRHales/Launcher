package com.version;

/**
 * @author Daniel
 */
public class VersionHandler {

    private Program[] programs = null;

    private VersionHandler() {
    }

    public Program[] getPrograms() {
        return programs;
    }

    public Program[] reload() {
        return programs = ProgramManager.loadVersions();
    }

    public Program getVersion(int index) {
        return programs[index];
    }

    public boolean isEmpty() {
        return programs == null || programs.length <= 0;
    }

    public void refresh() {
        for (Program program : programs) {
            refresh(program);
        }
    }

    public void refresh(Program program) {
        program.reload();
    }

    public static VersionHandler getInstance() {
        return InstanceHolder.instance != null ? InstanceHolder.instance : (InstanceHolder.instance = new VersionHandler());
    }

    private static class InstanceHolder {
        private static VersionHandler instance;
    }

}
