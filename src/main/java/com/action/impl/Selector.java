package com.action.impl;

import com.action.Process;
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
        VersionManager.refreshVersions();
    }

    public void postProcess() {
        toggle(true);
    }

    public String getDescription() {
        return "Validating...";
    }

}
