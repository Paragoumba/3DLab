package fr.paragoumba.threedlab.components;

import fr.paragoumba.threedlab.materials.Material;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static fr.paragoumba.threedlab.components.BlueprintPanel.PIXEL_SIZE;

public abstract class MaterialButton extends JButton {

    public MaterialButton(String text, Material material, boolean canSetMaterial){

        super(text);

        setToolTipText(text);

        setBorder(null);

        Dimension size = new Dimension(8 * PIXEL_SIZE, 8 * PIXEL_SIZE);

        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

        if (canSetMaterial){

            addActionListener(actionEvent -> BlueprintPanel.setCurrentMaterial(material));

        }
    }

    @Override
    public JToolTip createToolTip(){

        ToolTip toolTip = new ToolTip();

        toolTip.setToolTipText(getText());

        return toolTip;

    }

    @Override
    public Point getToolTipLocation(MouseEvent event){

        return new Point(0, getHeight() / 2);

    }

    @Override
    protected void paintComponent(Graphics g){}

}
