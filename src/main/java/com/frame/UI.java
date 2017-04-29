package com.frame;

import com.config.Configuration;
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

    private final Dimension dimension = new Dimension(360, 75);
    private final UIActionListener actionListener = new UIActionListener();
    
    private final JComboBox comboBox = new JComboBox(new DefaultComboBoxModel(new String[]{"No Programs are Currently Available"}));
    private final JCheckBox disposeCheckbox = new JCheckBox();
    private final JButton launchButton = new JButton();
    private final JButton refreshButton = new JButton();
    private final JCheckBox terminalCheckbox = new JCheckBox();
    
    private UI() {
        initialize();
    }

    private void initialize() {
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Constants.TITLE);
        setAlwaysOnTop(true);
        setResizable(false);
        setIconImages(Resource.ICONS_LIST);
        setMaximumSize(dimension);
        setMinimumSize(dimension);

        refreshButton.setIcon(Resource.REFRESH);
        refreshButton.addActionListener(actionListener);
        refreshButton.setToolTipText("Refresh Programs List");

        launchButton.setIcon(Resource.LAUNCH);
        launchButton.addActionListener(actionListener);
        launchButton.setToolTipText("Invoke Program");

        comboBox.setFont(new Font("Dialog", Font.BOLD, 11));
        comboBox.setRenderer(new javax.swing.ListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object value, int index, boolean selected, boolean focused) {
                final Program program = !VersionHandler.getInstance().isEmpty() && index != -1 ? VersionHandler.getInstance().getVersion(index) : null;
                final JLabel label = new JLabel(program != null ? program.getDescription() : value.toString(), program != null ? program.updateRequired() ? program.getFile().exists() ? Resource.OUTDATED : Resource.MISSING : Resource.UPDATED : Resource.LIST_ICON, SwingConstants.LEADING);
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
        comboBox.setToolTipText("Programs (0)");

        terminalCheckbox.setFont(new Font("Dialog", Font.BOLD, 11));
        terminalCheckbox.setSelected(Configuration.terminal);
        terminalCheckbox.setText("Terminal");
        terminalCheckbox.setToolTipText("Launch a Terminal with the Selected Program");
        terminalCheckbox.addActionListener(actionListener);

        disposeCheckbox.setFont(new Font("Dialog", Font.BOLD, 11));
        disposeCheckbox.setSelected(Configuration.dispose);
        disposeCheckbox.setText("Dispose");
        disposeCheckbox.setToolTipText("Disposes the Launcher after Invocation");
        disposeCheckbox.addActionListener(actionListener);

        label.setFont(new Font("Dialog", Font.BOLD, 3));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(String.format("Copyright © 2016 By the Developers of %s All Rights Reserved", Constants.SERVER_NAME));
        label.setToolTipText(label.getText());

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelLayout.createSequentialGroup().addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(launchButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(panelLayout.createSequentialGroup().addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(terminalCheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(disposeCheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(label, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE).addContainerGap()));
        panelLayout.setVerticalGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelLayout.createSequentialGroup().addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(refreshButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(launchButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(comboBox)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(terminalCheckbox).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 2, Short.MAX_VALUE).addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(disposeCheckbox).addComponent(label))));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        pack();
        setLocationRelativeTo(null);
    }

    public static UI getInstance() {
        return InstanceHolder.instance != null ? InstanceHolder.instance : (InstanceHolder.instance = new UI());
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

    public JCheckBox getTerminalCheckbox() {
        return terminalCheckbox;
    }

    public JCheckBox getDisposeCheckbox() {
        return disposeCheckbox;
    }

    private static class InstanceHolder {
        private static UI instance;
    }
    
}