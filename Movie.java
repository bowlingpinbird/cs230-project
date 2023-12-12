/**
 * Represents a Movie object as a FilmElement that has a list of Actors
 * 
 * @author Sophie Lin
 * @author Rachel Hu
 * @author Lilymoon Whalen
 * @version December 12, 2023
 */

import java.util.ArrayList;

public class Movie extends FilmElement {
    private ArrayList<Actor> actors = new ArrayList<Actor>(); //list of actors that participated in this movie

    /**
     * Constructor for creating a movie based on its name
     * 
     * @param movieName
     */
    public Movie(String movieName) {
        super(movieName, "Movie");
        actors = new ArrayList<Actor>();
    }

    /**
     * Returns the name of the Movie
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Adds an Actor to the list of Actors that have played in this movie
     * 
     * @param a1 Actor to add
     */
    public void addActor(Actor a1) {
        actors.add(a1);
    }

    /**
     * Returns a String list of all the Actors involved in this MOvie
     * 
     * @return the list of actors
     */
    public String getActors() {
        String description = "Actors involved in " + this.name + "\n";

        for (Actor actor : this.actors) {
            description += actor.getName() + "\n";
        }
        return description;
    }

    /**
     * Gets the List of actors that acted in this movie
     * @return and ArrayList of the Actors
     */
    public ArrayList<Actor> getActorList() {
        return actors;
    }

    /**
     * Creates a String description of this Movie object
     * (Consists of just the name)
     * @return the description
     */
    public String toString() {
        return name;
    }
}