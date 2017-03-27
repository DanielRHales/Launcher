package com.frame.util;

import com.resource.Resource;
import com.version.Program;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Daniel
 */
public class Terminal extends javax.swing.JFrame {

    private final Program program;
    private final BufferedReader inputReader;
    private final BufferedReader errorReader;

    public Terminal(Program program, InputStream inputStream, InputStream errorStream) {
        this.program = program;
        this.inputReader = new BufferedReader(new InputStreamReader(inputStream));
        this.errorReader = new BufferedReader(new InputStreamReader(errorStream));
        initialize();
    }

    private void initialize() {

        final JTabbedPane tabbedPane = new JTabbedPane();
        final JScrollPane inputScroll = new JScrollPane();
        final JTextArea inputArea = new JTextArea();
        final JScrollPane errorScroll = new JScrollPane();
        final JTextArea errorArea = new JTextArea();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(String.format("%s Terminal", program.getFile().getName()));
        setIconImage(Resource.TERMINAL);

        inputArea.setEditable(false);
        inputArea.setColumns(20);
        inputArea.setLineWrap(true);
        inputArea.setRows(5);
        inputArea.setInheritsPopupMenu(true);
        inputScroll.setViewportView(inputArea);

        tabbedPane.addTab("Input", inputScroll);

        errorArea.setEditable(false);
        errorArea.setColumns(20);
        errorArea.setLineWrap(true);
        errorArea.setRows(5);
        errorArea.setInheritsPopupMenu(true);
        errorScroll.setViewportView(errorArea);

        tabbedPane.addTab("Error", errorScroll);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        pack();
        new TerminalReader(inputArea, inputReader);
        new TerminalReader(errorArea, errorReader);
    }

}
