package fr.paragoumba.threedlab.materials;

import java.awt.*;
import java.util.LinkedHashSet;

public abstract class Material {

    public static LinkedHashSet<Material> materials = new LinkedHashSet<>();

    public Material(){

        materials.add(this);

    }

    public static Material getMaterial(String material){

        if (material.startsWith("#")){

            return new ColorMaterial(new Color(Integer.parseInt(material.substring(1), 16)));

        } else {

            return new TextureMaterial("/imgs/" + material + ".png");

        }
    }

    public abstract void drawMaterial(Graphics g);

}
