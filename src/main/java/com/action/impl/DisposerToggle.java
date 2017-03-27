package com.action.impl;

import com.action.Process;
import com.config.Configuration;

/**
 * @author Daniel
 */
public class DisposerToggle extends Process {
    @Override
    public void preProcess() {

    }

    @Override
    public void process() {
        Configuration.dispose = !Configuration.dispose;
    }

    @Override
    public void postProcess() {

    }

    @Override
    public String getDescription() {
        return "Toggling Disposal";
    }
}
