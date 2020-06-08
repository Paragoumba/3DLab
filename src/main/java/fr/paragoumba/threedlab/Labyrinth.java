package fr.paragoumba.threedlab;

import fr.paragoumba.threedlab.materials.Material;

public class Labyrinth {

    public Labyrinth(int width, int height){

        this.width = width;
        this.height = height;
        this.grid = new Material[width][height];

    }

    private final Material[][] grid;
    private final int width;
    private final int height;

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
}
