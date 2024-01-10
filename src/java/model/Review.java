/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell Inspiron
 */
public class Review {
    private int id;
    private String review,date_post,uid,pid;

    public Review() {
    }

    public Review(int id, String review, String date_post, String uid, String pid) {
        this.id = id;
        this.review = review;
        this.date_post = date_post;
        this.uid = uid;
        this.pid = pid;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDate_post() {
        return date_post;
    }

    public void setDate_post(String date_post) {
        this.date_post = date_post;
    }
    
}
