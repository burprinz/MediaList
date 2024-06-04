package de.luiskun.manager.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileHadler {

    public static BufferedImage SetupImage(String imagePath, int width, int heigth) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
            image = ScaleImage(image, width, heigth);
        } catch (IOException e) {
            System.out.println("ERROR LOADING IMAGE. PATH: " + imagePath + ".png");
            e.printStackTrace();
        }
        return image;
    }
    private static BufferedImage ScaleImage(BufferedImage original, int width, int heigth) {
        BufferedImage scaledImage = new BufferedImage(width, heigth, 2);
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, heigth, null);
        g2.dispose();
        return scaledImage;
    }

}
