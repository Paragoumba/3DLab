package fr.paragoumba.threedlab.materials;

import java.util.LinkedHashSet;

public abstract class Material {

    public static LinkedHashSet<Material> materials = new LinkedHashSet<>();

    public Material(){

        materials.add(this);

    }
}
