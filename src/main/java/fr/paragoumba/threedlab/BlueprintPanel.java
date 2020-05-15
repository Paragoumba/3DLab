package fr.paragoumba.threedlab;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlueprintPanel extends JPanel {

    public static final int PIXEL_SIZE = 10;
    private static final int LINE_SPACING = 50;

    public static final Color brightShadow = new Color(255, 255, 255, 84);
    public static final Color darkShadow = new Color(0, 0, 0, 114);

    public BlueprintPanel(List<Level> levels){

        super(new BorderLayout());

        add(new Menu(), BorderLayout.EAST);

        this.levels = levels;
        currentLevel = levels.get(0);

        addMouseMotionListener(new MouseAdapter(){

            @Override
            public void mouseMoved(MouseEvent e){

                super.mouseMoved(e);

                if (e.getLocationOnScreen() != null){

                    repaint();

                }
            }
        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e){

                super.mouseClicked(e);

                if (e.getButton() == MouseEvent.BUTTON1){

                    Point mousePosition = getSquareMousePosition(e.getPoint());

                    currentLevel.getLabyrinth().toggleSquare(mousePosition.x, mousePosition.y);

                    repaint();

                }
            }
        });
    }

    private final List<Level> levels;
    private Level currentLevel;

    private void fillSquare(Graphics g, int x, int y){

        g.fillRect(x, y, LINE_SPACING, LINE_SPACING);

    }

    private Point getSquareMousePosition(Point mousePosition){

        return new Point(mousePosition.x / LINE_SPACING, mousePosition.y / LINE_SPACING);

    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Labyrinth labyrinth = currentLevel.getLabyrinth();

        int width = labyrinth.getWidth() * LINE_SPACING;
        int height = labyrinth.getHeight() * LINE_SPACING;

        g.setColor(new Color(0, 109, 223));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);

        for (int i = LINE_SPACING - 1; i < Math.max(width, height); i += LINE_SPACING){

            g.drawLine(i, 0, i, height - 1);
            g.drawLine(0, i, width - 1, i);

        }

        Point mousePosition = getMousePosition();

        if (mousePosition != null){

            mousePosition = getSquareMousePosition(mousePosition);

        }

        boolean[][] grid = labyrinth.getGrid();

        for (int j = 0; j < labyrinth.getHeight(); ++j){
            for (int i = 0; i < labyrinth.getWidth(); ++i){

                if (grid[i][j]){

                    if (mousePosition == null || i != mousePosition.x || j != mousePosition.y){

                        fillSquare(g, i * LINE_SPACING, j * LINE_SPACING);

                    }

                } else {

                    if (mousePosition != null && i == mousePosition.x && j == mousePosition.y){

                        fillSquare(g, i * LINE_SPACING, j * LINE_SPACING);

                    }
                }
            }
        }
    }
}
