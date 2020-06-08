package fr.paragoumba.threedlab;

import fr.paragoumba.threedlab.materials.Material;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.BlueprintPanel.PIXEL_SIZE;

public abstract class Button extends JButton {

    public Button(String text, Material material){

        super(text);

        setToolTipText(text);

        setBorder(null);

        Dimension size = new Dimension(8 * PIXEL_SIZE, 8 * PIXEL_SIZE);

        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

        addActionListener(actionEvent -> BlueprintPanel.setCurrentMaterial(material));

    }

    @Override
    protected void paintComponent(Graphics g){}

}
