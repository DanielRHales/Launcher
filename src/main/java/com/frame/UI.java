package com.frame;

/**
 * @author Daniel
 */
@SuppressWarnings("unchecked")
public class UI extends javax.swing.JFrame {

    private final javax.swing.DefaultComboBoxModel listModel = new javax.swing.DefaultComboBoxModel(new String[]{"No Clients are Currently Available"});
    private final javax.swing.JButton refreshButton = new javax.swing.JButton();
    private final javax.swing.JButton launchButton = new javax.swing.JButton();
    private final javax.swing.JComboBox comboBox = new javax.swing.JComboBox(listModel);
    private final java.awt.Dimension dimension = new java.awt.Dimension(360, 65);
    private final com.event.UIActionListener actionListener = new com.event.UIActionListener();

    private UI() {
        initComponents();
    }

    public static UI getInstance() {
        return InstanceHolder.instance != null ? InstanceHolder.instance : (InstanceHolder.instance = new UI());
    }

    private void initComponents() {
        final javax.swing.JPanel panel = new javax.swing.JPanel() {
            protected void paintComponent(java.awt.Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(com.resource.Resource.BANNER, 0, 0, null);
            }
        };
        final javax.swing.JLabel label = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(com.config.Constants.TITLE);
        setAlwaysOnTop(true);
        setIconImage(com.resource.Resource.FRAME_ICON);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setResizable(false);
        refreshButton.setIcon(com.resource.Resource.REFRESH);
        refreshButton.addActionListener(actionListener);
        launchButton.setIcon(com.resource.Resource.LAUNCH);
        launchButton.addActionListener(actionListener);
        comboBox.setFont(new java.awt.Font("Dialog", 1, 11));
        comboBox.setRenderer(new javax.swing.ListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object value, int index, boolean selected, boolean focused) {
                javax.swing.JLabel label = new javax.swing.JLabel();
                final com.version.Version version = index != -1 ? com.version.VersionManager.getVersion(index) : null;
                label.setText(version != null ? version.getDescription() : value.toString());
                label.setIcon(version != null ? version.updateRequired() ? version.getFile().exists() ? com.resource.Resource.OUTDATED : com.resource.Resource.MISSING : com.resource.Resource.UPDATED : com.resource.Resource.LIST_ICON);
                label.setForeground(version != null ? version.updateRequired() ? version.getFile().exists() ? java.awt.Color.YELLOW : java.awt.Color.RED : selected ? java.awt.Color.GREEN : java.awt.Color.YELLOW : java.awt.Color.WHITE);
                return label;
            }
        });
        comboBox.addActionListener(actionListener);
        comboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent event) {
                for (com.version.Version version : com.version.VersionManager.getVersions()) {
                    version.refresh();
                }
            }

            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent event) {

            }

            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent event) {

            }
        });
        label.setFont(new java.awt.Font("Dialog", 0, 3));
        label.setText(String.format("Copyright © 2016 By the Developers of %s All Rights Reserved", com.config.Constants.SERVER_NAME));
        final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(launchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(comboBox, 0, 278, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addComponent(label).addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(launchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(comboBox)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE).addComponent(label)));
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    public javax.swing.JButton getRefreshButton() {
        return refreshButton;
    }

    public javax.swing.JButton getLaunchButton() {
        return launchButton;
    }

    public javax.swing.JComboBox getComboBox() {
        return comboBox;
    }

    private static class InstanceHolder {
        private static UI instance;
    }

}
