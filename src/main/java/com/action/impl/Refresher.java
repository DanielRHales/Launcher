package com.action.impl;

import com.action.Process;
import com.version.VersionManager;

import javax.swing.*;

/**
 * @author Daniel
 */
@SuppressWarnings("unchecked")
public class Refresher extends Process {

    public void preProcess() {
        toggle(false);
    }

    public void process() {
        VersionManager.refreshVersions();
    }

    public void postProcess() {
        getComboBox().setModel(new DefaultComboBoxModel(VersionManager.setVersions().length != 0 ? VersionManager.setVersions() : new String[]{"No Clients are Currently Available"}));
        getComboBox().setSelectedIndex(0);
    }

    public String getDescription() {
        return "Refreshing...";
    }
}
