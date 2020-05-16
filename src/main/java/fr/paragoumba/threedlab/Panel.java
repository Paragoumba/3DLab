package fr.paragoumba.threedlab;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.BlueprintPanel.PIXEL_SIZE;

public class Panel extends JPanel {

    public Panel(){

        setBorder(BorderFactory.createEmptyBorder(PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    }

    @Override
    protected void paintComponent(Graphics g){}

}
