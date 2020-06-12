package fr.paragoumba.threedlab.components;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.components.BlueprintPanel.PIXEL_SIZE;

public class ToolTip extends JToolTip {

    public ToolTip(){

        setBorder(null);

        Dimension size = new Dimension(PIXEL_SIZE * 10, PIXEL_SIZE * 6);

        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

    }

    @Override
    protected void paintComponent(Graphics g){

        int width = getWidth();
        int height = getHeight();
        
        g.setColor(BlueprintPanel.bgColor);
        g.fillRect(0, PIXEL_SIZE, width, height - 2 * PIXEL_SIZE);
        g.fillRect(PIXEL_SIZE, 0, width - 2 * PIXEL_SIZE, PIXEL_SIZE);
        g.fillRect(PIXEL_SIZE, height - PIXEL_SIZE, width - 2 * PIXEL_SIZE, PIXEL_SIZE);

        g.setColor(BlueprintPanel.brightShadow);
        g.fillRect(PIXEL_SIZE, 0, width - 2 * PIXEL_SIZE, PIXEL_SIZE);
        g.fillRect(PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
        g.fillRect(0, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);

        g.setColor(BlueprintPanel.darkShadow);
        g.fillRect(PIXEL_SIZE, height - PIXEL_SIZE, width - 2 * PIXEL_SIZE, PIXEL_SIZE);
        g.fillRect(width - PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);
        g.fillRect(width - 2 * PIXEL_SIZE, height - 2 * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);

        g.setFont(new Font(BlueprintPanel.font.getName(), Font.PLAIN, 12));

        String text = getTipText();
        int stringWidth = g.getFontMetrics().stringWidth(text);
        int stringHeight = g.getFontMetrics().getHeight();

        g.setColor(Color.WHITE);
        g.drawString(text, (width - stringWidth) / 2, (height + stringHeight) / 2);

    }
}
