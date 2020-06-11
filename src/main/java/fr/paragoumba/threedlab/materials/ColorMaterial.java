package fr.paragoumba.threedlab.materials;

import java.awt.*;

public class ColorMaterial extends Material {

    public ColorMaterial(Color color){

        this.color = color;

    }

    private final Color color;

    @Override
    public String toString(){

        return String.format("#%06x", color.getRGB() & 0x00FFFFFF).toUpperCase();

    }

    @Override
    public void drawMaterial(Graphics g){

        g.setColor(color);
        g.fillRect(0, 0, g.getClipBounds().width + 1, g.getClipBounds().height + 1);

    }
}
