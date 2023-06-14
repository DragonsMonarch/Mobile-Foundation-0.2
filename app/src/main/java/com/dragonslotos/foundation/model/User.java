package com.dragonslotos.foundation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("idVk")
    @Expose
    private String idVK;

    @SerializedName("likes")
    @Expose
    private long likes;

    public String getIdVK() {
        return idVK;
    }

    public void setIdVK(String idVK) {
        this.idVK = idVK;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
