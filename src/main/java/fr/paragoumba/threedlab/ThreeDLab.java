package fr.paragoumba.threedlab;

import fr.paragoumba.threedlab.components.BlueprintPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThreeDLab {

    public static void main(String[] args) throws IOException {

        List<Level> levels;

        if (Exporter.dataFile.exists()){

            levels = Importer.importData(Exporter.dataFile);

        } else {

            Labyrinth labyrinth = new Labyrinth(21, 21);
            Level level = new Level("Let's start easy", labyrinth);
            levels = new ArrayList<>();

            levels.add(level);

        }

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
