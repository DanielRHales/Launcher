package com.action.impl;

import com.action.Process;
import com.version.Version;
import com.version.VersionManager;

/**
 * @author Daniel
 */
public class Selector extends Process {

    public Selector() {

    }

    public void preProcess() {
        toggle(false);
    }

    public void process() {
        for (Version version : VersionManager.getVersions()) {
            version.refresh();
        }
    }

    public void postProcess() {
        toggle(true);
    }

    public String getDescription() {
        return "Validating...";
    }

}
