package fr.paragoumba.threedlab;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

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

        JSONObject root = new JSONObject();



    }
}
