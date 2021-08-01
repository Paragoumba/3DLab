package fr.paragoumba.threedlab;

import fr.paragoumba.threedlab.materials.Material;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Importer {

    private Importer(){}

    public static List<Level> importData(File file){

        try(FileInputStream fis = new FileInputStream(file);
            BufferedInputStream in = new BufferedInputStream(fis)){

            JSONTokener tokener = new JSONTokener(in);
            JSONObject root = new JSONObject(tokener);

            List<Level> levels = new ArrayList<>();

            JSONArray levelArray = root.getJSONArray("levels");

            for (int i = 0; i < levelArray.length(); ++i){

                JSONObject levelObject = levelArray.getJSONObject(i);
                JSONObject labyrinthObject = levelObject.getJSONObject("lab");
                JSONArray wallsArray = labyrinthObject.getJSONArray("walls");

                int width = labyrinthObject.getInt("width");
                int height = labyrinthObject.getInt("height");

                Material[][] grid = new Material[width][height];

                for (int j = 0; j < wallsArray.length(); ++j){

                    JSONObject wall = wallsArray.getJSONObject(j);

                    int x = wall.getInt("x");
                    int y = wall.getInt("y");
                    grid[x][y] = Material.getMaterial(wall.getString("mat"));

                }

                Labyrinth lab = new Labyrinth(width, height, grid);

                {

                    JSONObject startObject = labyrinthObject.getJSONObject("start");

                    Point start = new Point(startObject.getInt("x"), startObject.getInt("y"));

                    lab.setStart(start);

                }

                {

                    JSONObject endObject = labyrinthObject.getJSONObject("end");

                    Point end = new Point(endObject.getInt("x"), endObject.getInt("y"));

                    lab.setEnd(end);

                }

                Level level = new Level("", lab);
                levels.add(level);

            }

            return levels;

        } catch (IOException e){

            e.printStackTrace();

            return null;

        }
    }
}
