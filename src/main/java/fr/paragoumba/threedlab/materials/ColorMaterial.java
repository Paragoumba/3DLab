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

        Rectangle bounds = g.getClipBounds();

        g.setColor(color);
        g.fillRect(bounds.x, bounds.y, bounds.width + 1, bounds.height + 1);

    }
}
