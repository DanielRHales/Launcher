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
        getComboBox().setModel(new DefaultComboBoxModel(VersionHandler.getInstance().isEmpty() ? new String[]{"No Programs are Currently Available"} : VersionHandler.getInstance().getPrograms()));
        final int length = VersionHandler.getInstance().getPrograms().length;
        getComboBox().setToolTipText(String.format("Programs List (%d Object%s)", length, length != 1 ? "s" : ""));
        getComboBox().setSelectedIndex(index != -1 && VersionHandler.getInstance().getVersion(index) != null ? index : 0);
    }

    public String getDescription() {
        return "Refreshing...";
    }
}
