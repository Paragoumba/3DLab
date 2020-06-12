package fr.paragoumba.threedlab;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.BlueprintPanel.PIXEL_SIZE;

public class RadioButton extends JRadioButton {

    @Override
    public Dimension getMaximumSize(){

        Dimension preferredSize = super.getMinimumSize();

        preferredSize.width = PIXEL_SIZE * 6;
        preferredSize.height = PIXEL_SIZE * 6;

        return preferredSize;

    }

    @Override
    public Dimension getPreferredSize(){

        Dimension preferredSize = super.getMinimumSize();

        preferredSize.width = PIXEL_SIZE * 6;
        preferredSize.height = PIXEL_SIZE * 6;

        return preferredSize;

    }

    @Override
    public Dimension getMinimumSize(){

        Dimension preferredSize = super.getMinimumSize();

        preferredSize.width = PIXEL_SIZE * 6;
        preferredSize.height = PIXEL_SIZE * 6;

        return preferredSize;

    }

    @Override
    protected void paintComponent(Graphics g){

        int width = getWidth();
        int height = getHeight();

        if (isSelected()){

            g.setColor(new Color(0, 72, 151));
            g.fillRect(PIXEL_SIZE, 0, width - 2 * PIXEL_SIZE, height);
            g.fillRect(0, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);
            g.fillRect(width - PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);

            g.setColor(BlueprintPanel.darkShadow);
            g.fillRect(PIXEL_SIZE, 0, width - 2 * PIXEL_SIZE, PIXEL_SIZE);
            g.fillRect(PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
            g.fillRect(0, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);

            g.setColor(BlueprintPanel.brightShadow);

        } else {

            g.setColor(BlueprintPanel.bgColor);
            g.fillRect(PIXEL_SIZE, 0, width - 2 * PIXEL_SIZE, height);
            g.fillRect(0, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);
            g.fillRect(width - PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);

            g.setColor(BlueprintPanel.brightShadow);
            g.fillRect(PIXEL_SIZE, 0, width - 2 * PIXEL_SIZE, PIXEL_SIZE);
            g.fillRect(PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
            g.fillRect(0, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);

            g.setColor(BlueprintPanel.darkShadow);

        }

        g.fillRect(PIXEL_SIZE, height - PIXEL_SIZE, width - 2 * PIXEL_SIZE, PIXEL_SIZE);
        g.fillRect(width - PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);
        g.fillRect(width - 2 * PIXEL_SIZE, height - 2 * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);

        Icon icon = getIcon();

        if (icon != null){

            icon.paintIcon(this, g,
                    (getWidth() - icon.getIconWidth()) / 2, (getHeight() - icon.getIconHeight()) / 2);

        }
    }
}
