/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

public class Account {
    private int UserID, role, status;
    private String email, password, fullName, address;
    private Date createAt;
    
    public Account(int UserID, String address, Date createAt, String email, String fullName, String password, int role, int status) {
        this.UserID = UserID;
        this.address = address;
        this.createAt = createAt;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
        this.status = status;
    }
    public Account() {
    }
    
    public int getUserID() {
        return UserID;
    }
    public int getRole() {
        return role;
    }
    public int getStatus() {
        return status;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Date getCreateAt() {
        return createAt;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
