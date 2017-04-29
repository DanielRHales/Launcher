package com.action;

import com.frame.UI;

import javax.swing.*;

/**
 * @author Daniel
 */
public abstract class Process {

    private final JButton refreshButton;
    private final JButton launchButton;
    private final JComboBox comboBox;
    private final JCheckBox terminalCheckBox;
    private final JCheckBox disposeCheckBox;

    protected Process() {
        refreshButton = UI.getInstance().getRefreshButton();
        launchButton = UI.getInstance().getLaunchButton();
        comboBox = UI.getInstance().getComboBox();
        terminalCheckBox = UI.getInstance().getTerminalCheckbox();
        disposeCheckBox = UI.getInstance().getDisposeCheckbox();
    }

    public abstract void preProcess();

    public abstract void process();

    public abstract void postProcess();

    public abstract String getDescription();

    protected JComboBox getComboBox() {
        return comboBox;
    }

    protected void toggle(boolean enabled) {
        refreshButton.setEnabled(enabled);
        launchButton.setEnabled(enabled);
        comboBox.setEnabled(enabled);
        terminalCheckBox.setEnabled(enabled);
        disposeCheckBox.setEnabled(enabled);
    }

}
