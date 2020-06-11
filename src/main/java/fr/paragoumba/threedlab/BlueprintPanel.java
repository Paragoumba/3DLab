package fr.paragoumba.threedlab;

import fr.paragoumba.threedlab.materials.ColorMaterial;
import fr.paragoumba.threedlab.materials.Material;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class BlueprintPanel extends JPanel {

    public static final int PIXEL_SIZE = 10;
    private static final int[] SPACINGS = new int[]{10, 15, 20, 30, 50, 75, 100};
    private static int LINE_SPACING = 4;
    private static BufferedImage arrow;
    private static BufferedImage startFlag;
    private static BufferedImage endFlag;

    public static final Color bgColor = new Color(0, 109, 223);
    public static final Color brightShadow = new Color(255, 255, 255, 20);
    public static final Color darkShadow = new Color(0, 0, 0, 50);
    public static Font font;
    private static Material material = new ColorMaterial(Color.WHITE);

    public BlueprintPanel(List<Level> levels){

        super(new BorderLayout());

        try {

            arrow = ImageIO.read(BlueprintPanel.class.getResourceAsStream("/imgs/arrow.png"));
            startFlag = ImageIO.read(BlueprintPanel.class.getResourceAsStream("/imgs/start-flag.png"));
            endFlag = ImageIO.read(BlueprintPanel.class.getResourceAsStream("/imgs/finish-flag.png"));
            font = Font.createFont(Font.TRUETYPE_FONT, BlueprintPanel.class.getResourceAsStream("/fonts/prstartk.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);

        } catch (IOException | FontFormatException e){

            e.printStackTrace();

        }

        Panel menuPanel = new Panel();

        menuPanel.setBorder(BorderFactory.createEmptyBorder(PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE));
        menuPanel.add(new Menu());

        add(menuPanel, BorderLayout.AFTER_LINE_ENDS);

        this.levels = levels;

        addMouseMotionListener(new MouseAdapter(){

            @Override
            public void mouseMoved(MouseEvent e){

                super.mouseMoved(e);

                if (e.getLocationOnScreen() != null){

                    repaint();

                }
            }

            @Override
            public void mouseDragged(MouseEvent e){

                super.mouseDragged(e);

                if (dragStart != null){

                    Point dragEnd = e.getPoint();

                    offset.x += dragEnd.x - dragStart.x;
                    offset.y += dragEnd.y - dragStart.y;

                    dragStart = dragEnd;
                    repaint();

                }
            }
        });

        addMouseListener(new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent e){

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

                        currentLevel.getLabyrinth().toggleSquare(mouseSquarePosition.x, mouseSquarePosition.y, material);

                        repaint();

                    } else {

                        dragStart = mousePosition;

                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e){

                super.mousePressed(e);

                if (e.getButton() == MouseEvent.BUTTON1){

                    Point mousePosition = e.getPoint();
                    Labyrinth labyrinth = currentLevel.getLabyrinth();

                    int labWidth = labyrinth.getWidth() * SPACINGS[LINE_SPACING];
                    int labHeight = labyrinth.getHeight() * SPACINGS[LINE_SPACING];

                    boolean inLab = mousePosition.x >= offset.x && mousePosition.x < offset.x + labWidth &&
                            mousePosition.y >= offset.y && mousePosition.y < offset.y + labHeight;

                    dragStart = inLab ? null : mousePosition;

                }
            }
        });

        addMouseWheelListener(e -> {

            if (e.getWheelRotation() < 0) {

                if (LINE_SPACING < SPACINGS.length - 1){

                    LINE_SPACING++;
                    repaint();

                }

            } else {

                if (LINE_SPACING > 0){

                    LINE_SPACING--;
                    repaint();

                }
            }
        });
    }

    private final List<Level> levels;
    private Level currentLevel;
    private Point offset;
    private Point dragStart = null;

    private void fillSquare(Graphics g, int x, int y){

        g.fillRect(x, y, SPACINGS[LINE_SPACING], SPACINGS[LINE_SPACING]);

    }

    private Point getSquareMousePosition(Point mousePosition){

        return new Point((int) Math.floor((float)(mousePosition.x - offset.x) / SPACINGS[LINE_SPACING]),
                (int) Math.floor((float)(mousePosition.y - offset.y) / SPACINGS[LINE_SPACING]));

    }

    public List<Level> getLevels(){

        return levels;

    }

    public void setCurrentLevel(int levelIndex){

        if (levelIndex >= levels.size()){

            return;

        }

        this.currentLevel = levels.get(levelIndex);

        Labyrinth labyrinth = currentLevel.getLabyrinth();

        int labWidth = labyrinth.getWidth() * SPACINGS[LINE_SPACING];
        int labHeight = labyrinth.getHeight() * SPACINGS[LINE_SPACING];

        int width = getWidth();
        int height = getHeight();

        offset = new Point((width - labWidth) / 2, (height - labHeight) / 2);

    }

    public static void setCurrentMaterial(Material material){

        BlueprintPanel.material = material;

    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(brightShadow);

        int width = getWidth();
        int height = getHeight();

        for (int i = offset.y % SPACINGS[LINE_SPACING]; i <= height; i += SPACINGS[LINE_SPACING]){

            g.drawLine(0, i, width, i);

        }

        for (int i = offset.x % SPACINGS[LINE_SPACING]; i <= width; i += SPACINGS[LINE_SPACING]){

            g.drawLine(i, 0, i, height);

        }

        g.setColor(Color.WHITE);

        Labyrinth labyrinth = currentLevel.getLabyrinth();

        int labWidth = labyrinth.getWidth() * SPACINGS[LINE_SPACING];
        int labHeight = labyrinth.getHeight() * SPACINGS[LINE_SPACING];

        for (int i = 0; i <= labWidth; i += SPACINGS[LINE_SPACING]){

            g.drawLine(offset.x + i, offset.y, offset.x + i, offset.y + labHeight);

        }

        for (int i = 0; i <= labHeight; i += SPACINGS[LINE_SPACING]){

            g.drawLine(offset.x, offset.y + i, offset.x + labWidth, offset.y + i);

        }

        Point mousePosition = getMousePosition();

        if (mousePosition != null){

            mousePosition = getSquareMousePosition(mousePosition);

        }

        Material[][] grid = labyrinth.getGrid();

        for (int j = 0; j < labyrinth.getHeight(); ++j){
            for (int i = 0; i < labyrinth.getWidth(); ++i){

                Graphics squareG = g.create(offset.x + i * SPACINGS[LINE_SPACING], offset.y + j * SPACINGS[LINE_SPACING], SPACINGS[LINE_SPACING] + 1, SPACINGS[LINE_SPACING] + 1);

                if (grid[i][j] != null){

                    if (mousePosition == null || i != mousePosition.x || j != mousePosition.y){

                        grid[i][j].drawMaterial(squareG);

                    }

                } else {

                    if (mousePosition != null && i == mousePosition.x && j == mousePosition.y){

                        fillSquare(g, offset.x + i * SPACINGS[LINE_SPACING], offset.y + j * SPACINGS[LINE_SPACING]);

                    }
                }
            }
        }

        {

            Point startPoint = labyrinth.getStart();

            int x1 = offset.x + startPoint.x * SPACINGS[LINE_SPACING];
            int y1 = offset.y + startPoint.y * SPACINGS[LINE_SPACING];

            g.drawImage(startFlag, x1, y1, SPACINGS[LINE_SPACING] + 1,  SPACINGS[LINE_SPACING] + 1, null);

        }

        {

            Point endPoint = labyrinth.getEnd();

            int x1 = offset.x + endPoint.x * SPACINGS[LINE_SPACING];
            int y1 = offset.y + endPoint.y * SPACINGS[LINE_SPACING];

            g.drawImage(endFlag, x1, y1, SPACINGS[LINE_SPACING] + 1, SPACINGS[LINE_SPACING] + 1, null);

        }

        if (arrow != null && offset.x > width || offset.x < -labWidth || offset.y > height || offset.y < -labHeight){

            int labCenterX = offset.x + labWidth / 2;
            int labCenterY = offset.y + labHeight / 2;

            int halfWidth = width / 2;
            int halfHeight= height / 2;

            Point arrowVector = new Point(0, -1);
            Point labVector = new Point(labCenterX - halfWidth, labCenterY - halfHeight);

            AffineTransform at = new AffineTransform();

            at.translate(halfWidth, halfHeight);
            at.scale(0.5, 0.5);

            double angle = (labCenterX < halfWidth ? -1 : 1) * Math.acos((labVector.x * arrowVector.x + labVector.y * arrowVector.y) / Math.sqrt(Math.pow(labVector.x, 2) + Math.pow(labVector.y, 2)) * Math.sqrt(Math.pow(arrowVector.x, 2) + Math.pow(arrowVector.y, 2)));

            at.rotate(angle);
            at.translate((float) -arrow.getWidth() / 2, (float) -arrow.getHeight() / 2);

            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2d.drawImage(arrow, at, this);

        }

        g.setFont(new Font(font.getName(), Font.PLAIN, 25));

        g.setColor(Color.WHITE);
        g.drawString("Level " + currentLevel.getLevel() + " - " + currentLevel.getName(), 2 * PIXEL_SIZE, 4 * PIXEL_SIZE);

    }
}
