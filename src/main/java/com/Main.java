package com;

import com.frame.UI;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.GlassInnerBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.GlassGradientPainter;
import org.jvnet.substance.skin.EbonyHighContrastSkin;
import org.jvnet.substance.theme.SubstanceEbonyTheme;
import org.jvnet.substance.watermark.SubstanceKatakanaWatermark;

import javax.swing.*;
import java.awt.*;

/**
 * @author Daniel
 */
public class Main {

    private Main() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        JPopupMenu.setDefaultLightWeightPopupEnabled(true);
        SubstanceLookAndFeel.setSkin(new EbonyHighContrastSkin());
        SubstanceLookAndFeel.setCurrentTheme(new SubstanceEbonyTheme());
        SubstanceLookAndFeel.setCurrentWatermark(new SubstanceKatakanaWatermark());
        SubstanceLookAndFeel.setCurrentBorderPainter(new GlassInnerBorderPainter());
        SubstanceLookAndFeel.setCurrentGradientPainter(new GlassGradientPainter());
        SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
        initialize();
    }

    public static void main(String... argv) {
        new Main();
    }

    private void initialize() {
        EventQueue.invokeLater(
                new SwingWorker() {
                    protected Void doInBackground() throws Exception {
                        UI.getInstance().getRefreshButton().doClick();
                        return null;
                    }

                    protected void done() {
                        UI.getInstance().setVisible(true);
                    }
                }
        );
    }

}
