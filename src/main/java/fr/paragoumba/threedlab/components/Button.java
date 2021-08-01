package fr.paragoumba.threedlab.components;

import javax.swing.*;
import java.awt.*;

import static fr.paragoumba.threedlab.components.BlueprintPanel.PIXEL_SIZE;
import static fr.paragoumba.threedlab.components.BlueprintPanel.bgColorDark;

public class Button extends JButton {

    public Button(char c){

        this.c = c;

        setBorder(null);

    }

    protected final char c;

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

        boolean isPressed = getModel().isPressed();

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
        g.drawString(s, (width - stringWidth) / 2, (height + stringHeight) / 2);

    }
}
