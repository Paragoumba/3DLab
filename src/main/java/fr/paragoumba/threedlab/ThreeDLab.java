package fr.paragoumba.threedlab;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ThreeDLab {

    public static void main(String[] args){

        Labyrinth labyrinth = new Labyrinth(10, 10);
        Level level = new Level(labyrinth);
        ArrayList<Level> levels = new ArrayList<>();

        levels.add(level);

        JFrame window = new JFrame("3DLab - Labyrinth Generator");
        JPanel mainPanel = new BlueprintPanel(levels);

        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        window.pack();
        window.setVisible(true);

        Exporter.exportLab(labyrinth);

    }
}
