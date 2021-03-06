package fr.paragoumba.threedlab.components;

import fr.paragoumba.threedlab.materials.ColorMaterial;

import java.awt.*;

import static fr.paragoumba.threedlab.components.BlueprintPanel.PIXEL_SIZE;

public class ColorButton extends MaterialButton {

    public ColorButton(String text, Color color, boolean canSetMaterial){

        super(text, new ColorMaterial(color), canSetMaterial);

        this.color = color;

        Dimension size = new Dimension(80 + 4 * PIXEL_SIZE, 80 + 4 * PIXEL_SIZE);

        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

    }

    private final Color color;

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(color);
        g.fillRect(2 * PIXEL_SIZE, 2 * PIXEL_SIZE, getWidth() - 4 * PIXEL_SIZE, getHeight() - 4 * PIXEL_SIZE);

    }
}
