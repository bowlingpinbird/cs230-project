/**
 * Represents an actor's Role in a movie, with the name of the role, type,
 * billing, and gender
 * 
 * @author Lilymoon Whalen
 * @author Sophie Lin
 * @author Rachel Hu
 * 
 * @version December 12, 2023
 */

public class Role {
    private String gender; // gender of the role the actor plays
    private String roleName;
    private String roleType;
    private String billing;

    /**
     * Creates a Role .
     * Does not include movie name since it is used as a key to the Hashtable in Actor
     * 
     * @param roleName - name of the Role
     * @param roleTypeName - type of role (e.g. Leading)
     * @param billing - billing number
     * @param genderName - gender of the role
     */
    public Role(String roleName, String roleTypeName, String billing, String genderName) {
        gender = genderName;
        this.billing = billing;
        this.roleName = roleName;
        roleType = roleTypeName;
    }

    /**
     * Gets the gender associated with this Role
     * @return gender of the role Actor plays
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the name of the character that this Role is associated with
     * @return name of the character actor plays
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Gets the type of role that this is
     * @return the role type actor plays, e.g. Leading Supporting
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * Gets the billing number of this Role
     * @return the billing number
     */
    public String getBilling() {
        return billing;
    }

    /**
     * String representation of role actor plays
     * @return the String 
     */
    public String toString() {
        return "Role: " + this.roleName + ", Gender: " + this.gender + ", Role type: " + this.roleType + ", Billing: "
                + this.billing + "\n";
    }

}
