package com.action.impl;

import com.action.Process;
import com.version.VersionHandler;

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
        VersionHandler.getInstance().reload();
    }

    public void postProcess() {
        final int index = getComboBox().getSelectedIndex();
        getComboBox().setModel(new DefaultComboBoxModel(VersionHandler.getInstance().isEmpty() ? new String[]{"No Clients are Currently Available"} : VersionHandler.getInstance().getVersions()));
        final int length = VersionHandler.getInstance().getVersions().length;
        getComboBox().setToolTipText(String.format("Clients List (%d Object%s)", length, length != 1 ? "s" : ""));
        getComboBox().setSelectedIndex(index != -1 && VersionHandler.getInstance().getVersion(index) != null ? index : 0);
    }

    public String getDescription() {
        return "Refreshing...";
    }
}
