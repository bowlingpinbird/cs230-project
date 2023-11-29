/**
 * Represents an Actor with their name and the movies they've acted in
 * cs230 final project Task 1
 * 
 * @author Lily Wholen, Sophie Lin, Rachel Hu
 * @version November 28, 2023
 */

import java.util.ArrayList;

public class Actor {
    private String name;
    private Role[] roles;
    private int roleCount;

    /**
     * Constructor for a new Actor based on name
     * @param name - name of the Actor
     */
    public Actor(String name) {
        this.name = name;
        this.roles = new Role[10];
        this.roleCount = 0;
    }

    /**
     * Adds a movie to the array of movies that this Actor has participated in
     * @param movieName - String of the name of the new movie
     */
    public void addRole(Role newRole) {
        this.roles[roleCount] = newRole;
        roleCount ++;
    }
    



}