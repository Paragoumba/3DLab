package fr.paragoumba.threedlab;

import fr.paragoumba.threedlab.materials.Material;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Exporter {

    private Exporter(){}

    public static final File exportDir = new File("export");
    public static final File dataFile = new File(exportDir, "data.json");

    public static void export(List<Level> levels){

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

        File fontsDir = new File(exportDir, "fonts");

        if (!fontsDir.exists()){

            fontsDir.mkdir();

        }

        File imgsDir = new File(exportDir, "imgs");

        if (!imgsDir.exists()){

            imgsDir.mkdir();

        }

        Utils.copyResource("css/style.css");
        Utils.copyResource("fonts/prstartk.ttf");

        Utils.copyResource("imgs/3dlab-icon.png");
        Utils.copyResource("imgs/brick.png");
        Utils.copyResource("imgs/stone.png");
        Utils.copyResource("imgs/cloudtop_ft.png");
        Utils.copyResource("imgs/cloudtop_bk.png");
        Utils.copyResource("imgs/cloudtop_up.png");
        Utils.copyResource("imgs/cloudtop_dn.png");
        Utils.copyResource("imgs/cloudtop_rt.png");
        Utils.copyResource("imgs/cloudtop_lf.png");

        Utils.copyResource("js/three.min.js");
        Utils.copyResource("js/BasisTextureLoader.min.js");
        Utils.copyResource("js/PointerLockControls.min.js");
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

            {

                Point start = labyrinth.getStart();
                JSONObject startObject = new JSONObject();

                startObject.put("x", start.x);
                startObject.put("y", start.y);

                labyrinthObject.put("start", startObject);

            }

            {

                Point end = labyrinth.getEnd();
                JSONObject endObject = new JSONObject();

                endObject.put("x", end.x);
                endObject.put("y", end.y);

                labyrinthObject.put("end", endObject);

            }

            labyrinthObject.put("walls", wallsArray);

            Material[][] grid = labyrinth.getGrid();

            for (int i = 0; i < grid.length; ++i){
                for (int j = 0; j < grid[i].length; ++j){

                    if (grid[i][j] != null){

                        JSONObject square = new JSONObject();

                        square.put("x", i);
                        square.put("y", j);
                        square.put("mat", grid[i][j].toString());

                        wallsArray.put(square);

                    }
                }
            }

            levelArray.put(levelObject);

        }

        try(FileOutputStream fos = new FileOutputStream(dataFile);
            PrintWriter out = new PrintWriter(fos)){

            String exportedJson = root.toString(2);

            out.println(exportedJson);

        } catch (IOException e){

            e.printStackTrace();

        }
    }
}
