package com.event;

import javax.swing.*;
import java.awt.event.MouseAdapter;

/**
 * @author Daniel
 */
public class UIMouseAdapter extends MouseAdapter {

    private final JComboBox comboBox;

    public UIMouseAdapter(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public void mousePressed(java.awt.event.MouseEvent event) {
        if (comboBox.isEnabled() && (event.getSource() instanceof JButton != comboBox.isPopupVisible())) {
            for (com.version.Version version : com.version.VersionManager.getVersions()) {
                version.refresh();
            }
        }
    }


}
