/**
 * Represents an Actor's Role in a Movie
 * cs230 project Task 1
 * 
 * @author Lily Wholen, Sophie Lin, Rachel Hu
 * @version November 28, 2023
 */

public class Role {
    private Movie movie;
    private String part;
    private int sceneCount;
    private String gender;

    /**
     * Defines the an Actor's Role in a specified Movie
     * @param movie - movie they played the part in
     * @param part - name of their role
     * @param sceneCount - number of scenes they appeared in
     * @param gender - gender of the role they played
     */
    public Role(Movie movie, String part, int sceneCount, String gender) {
        this.movie = movie;
        this.part = part;
        this.sceneCount = sceneCount;
        this.gender = gender;
    }

    /**
     * Gets the movie associated with this role
     * @return the movie as a Movie object
     */
    public Movie getMovie() {
        return this.movie;
    }

    /**
     * Returns the gender of this Role
     * @return String of the gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Returns the number of scenes this role appeared in the movie
     * @return int representing the number of scenes
     */
    public int getSceneCount() {
        return this.sceneCount;
    }

}
