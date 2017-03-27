package com.action.impl;

import com.action.Process;
import com.config.Configuration;

/**
 * @author Daniel
 */
public class TerminalToggle extends Process {

    @Override
    public void preProcess() {
    }

    @Override
    public void process() {
        Configuration.terminal = !Configuration.terminal;
    }

    @Override
    public void postProcess() {
    }

    @Override
    public String getDescription() {
        return "Toggling Terminal";
    }
}
