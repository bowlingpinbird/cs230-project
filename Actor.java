/**
 * Represents an Actor with their name and the movies they've acted in
 * cs230 final project Task 1
 * 
 * @author Sophie Lin
 * @author Rachel Hu
 * @author Lilymoon Whalen
 * @version November 28, 2023
 */

import java.util.ArrayList;
import java.util.Vector;

public class Actor {
    private final String name;
    private int roleCount;
    
    /*
     * store information about the actor's roles
     */
    private Vector<Movie> movies;
    private Vector<String> roles;
    private Vector<String> roleTypes;
    private Vector<Integer> billings;
    private Vector<String> genders;

    /**
     * Constructor for a new Actor based on name
     * @param name - name of the Actor
     */
    public Actor(String name) {
        this.name = name;
        this.roleCount = 0;
        
        movies = new Vector<Movie>();
        roles = new Vector<String>();
        roleTypes = new Vector<String>();
        billings = new Vector<Integer>();
        genders = new Vector<String>();
    }

    /**
     * Getter for name
     * @return name of Actor
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for number of roles this Actor has played
     * @return number of roles
     */
    public int getRoleCount() {
        return roleCount;
    }

    /**
     * Adds a role that this Actor has participated in 
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
    }

    /**
     * Returns a String list of all the movies this Actor has played in
     * @return the list of movies 
     */
    public String getMovies() {
        String description = "Movies " + this.name + " has played in:\n";
        for (Movie movie : this.movies) {
            description += movie.getName() + "\n";
        }
        return description;
    }


    
}