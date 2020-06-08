package fr.paragoumba.threedlab.materials;

public class TextureMaterial extends Material {

    public TextureMaterial(String path){

        this.path = path;

    }

    private final String path;

    @Override
    public String toString(){

        String subStr = path.substring(path.lastIndexOf('/') + 1);

        return subStr.substring(0, subStr.lastIndexOf('.'));

    }
}
