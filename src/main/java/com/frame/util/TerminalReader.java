package com.frame.util;

import com.logging.Logger;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * @author Daniel
 */
class TerminalReader {

    private final DateFormat format = new SimpleDateFormat("d/MM/y h:m:s.S a zzzz");

    private int appended = 0;
    private boolean applyDate = false;

    private final JTextArea area;
    private final BufferedReader reader;

    TerminalReader(JTextArea area, BufferedReader reader) {
        this.area = area;
        this.reader = reader;
        ((DefaultCaret) area.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        addAction();
        initialize();
    }

    private void initialize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        area.append(String.format("%s%s\n", applyDate ? String.format("[%s]:", format.format(new Date())) : "", line));
                        appended += 1;
                        if (appended >= 500) {
                            clearArea();
                        }
                    }
                } catch (IOException ex) {
                    Logger.log(TerminalReader.class, Level.WARNING, "Error appending from Stream.", ex);
                }
            }
        }).start();
    }

    private void addAction() {
        final JPopupMenu popupMenu = new JPopupMenu();
        final JMenuItem clear = new JMenuItem("Clear");
        final JMenuItem date = new JMenuItem("Date");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                clearArea();
            }
        });
        date.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                applyDate = !applyDate;
            }
        });
        popupMenu.add(clear);
        popupMenu.add(date);
        area.setComponentPopupMenu(popupMenu);
    }

    private void clearArea() {
        area.setText("");
        appended = 0;
    }

}
