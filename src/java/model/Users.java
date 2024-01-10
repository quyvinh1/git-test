/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FPTSHOP
 */
public class Users {
    /*
        [Id]
      ,[Name]
      ,[Address]
      ,[Phone]
      ,[Username]
      ,[Password]
      ,[Role]
    */
    private int iD;
    private String name,address,phone,username,pass,role;

    public Users() {
    }
    public Users(String username, String password, String role) {
        this.username = username;
        this.pass = password;
        this.role = role;
    }
    public Users(int iD, String name, String address, String phone, String username, String pass, String role) {
        this.iD = iD;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.pass = pass;
        this.role = role;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
