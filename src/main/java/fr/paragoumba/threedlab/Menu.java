package fr.paragoumba.threedlab;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.BlueprintPanel.PIXEL_SIZE;

public class Menu extends Panel {

    public Menu(){

        Button save = new ImageButton("Save", "/imgs/save.png");

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
        add(new ColorButton("White", Color.WHITE));
        add(Box.createVerticalGlue());
        add(new ImageButton("Brick", "/imgs/brick.png"));
        add(Box.createVerticalGlue());
        add(new ImageButton("Stone", "/imgs/stone.png"));
        add(Box.createVerticalGlue());
        add(new ColorButton("Black", Color.BLACK));
        add(Box.createVerticalGlue());
        add(new ColorButton("Green", Color.GREEN));
        add(Box.createVerticalGlue());
        add(new ColorButton("Blue", Color.BLUE));
        add(Box.createVerticalGlue());
        add(new ColorButton("Red", Color.RED));
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
