package com.model;
// Generated Aug 20, 2012 8:44:26 PM by Hibernate Tools 3.2.1.GA



/**
 * Users generated by hbm2java
 */
public class Users  implements java.io.Serializable {


     private String username;
     private String password;
     private String role;
     private String mark;
     private Integer enabled;

    public Users() {
    }

	
    public Users(String username) {
        this.username = username;
    }
    public Users(String username, String password, String role, String mark, Integer enabled) {
       this.username = username;
       this.password = password;
       this.role = role;
       this.mark = mark;
       this.enabled = enabled;
    }
   
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    public String getMark() {
        return this.mark;
    }
    
    public void setMark(String mark) {
        this.mark = mark;
    }
    public Integer getEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }




}


