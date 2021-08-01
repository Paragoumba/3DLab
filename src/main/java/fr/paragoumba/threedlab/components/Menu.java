package fr.paragoumba.threedlab.components;

import fr.paragoumba.threedlab.Exporter;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.components.BlueprintPanel.PIXEL_SIZE;

public class Menu extends Panel {

    public Menu(){

        MaterialButton save = new ImageButton("Save", "/imgs/save.png", false);

        save.addActionListener(actionEvent -> {

            Container topLevelAncestor = getTopLevelAncestor();

            if (topLevelAncestor instanceof JFrame){

                Container parent = ((JFrame) topLevelAncestor).getContentPane();

                if (parent instanceof BlueprintPanel){

                    Exporter.export(((BlueprintPanel) parent).getLevels());

                }
            }
        });

        add(Box.createVerticalGlue());
        add(save);
        add(Box.createVerticalGlue());
        add(new Separator());
        add(Box.createVerticalGlue());
        add(new ColorButton("White", Color.WHITE, true));
        add(Box.createVerticalGlue());
        add(new ImageButton("Brick", "/imgs/brick.png", true));
        add(Box.createVerticalGlue());
        add(new ImageButton("Stone", "/imgs/stone.png", true));
        add(Box.createVerticalGlue());
        add(new ColorButton("Black", Color.BLACK, true));
        add(Box.createVerticalGlue());
        add(new ColorButton("Green", Color.GREEN, true));
        add(Box.createVerticalGlue());
        add(new ColorButton("Blue", Color.BLUE, true));
        add(Box.createVerticalGlue());
        add(new ColorButton("Red", Color.RED, true));
        add(Box.createVerticalGlue());

    }

    @Override
    protected void paintComponent(Graphics g){

        int width = getWidth();
        int height = getHeight();

        g.setColor(BlueprintPanel.bgColor);
        g.fillRect(PIXEL_SIZE, 0, width - 2 * PIXEL_SIZE, height);
        g.fillRect(0, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);
        g.fillRect(width - PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);

        g.setColor(BlueprintPanel.brightShadow);
        g.fillRect(PIXEL_SIZE, 0, width - 2 * PIXEL_SIZE, PIXEL_SIZE);
        g.fillRect(PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
        g.fillRect(0, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);

        g.setColor(BlueprintPanel.darkShadow);
        g.fillRect(PIXEL_SIZE, height - PIXEL_SIZE, width - 2 * PIXEL_SIZE, PIXEL_SIZE);
        g.fillRect(width - PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);
        g.fillRect(width - 2 * PIXEL_SIZE, height - 2 * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);

    }
}
