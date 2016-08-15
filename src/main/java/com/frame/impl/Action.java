package com.frame.impl;

import com.action.Process;
import com.action.impl.Invoker;
import com.action.impl.Refresher;
import com.action.impl.Selector;
import com.config.Constants;
import com.frame.UI;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Daniel
 */
public enum Action {
    REFRESH(UI.getInstance().getRefreshButton(), new Refresher()),
    LAUNCH(UI.getInstance().getLaunchButton(), new Invoker()),
    SELECT(UI.getInstance().getComboBox(), new Selector());

    private static final Map<JComponent, Action> BY_COMPONENT_VALUE = new HashMap<JComponent, Action>();

    static {
        for (Action action : values()) {
            BY_COMPONENT_VALUE.put(action.component, action);
        }
    }

    private final JComponent component;

    private final Process process;

    Action(JComponent component, Process process) {
        this.component = component;
        this.process = process;
    }

    public static Action getByJComponent(JComponent jComponent) {
        return BY_COMPONENT_VALUE.get(jComponent);
    }

    public void process() {
        UI.getInstance().setTitle(String.format("%s | %s", UI.getInstance().getTitle(), process.getDescription()));
        process.preProcess();
        new Thread(
                new SwingWorker() {
                    protected Void doInBackground() throws Exception {
                        process.process();
                        return null;
                    }

                    protected void done() {
                        UI.getInstance().setTitle(Constants.TITLE);
                        process.postProcess();
                    }
                }
        ).start();
    }
}
