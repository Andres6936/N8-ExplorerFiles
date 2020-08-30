package edu.explorer.interfaz;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class IconsUtility {

    private final static ClassLoader loader = IconsUtility.class.getClassLoader();

    public static BufferedImage getIcon(final String path) throws IOException {
        return ImageIO.read(new File(Objects.requireNonNull(loader.getResource(path)).getFile()));
    }

    public static BufferedImage scaleImage(final BufferedImage img, final int width, final int height) {
        Image resize = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = buffer.createGraphics();

        graphics2D.drawImage(resize, 0, 0, null);
        graphics2D.dispose();

        return buffer;
    }

    public static BufferedImage colorizeImage(final BufferedImage img, Color color) {
        BufferedImage buffer = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = buffer.createGraphics();
        graphics2D.drawImage(img, 0, 0, null);
        graphics2D.setComposite(AlphaComposite.SrcAtop);
        graphics2D.setColor(color);
        graphics2D.fillRect(0, 0, img.getWidth(), img.getHeight());
        graphics2D.dispose();

        return buffer;
    }
}
