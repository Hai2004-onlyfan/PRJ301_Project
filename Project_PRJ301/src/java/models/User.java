package models;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String displayname;
    private Employee e;
    private ArrayList<Role> roles = new ArrayList<>();

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

    public Employee getE() {
        return e;
    }

    public void setE(Employee e) {
        this.e = e;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    // Kiểm tra vai trò Director
    public boolean isDirector() {
        return roles.stream().anyMatch(role -> role.getName().equals("director"));
    }

    // Kiểm tra vai trò Manager
    public boolean isManager() {
        return roles.stream().anyMatch(role -> role.getName().equals("manager"));
    }

    // Kiểm tra vai trò Employee
    public boolean isEmployee() {
        return roles.stream().anyMatch(role -> role.getName().equals("staff"));
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", displayname=" + displayname + ", e=" + e + ", roles=" + roles + '}';
    }
}
