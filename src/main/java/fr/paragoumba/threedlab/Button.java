package fr.paragoumba.threedlab;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.BlueprintPanel.PIXEL_SIZE;

public class Button extends JButton {

    public Button(String text){

        super(text);

        setBorder(null);

        Dimension size = new Dimension(8 * PIXEL_SIZE, 8 * PIXEL_SIZE);

        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

    }

    @Override
    protected void paintComponent(Graphics g){

        g.fillRect(0, 0, getWidth(), getHeight());

    }
}
