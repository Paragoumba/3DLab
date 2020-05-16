package fr.paragoumba.threedlab;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlueprintPanel extends JPanel {

    public static final int PIXEL_SIZE = 10;
    private static final int[] SPACINGS = new int[]{10, 15, 20, 30, 50, 75, 100};
    private static int LINE_SPACING = 2;

    public static final Color bgColor = new Color(0, 109, 223);
    public static final Color brightShadow = new Color(255, 255, 255, 20);
    public static final Color darkShadow = new Color(0, 0, 0, 50);

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

                    Point mousePosition = e.getPoint();
                    Labyrinth labyrinth = currentLevel.getLabyrinth();

                    int labWidth = labyrinth.getWidth() * SPACINGS[LINE_SPACING];
                    int labHeight = labyrinth.getHeight() * SPACINGS[LINE_SPACING];

                    if (mousePosition.x >= offset.x && mousePosition.x < offset.x + labWidth &&
                            mousePosition.y >= offset.y && mousePosition.y < offset.y + labHeight){

                        dragStart = null;

                        Point mouseSquarePosition = getSquareMousePosition(mousePosition);

                        currentLevel.getLabyrinth().toggleSquare(mouseSquarePosition.x, mouseSquarePosition.y);

                        repaint();

                    } else {

                        dragStart = mousePosition;

                    }
                }
            }
        });
    }

    private final List<Level> levels;
    private Level currentLevel;

    private void fillSquare(Graphics g, int x, int y){

        g.fillRect(x, y, SPACINGS[LINE_SPACING], SPACINGS[LINE_SPACING]);

    }

    private Point getSquareMousePosition(Point mousePosition){

        return new Point((int) Math.floor((float)(mousePosition.x - offset.x) / SPACINGS[LINE_SPACING]),
                (int) Math.floor((float)(mousePosition.y - offset.y) / SPACINGS[LINE_SPACING]));

    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);

        Labyrinth labyrinth = currentLevel.getLabyrinth();

        int labWidth = labyrinth.getWidth() * SPACINGS[LINE_SPACING];
        int labHeight = labyrinth.getHeight() * SPACINGS[LINE_SPACING];

        for (int i = 0; i <= labWidth; i += SPACINGS[LINE_SPACING]){

            g.drawLine(offset.x + i, offset.y, offset.x + i, offset.y + labHeight);

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

                        fillSquare(g, offset.x + i * SPACINGS[LINE_SPACING], offset.y + j * SPACINGS[LINE_SPACING]);

                    }

                } else {

                    if (mousePosition != null && i == mousePosition.x && j == mousePosition.y){

                        fillSquare(g, offset.x + i * SPACINGS[LINE_SPACING], offset.y + j * SPACINGS[LINE_SPACING]);

                    }
                }
            }
        }
    }
}
