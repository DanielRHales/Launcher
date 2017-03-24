package com.frame;

import com.config.Constants;
import com.event.UIActionListener;
import com.resource.Resource;
import com.version.Program;
import com.version.VersionHandler;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;

/**
 * @author Daniel
 */
@SuppressWarnings("unchecked")
public class UI extends JFrame {

    private final JButton refreshButton = new JButton();
    private final JButton launchButton = new JButton();
    private final JComboBox comboBox = new JComboBox(new DefaultComboBoxModel(new String[]{"No Programs are Currently Available"}));
    private final Dimension dimension = new Dimension(360, 65);
    private final UIActionListener actionListener = new UIActionListener();

    private UI() {
        initComponents();
    }

    public static UI getInstance() {
        return InstanceHolder.instance != null ? InstanceHolder.instance : (InstanceHolder.instance = new UI());
    }

    private void initComponents() {
        final JPanel panel = new javax.swing.JPanel() {
            protected void paintComponent(java.awt.Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(Resource.BANNER, getWidth() - Resource.BANNER.getWidth(null), getHeight() - Resource.BANNER.getHeight(null), null);
            }
        };
        final JLabel label = new JLabel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(Constants.TITLE);
        setAlwaysOnTop(true);
        setIconImage(Resource.FRAME_ICON);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setResizable(false);
        refreshButton.setToolTipText("Refresh Program List");
        refreshButton.setIcon(Resource.REFRESH);
        refreshButton.addActionListener(actionListener);
        launchButton.setToolTipText("Invoke Selected Program");
        launchButton.setIcon(Resource.LAUNCH);
        launchButton.addActionListener(actionListener);
        comboBox.setToolTipText("Program List (0 Objects)");
        comboBox.setFont(new Font("Dialog", 1, 11));
        comboBox.setRenderer(new javax.swing.ListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object value, int index, boolean selected, boolean focused) {
                final javax.swing.JLabel label = new javax.swing.JLabel();
                final Program program = !VersionHandler.getInstance().isEmpty() && index != -1 ? VersionHandler.getInstance().getVersion(index) : null;
                label.setText(program != null ? program.getDescription() : value.toString());
                label.setIcon(program != null ? program.updateRequired() ? program.getFile().exists() ? Resource.OUTDATED : Resource.MISSING : Resource.UPDATED : Resource.LIST_ICON);
                label.setForeground(program != null ? program.updateRequired() ? program.getFile().exists() ? Color.YELLOW : Color.RED : selected ? Color.GREEN : Color.YELLOW : Color.WHITE);
                return label;
            }
        });
        comboBox.addActionListener(actionListener);
        comboBox.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
                VersionHandler.getInstance().refresh();
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {

            }

            public void popupMenuCanceled(PopupMenuEvent event) {

            }
        });
        label.setFont(new Font("Dialog", 0, 3));
        label.setText(String.format("Copyright © 2016 By the Developers of %s All Rights Reserved", Constants.SERVER_NAME));
        final GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(launchButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(comboBox, 0, 278, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addComponent(label).addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(refreshButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(launchButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(comboBox)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE).addComponent(label)));
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JButton getLaunchButton() {
        return launchButton;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    private static class InstanceHolder {
        private static UI instance;
    }

}
