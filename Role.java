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
        this.Movie = movie;
        this.part = part;
        this.sceneCount = sceneCount;
        this.gender = gender;
    }
    
}
