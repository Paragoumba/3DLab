package fr.paragoumba.threedlab;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static fr.paragoumba.threedlab.BlueprintPanel.PIXEL_SIZE;

public class ImageButton extends Button {

    public ImageButton(String text, String imagePath){

        super(text);

        try {

            image = ImageIO.read(ImageButton.class.getResourceAsStream(imagePath))
                    .getScaledInstance(80, 80, Image.SCALE_DEFAULT);

            this.imageHeight = this.imageWidth = 80;

        } catch (IOException e){

            e.printStackTrace();

        }

        Dimension size = new Dimension(imageWidth + 4 * PIXEL_SIZE, imageHeight + 4 * PIXEL_SIZE);

        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

    }

    private Image image;
    private int imageWidth;
    private int imageHeight;

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        g.drawImage(image, getWidth() / 2 - imageWidth / 2,
                getHeight() / 2 - imageHeight / 2, null);

    }
}
