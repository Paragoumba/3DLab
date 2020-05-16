package fr.paragoumba.threedlab;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.BlueprintPanel.PIXEL_SIZE;

public class Separator extends JComponent {

    @Override
    public int getHeight(){

        return PIXEL_SIZE * 3;

    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(BlueprintPanel.brightShadow);
        g.fillRect(2 * PIXEL_SIZE, PIXEL_SIZE, getWidth() - 4 * PIXEL_SIZE, PIXEL_SIZE);

    }
}
