package fr.paragoumba.threedlab.components;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.components.BlueprintPanel.PIXEL_SIZE;

public class Separator extends JComponent {

    @Override
    public Dimension getPreferredSize(){

        Dimension preferredSize = super.getMinimumSize();

        preferredSize.height = PIXEL_SIZE * 3;

        return preferredSize;

    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(BlueprintPanel.brightShadow);
        g.fillRect(2 * PIXEL_SIZE, PIXEL_SIZE, getWidth() - 4 * PIXEL_SIZE, PIXEL_SIZE);

    }
}
