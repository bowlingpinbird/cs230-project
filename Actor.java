/**
 * Represents an Actor with their name and the movies they've acted in
 * cs230 final project Task 1
 * 
 * @author Sophie Lin
 * @author Rachel Hu
 * @author Lilymoon Whalen
 * @version December 6, 2023
 */

import java.util.Vector;

public class Actor extends FilmElement {
    private int roleCount;

    /*
     * store information about the actor's roles
     * corresponding information of a given role in a given movie is placed in the
     * same index across all vectors
     */
    private Vector<Movie> movies; // list of movies the actor has participated in
    private Vector<String> roles; // all roles the actor has played
    private Vector<String> roleTypes; // types of roles of the actor's roles
    private Vector<Integer> billings; // billing of each role
    private Vector<String> genders; // gender of each role played

    /**
     * Constructor for a new Actor based on name
     * 
     * @param name - name of the Actor
     */
    public Actor(String name) {
        super(name);
        this.roleCount = 0;

        movies = new Vector<Movie>();
        roles = new Vector<String>();
        roleTypes = new Vector<String>();
        billings = new Vector<Integer>();
        genders = new Vector<String>();
    }

    /**
     * Getter for name
     * 
     * @return name of Actor
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for number of roles this Actor has played
     * 
     * @return number of roles
     */
    public int getRoleCount() {
        return roleCount;
    }

    /**
     * Adds a role to the list of information that
     * 
     * @param movieName
     * @param roleName
     * @param roleTypeName
     * @param billing
     * @param genderName
     */
    public void addRole(String movieName, String roleName, String roleTypeName, String billing, String genderName) {
        this.movies.add(new Movie(movieName));
        this.roles.add(roleName);
        this.roleTypes.add(roleTypeName);
        this.billings.add(Integer.parseInt(billing));
        this.genders.add(genderName);
        roleCount++;
    }

    /**
     * Returns a String list of all the movies this Actor has played in
     * 
     * @return the list of movies
     */
    public String getMovies() {
        String description = "Movies " + this.name + " has played in:\n";
        for (Movie movie : this.movies) {
            description += movie.getName() + "\n";
        }
        return description;
    }

    public String toString() {
        return name;
    }

    // TODO ask Takis about what he means with main() to show how we use this class
}