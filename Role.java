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
     * creates a role does not include movie name since it is included in the
     * Dictionary in actor
     * 
     * @param roleName
     * @param roleTypeName
     * @param billing
     * @param genderName
     */
    public Role(String roleName, String roleTypeName, String billing, String genderName) {
        gender = genderName;
        this.billing = billing;
        this.roleName = roleName;
        roleType = roleTypeName;
    }

    /**
     * @return gender of the role actor plays
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return name of the character actor plays
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @return the role type actor plays, e.g. Leading Supporting
     */
    public String getRoleType() {
        return roleType;
    }

    public String getBilling() {
        return billing;
    }

    /**
     * string representation of role actor plays
     */
    public String toString() {
        return "Role: " + this.roleName + ", Gender: " + this.gender + ", Role type: " + this.roleType + ", Billing: "
                + this.billing + "\n";
    }

}
