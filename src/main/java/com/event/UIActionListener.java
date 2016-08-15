package com.event;

import java.awt.event.ActionListener;

/**
 * @author Daniel
 */
public class UIActionListener implements ActionListener {

    public UIActionListener() {
    }

    public void actionPerformed(java.awt.event.ActionEvent event) {
        final com.frame.impl.Action action = com.frame.impl.Action.getByJComponent((javax.swing.JComponent) event.getSource());
        if (action != null) {
            action.process();
        }
    }

}
