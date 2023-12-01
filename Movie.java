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
}