package fr.paragoumba.threedlab;

public class Level {

    public Level(Labyrinth labyrinth){

        this.labyrinth = labyrinth;

    }

    private Labyrinth labyrinth;

    public Labyrinth getLabyrinth(){

        return labyrinth;

    }
}
