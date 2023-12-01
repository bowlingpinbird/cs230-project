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

public class Actor {
    private String name;
    private Role[] roles;
    private int roleCount;

    /**
     * Returns the name of the Actor
     * @return name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an array containing all the roles this Actor has played
     * @return the array of Roles
     */
    public Role[] getRoles() {
        return roles;
    }

    /**
     * Returns the number of roles this Actor has played
     * @return number of roles
     */
    public int getRoleCount() {
        return roleCount;
    }

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