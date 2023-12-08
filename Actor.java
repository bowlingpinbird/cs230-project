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
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Actor extends FilmElement {
    private int roleCount;

    /*
     * store information about the actor's roles
     * corresponding information of a given role in a given movie is placed in the
     * same index across all vectors
     */
    private Dictionary<String, Role> roleInfo;
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
        super(name, "Actor");
        this.roleCount = 0;
        roleInfo = new Hashtable<>();

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
        Role role = new Role(roleName, roleTypeName, billing, genderName);
        roleInfo.put(movieName, role);
        this.movies.add(new Movie(movieName));

        //this.roles.add(roleName);
        //this.roleTypes.add(roleTypeName);
        //this.billings.add(Integer.parseInt(billing));
        //this.genders.add(genderName);
        roleCount++;
    }

    public int getMovieIndex(Movie temp){
        for(int i = 0; i < movies.size(); i++){
            movies.get(i).equals(temp)
        }
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
    /** gets the gender assiociated with specific movie
     * 
     * @return
     */
    public String genderAssociatedWithMovie(String movieName){
        Role temp = roleInfo.get(movieName);
        return temp.getGender();
    }

    public String toString() {
        return name;
    }

    // TODO ask Takis about what he means with main() to show how we use this class
}