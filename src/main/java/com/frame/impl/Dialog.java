package com.frame.impl;

import com.frame.UI;

import javax.swing.*;

/**
 * @author Daniel
 */
public class Dialog {

    public static boolean acceptedChoice(final String title, final String message) {
        return JOptionPane.showConfirmDialog(UI.getInstance(), message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION;
    }

    public static void displayMessage() {
        JOptionPane.showMessageDialog(UI.getInstance(), "The Process will now Exit.");
    }

}
