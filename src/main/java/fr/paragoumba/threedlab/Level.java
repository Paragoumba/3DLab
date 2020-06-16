package fr.paragoumba.threedlab;

public class Level {

    public Level(String name, Labyrinth labyrinth){

        this.name = name;
        this.labyrinth = labyrinth;

    }

    private final String name;
    private final Labyrinth labyrinth;

    public String getName(){

        return name;

    }

    public Labyrinth getLabyrinth(){

        return labyrinth;

    }
}
