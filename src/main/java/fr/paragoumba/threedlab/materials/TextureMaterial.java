package fr.paragoumba.threedlab.materials;

import fr.paragoumba.threedlab.BlueprintPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TextureMaterial extends Material {

    public TextureMaterial(String path){

        this.path = path;

        try {

            image = ImageIO.read(BlueprintPanel.class.getResourceAsStream(path));

        } catch (IOException e){

            e.printStackTrace();

        }
    }

    private final String path;
    private BufferedImage image;

    @Override
    public String toString(){

        String subStr = path.substring(path.lastIndexOf('/') + 1);

        return subStr.substring(0, subStr.lastIndexOf('.'));

    }

    @Override
    public void drawMaterial(Graphics g){

        g.drawImage(image, 0, 0, g.getClipBounds().width + 1,  g.getClipBounds().height + 1, null);

    }
}
