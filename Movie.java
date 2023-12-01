import java.util.ArrayList;
/**
 * @author Sophie Lin
 * @author Rachel Hu
 * @author Lilymoon Whalen
 * @version 11/28/2023
 */
public class Movie{
    private ArrayList<Actor> actors = new ArrayList<Actor>();
    private String name;

    /**
     * Constructor for creating a movie based on its name
     * @param movieName
     */
    public Movie(String movieName){
        name = movieName;
        actors = new ArrayList<Actor>();
    }

    /**
     * Returns the name of the Movie
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Adds an Actor to the list of Actors that have played in this movie
     * @param a1 Actor to add
     */
    public void addActor(Actor a1){
        actors.add(a1);
    }

    /**
     * Returns a String list of all the Actors involved in this MOvie
     * @return the list of actors 
     */
    public String getActors() {
        String description = "Actors involved in " + this.name + "\n";
        for (Actor actor : this.actors) {
            description += actor.getName() + "\n";
        }
        return description;
    }
}