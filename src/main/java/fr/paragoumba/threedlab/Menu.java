package fr.paragoumba.threedlab;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.BlueprintPanel.PIXEL_SIZE;

public class Menu extends JPanel {

    public Menu(){

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        Button save = new ImageButton("Save", "/save.png");

        save.addActionListener(actionEvent -> {

            System.out.println("Save");

        });
        add(save);

        add(new Separator());
        add(new Button("Texture 1"));
        add(new Button("Texture 2"));
        add(new Button("Texture 3"));
        add(new Button("Texture 4"));
        add(new Button("Texture 5"));

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
