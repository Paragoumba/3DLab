package fr.paragoumba.threedlab;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ThreeDLab {

    public static void main(String[] args) throws IOException {

        Labyrinth labyrinth = new Labyrinth(10, 10);
        Level level = new Level(labyrinth);
        ArrayList<Level> levels = new ArrayList<>();

        levels.add(level);

        JFrame window = new JFrame("3DLab - Labyrinth Generator");
        BlueprintPanel mainPanel = new BlueprintPanel(levels);

        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        window.pack();
        mainPanel.setCurrentLevel(0);
        window.setIconImage(ImageIO.read(ThreeDLab.class.getResourceAsStream("/imgs/3dlab-icon.png")));
        window.setVisible(true);

    }
}
