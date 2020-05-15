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

            image = ImageIO.read(ImageButton.class.getResourceAsStream(imagePath));

        } catch (IOException e){

            e.printStackTrace();

        }

        this.setPreferredSize(new Dimension(image.getWidth() + 4 * PIXEL_SIZE, image.getHeight() + 4 * PIXEL_SIZE));

    }

    private BufferedImage image;

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        g.drawImage(image, this.getWidth() / 2 - image.getWidth() / 2,
                this.getHeight() / 2 - image.getHeight() / 2, null);

    }
}
