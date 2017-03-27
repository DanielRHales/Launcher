package com.action.impl;

import com.action.Process;
import com.config.Configuration;
import com.frame.UI;
import com.version.Program;
import com.version.VersionHandler;

/**
 * @author Daniel
 */
public class Invoker extends Process {

    private Program program = null;

    public void preProcess() {
        toggle(false);
        program = (Program) getComboBox().getSelectedItem();
    }

    public void process() {
        VersionHandler.getInstance().refresh(program);
        if (program.updateRequired()) {
            program.get();
            process();
        }
    }

    public void postProcess() {
        program.invoke();
        toggle(true);
        if (Configuration.dispose) {
            UI.getInstance().dispose();
        }
    }

    public String getDescription() {
        return "Invoking...";
    }

}