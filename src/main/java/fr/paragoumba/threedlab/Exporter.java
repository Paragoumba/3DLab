package fr.paragoumba.threedlab;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Exporter {

    private Exporter(){}

    public static void export(List<Level> levels){

        File exportDir = new File("export");

        if (!exportDir.exists()){

            exportDir.mkdir();

        }

        File cssDir = new File(exportDir, "css");

        if (!cssDir.exists()){

            cssDir.mkdir();

        }

        File jsDir = new File(exportDir, "js");

        if (!jsDir.exists()){

            jsDir.mkdir();

        }

        Utils.copyResource("css/style.css");
        Utils.copyResource("js/three.min.js");
        Utils.copyResource("js/script.js");
        Utils.copyResource("index.html");

        JSONObject root = new JSONObject();
        JSONArray levelArray = new JSONArray();

        root.put("levels", levelArray);

        for (Level level : levels){

            JSONObject levelObject = new JSONObject();
            Labyrinth labyrinth = level.getLabyrinth();
            JSONObject labyrinthObject = new JSONObject();
            JSONArray wallsArray = new JSONArray();

            levelObject.put("lab", labyrinthObject);

            labyrinthObject.put("width", labyrinth.getWidth());
            labyrinthObject.put("height", labyrinth.getHeight());
            labyrinthObject.put("walls", wallsArray);

            boolean[][] grid = labyrinth.getGrid();

            for (int i = 0; i < grid.length; ++i){
                for (int j = 0; j < grid[i].length; ++j){

                    if (grid[i][j]){

                        JSONObject square = new JSONObject();

                        square.put("x", i);
                        square.put("y", j);

                        wallsArray.put(square);

                    }
                }
            }

            levelArray.put(levelObject);

        }

        try(FileOutputStream fos = new FileOutputStream(new File(exportDir, "data.json"));
            PrintWriter out = new PrintWriter(fos)){

            String exportedJson = root.toString(2);

            out.println(exportedJson);
            System.out.println(exportedJson);

        } catch (IOException e){

            e.printStackTrace();

        }
    }
}
