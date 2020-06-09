package fr.paragoumba.threedlab;

import fr.paragoumba.threedlab.materials.Material;

import java.awt.*;

public class Labyrinth {

    public Labyrinth(int width, int height){

        this.width = width;
        this.height = height;
        this.start = new Point(0, 0);
        this.end = new Point(width - 1, height - 1);
        this.grid = new Material[width][height];

    }

    private final Material[][] grid;
    private final int width;
    private final int height;
    private Point start;
    private Point end;

    public void toggleSquare(int x, int y, Material material){

        if (x >= 0 && x < width && y >= 0 && y < height){

            grid[x][y] = material;

        }
    }

    public Material[][] getGrid(){

        return grid;

    }

    public int getWidth(){

        return width;

    }

    public int getHeight(){

        return height;

    }

    public Point getStart(){

        return start;

    }

    public Point getEnd(){

        return end;

    }

    public void setStart(Point start){

        this.start = start;

    }

    public void setEnd(Point end){

        this.end = end;

    }
}
