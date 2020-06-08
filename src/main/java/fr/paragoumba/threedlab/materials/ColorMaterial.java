package fr.paragoumba.threedlab.materials;

import java.awt.*;

public class ColorMaterial extends Material {

    public ColorMaterial(Color color){

        this.color = color;

    }

    private final Color color;

    @Override
    public String toString(){

        return String.format("#%06x", color.getRGB() & 0x00FFFFFF).toUpperCase();

    }
}
