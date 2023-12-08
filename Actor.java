/**
 * Represents an Actor with their name and the movies they've acted in
 * cs230 final project Task 1
 * 
 * @author Sophie Lin
 * @author Rachel Hu
 * @author Lilymoon Whalen
 * @version December 8, 2023
 */

import java.util.Vector;
import java.util.Hashtable;

public class Actor extends FilmElement {
    private int roleCount;

    /*
     * store information about the actor's roles
     * corresponding information of a given role in a given movie is placed in the
     * same index across all vectors
     */
    private Hashtable<String, Role> roleInfo; // given a movie name, we can see the role assoicated with such movie
    private Vector<Movie> movies; // list of movies the actor has participated in

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

    /** 
     * gets the gender assiociated with specific movie
     * 
     * @return String of the gender
     */
    public String genderAssociatedWithMovie(String movieName){
        Role temp = roleInfo.get(movieName);
        return temp.getGender();
    }

    /**
     * Gets information about this Actor's role in a given movie
     * @param movieName name of the movie to get this Actor's role from
     * @return the Role object that has information about the role
     */
    public Role getRoleFromMovie(String movieName) {
        return roleInfo.get(movieName);
    }

    /**
     * Returns a String description of this Actor
     * @return the description
     */
    public String toString() {
        String description = this.name + "\nMovies and roles:\n";
        for (Movie movie : this.movies) {
            description += movie.getName() + "Role information: " + roleInfo.get(movie.getName()).toString(); //TODO CAUSING RUNTIME ERRORS
        }
        return description;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        Actor a1 = new Actor("me");
        a1.addRole("movie", "person", "leading", "1", "female");
        System.out.println(a1);
    }
}