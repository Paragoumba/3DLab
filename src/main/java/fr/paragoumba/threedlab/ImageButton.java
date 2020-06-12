package fr.paragoumba.threedlab;

import fr.paragoumba.threedlab.materials.TextureMaterial;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.paragoumba.threedlab.BlueprintPanel.PIXEL_SIZE;

public class ImageButton extends Button {

    public ImageButton(String text, String imagePath){

        super(text, new TextureMaterial(imagePath));

        BufferedImage bufferedImage = ResourceManager.getImage(imagePath);

        if (bufferedImage != null){

            image = bufferedImage.getScaledInstance(80, 80, Image.SCALE_DEFAULT);

        }

        this.imageHeight = this.imageWidth = 80;

        Dimension size = new Dimension(imageWidth + 4 * PIXEL_SIZE, imageHeight + 4 * PIXEL_SIZE);

        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

    }

    private Image image;
    private final int imageWidth;
    private final int imageHeight;

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        g.drawImage(image, getWidth() / 2 - imageWidth / 2,
                getHeight() / 2 - imageHeight / 2, null);

    }
}
