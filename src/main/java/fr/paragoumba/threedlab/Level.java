package fr.paragoumba.threedlab;

public class Level {

    public Level(String name, Labyrinth labyrinth){

        this.level = LAST_LEVEL;
        LAST_LEVEL++;
        this.name = name;
        this.labyrinth = labyrinth;

    }

    private static int LAST_LEVEL = 0;
    private final int level;
    private final String name;
    private final Labyrinth labyrinth;

    public int getLevel(){

        return level;

    }

    public String getName(){

        return name;

    }

    public Labyrinth getLabyrinth(){

        return labyrinth;

    }
}
