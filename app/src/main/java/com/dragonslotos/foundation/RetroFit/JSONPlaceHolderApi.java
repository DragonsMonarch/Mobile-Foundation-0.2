package com.dragonslotos.foundation.RetroFit;

import com.dragonslotos.foundation.DTO.PostDTO;
import com.dragonslotos.foundation.DTO.LoginDto;
import com.dragonslotos.foundation.DTO.SignUpDTO;
import com.dragonslotos.foundation.model.Post;
import com.dragonslotos.foundation.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface JSONPlaceHolderApi {
    @POST("/api/auth/signup")
    public Call<SignUpDTO> postDataSignUp(@Body SignUpDTO data);
    @POST("/api/auth/signin")
    public Call<LoginDto> postDataSignIn(@Body LoginDto data);
    @GET
    public Call<List<Post>> getPosts(@Url String url);
    @POST
    public Call<PostDTO> postDataPost(@Url String url, @Body PostDTO data);
    @GET
    public Call<User> getUser(@Url String url);

}
