package com;

import com.frame.UI;
import org.apache.commons.lang3.SystemUtils;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.FlatBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.MatteGradientPainter;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.skin.RavenSkin;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;

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
        SubstanceLookAndFeel.setSkin(SystemUtils.IS_OS_MAC ? new CremeSkin() : new RavenSkin());
        SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBinaryWatermark());
        SubstanceLookAndFeel.setCurrentBorderPainter(new FlatBorderPainter());
        SubstanceLookAndFeel.setCurrentGradientPainter(new MatteGradientPainter());
        SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());
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
