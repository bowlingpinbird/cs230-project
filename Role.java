public class Role {
    String movie;
    String gender;
    String roleName;
    String roleType;
    String billing;
    
    public Role(String roleName, String roleTypeName, String billing, String genderName){
        gender = genderName;
        this.billing = billing;
        this.roleName = roleName;
        roleType = roleTypeName;
    }

   
 public String getGender(){
        return gender;
    }
     public String getRoleName(){
        return movie;
    }
    public String getRoleType(){
        return roleType;
    }
    public String getBilling(){
        return billing;
    }



}
