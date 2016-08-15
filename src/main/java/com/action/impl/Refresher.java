package com.action.impl;

import com.action.Process;
import com.version.Version;
import com.version.VersionManager;

import javax.swing.*;

/**
 * @author Daniel
 */
@SuppressWarnings("unchecked")
public class Refresher extends Process {

    private Version[] versions = null;

    public void preProcess() {
        toggle(false);
        versions = VersionManager.setVersions();
    }

    public void process() {
        for (Version version : versions) {
            version.refresh();
        }
    }

    public void postProcess() {
        getComboBox().setModel(new DefaultComboBoxModel(versions.length != 0 ? versions : new String[]{"No Clients are Currently Available"}));
        getComboBox().setSelectedIndex(0);
        toggle(true);
    }

    public String getDescription() {
        return "Refreshing...";
    }
}
