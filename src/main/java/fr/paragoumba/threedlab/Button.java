package fr.paragoumba.threedlab;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.BlueprintPanel.PIXEL_SIZE;

public class Button extends JButton {

    @Override
    protected void paintComponent(Graphics g){

        int width = getWidth();
        int height = getHeight();

        g.setColor(isPressed ? bgColorDark : BlueprintPanel.bgColor);
        g.fillRect(PIXEL_SIZE, 0, width - 2 * PIXEL_SIZE, height);
        g.fillRect(0, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);
        g.fillRect(width - PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);

        g.setColor(isPressed ? BlueprintPanel.darkShadow : BlueprintPanel.brightShadow);
        g.fillRect(PIXEL_SIZE, 0, width - 2 * PIXEL_SIZE, PIXEL_SIZE);
        g.fillRect(PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
        g.fillRect(0, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);

        g.setColor(isPressed ? BlueprintPanel.brightShadow : BlueprintPanel.darkShadow);
        g.fillRect(PIXEL_SIZE, height - PIXEL_SIZE, width - 2 * PIXEL_SIZE, PIXEL_SIZE);
        g.fillRect(width - PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, height - 2 * PIXEL_SIZE);
        g.fillRect(width - 2 * PIXEL_SIZE, height - 2 * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);

        String s = String.valueOf(c);

        g.setFont(new Font(BlueprintPanel.font.getName(), Font.PLAIN, 30));
        int stringWidth = g.getFontMetrics().stringWidth(s);
        int stringHeight = g.getFontMetrics().getHeight();

        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(c), (width - stringWidth) / 2, (height + stringHeight) / 2);

    }
}
