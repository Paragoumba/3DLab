package fr.paragoumba.threedlab;

public class Labyrinth {

    public Labyrinth(int width, int height){

        this.width = width;
        this.height = height;
        this.grid = new boolean[width][height];

    }

    private final boolean[][] grid;
    private final int width;
    private final int height;

    public void toggleSquare(int x, int y){

        if (x >= 0 && x < width && y >= 0 && y < height){

            grid[x][y] = !grid[x][y];

        }
    }

    public boolean[][] getGrid(){

        return grid;

    }

    public int getWidth(){

        return width;

    }

    public int getHeight(){

        return height;

    }
}
