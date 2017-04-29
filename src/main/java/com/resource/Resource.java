package com.resource;

import com.logging.Logger;
import org.apache.commons.lang3.SystemUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static final Image TERMINAL = getImage(getIcon("Terminal"));

    public static ArrayList<Image> ICONS_LIST = new ArrayList<Image>(Arrays.asList(getResourceImage("Icon", "png", new Dimension(16, 16)), getResourceImage("Icon", "png", new Dimension(32, 32))));

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

    public static Image getResourceImage(String name, String extension, Dimension dimension) {
        return getResourceImage(String.format("%s_%dx%d", name, dimension.width, dimension.height), extension);
    }

    public static Image getResourceImage(String name, String extension) {
        return getResourceImage(String.format("%s.%s", name, extension));
    }

    public static Image getResourceImage(String name) {
        try {
            return ImageIO.read(Resource.class.getResource(String.format("/%s", name)));
        } catch (Exception ex) {
            return null;
        }
    }

    public static Image getResizedImage(Image image, int width, int height) {
        BufferedImage value = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = value.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();
        return value;
    }

    public static Image getResizedImage(Image image, int width, int height, int originalWidth, int originalHeight, float alpha) {
        final BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = resized.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        graphics.drawImage(image, 0, 0, originalWidth, originalHeight, null);
        graphics.dispose();
        return resized;
    }

    public static Image getFilteredImage(Image image, final Color color) {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new RGBImageFilter() {
            public final int filterRGB(int x, int y, int rgb) {
                return ((rgb | 0xFF000000) != (color.getRGB() | 0xFF000000)) ? rgb : 0x00FFFFFF & rgb;
            }
        }));
    }

    public static Image getTransparentImage(BufferedImage image, float transparency) {
        BufferedImage transparentImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) transparentImage.getGraphics();
        graphics.setComposite(AlphaComposite.SrcOver.derive(transparency));
        graphics.drawImage(image, 0, 0, null);
        return transparentImage;
    }

}
