/**
 * Represents an actor's Role in a movie, with the name of the role, type, billing, and gender
 * 
 * @author Lilymoon Whalen
 * @author Sophie Lin
 * @author Rachel Hu
 * 
 * @version December 8, 2023
 */

public class Role {
    private String movie;
    private String gender;
    private String roleName;
    private String roleType;
    private String billing;

    public Role(String roleName, String roleTypeName, String billing, String genderName) {
        gender = genderName;
        this.billing = billing;
        this.roleName = roleName;
        roleType = roleTypeName;
    }

    public String getGender() {
        return gender;
    }

    public String getRoleName() {
        return movie;
    }

    public String getRoleType() {
        return roleType;
    }

    public String getBilling() {
        return billing;
    }

    public String toString() {
        return "Movie: " + this.movie + ", Role: " + this.roleName + ", Gender: " + this.gender + ", Role type: " + this.roleType + ", Billing: " + this.billing + "\n";
    }

}
