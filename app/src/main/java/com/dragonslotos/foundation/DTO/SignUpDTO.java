package com.dragonslotos.foundation.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpDTO {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("idVK")
    @Expose
    private String idVK;

    public String getIdVK() {
        return idVK;
    }

    public void setIdVK(String idVK) {
        this.idVK = idVK;
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
