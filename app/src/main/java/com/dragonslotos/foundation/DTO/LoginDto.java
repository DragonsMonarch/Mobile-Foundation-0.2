package com.dragonslotos.foundation.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDto {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;

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
