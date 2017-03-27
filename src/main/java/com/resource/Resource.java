package com.resource;

import com.logging.Logger;
import org.apache.commons.lang3.SystemUtils;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Resource {

    public static final ImageIcon LAUNCH = getIcon("Play");
    public static final ImageIcon REFRESH = getIcon("Reload");
    public static final ImageIcon UPDATED = getIcon("Updated");
    public static final ImageIcon OUTDATED = getIcon("Outdated");
    public static final ImageIcon MISSING = getIcon("Missing");
    public static final ImageIcon LIST_ICON = getIcon(
            SystemUtils.IS_OS_WINDOWS ?
                    "Windows" : SystemUtils.IS_OS_MAC ?
                    "Macintosh" : SystemUtils.IS_OS_LINUX ?
                    "Linux" : SystemUtils.IS_OS_UNIX ?
                    "Unix" : SystemUtils.IS_OS_FREE_BSD ?
                    "FreeBSD" : SystemUtils.IS_OS_OPEN_BSD ?
                    "OpenBSD" : SystemUtils.IS_OS_NET_BSD ?
                    "NetBSD" : SystemUtils.IS_OS_SOLARIS ?
                    "Solaris" : SystemUtils.IS_OS_AIX ?
                    "AIX" :
                    "Java"
    );

    public static final Image BANNER = getImage(getIcon("Banner"));

    public static final Image FRAME_ICON = getImage(getIcon("Icon"));

    public static final Image TERMINAL = getImage(getIcon("Terminal"));

    private static ImageIcon getIcon(String name) {
        try {
            return new ImageIcon(Resource.class.getResource(String.format("/%s.png", name)));
        } catch (Exception ex) {
            Logger.log(Resource.class, Level.WARNING, String.format("Unable to get ImageIcon \'%s.png\'", name), ex);
            return null;
        }
    }

    private static Image getImage(ImageIcon icon) {
        try {
            return icon.getImage();
        } catch (Exception ex) {
            Logger.log(Resource.class, Level.WARNING, "Unable to get Image from Icon", ex);
            return null;
        }
    }

}
