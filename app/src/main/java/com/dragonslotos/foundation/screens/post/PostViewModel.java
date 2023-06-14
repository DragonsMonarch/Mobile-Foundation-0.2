package com.dragonslotos.foundation.screens.post;

import android.text.format.DateFormat;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dragonslotos.foundation.DTO.PostDTO;
import com.dragonslotos.foundation.RetroFit.NetworkService;
import com.dragonslotos.foundation.model.Post;
import com.dragonslotos.foundation.model.Theme;
import com.dragonslotos.foundation.model.User;
import com.vk.api.sdk.requests.VKRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    MutableLiveData Login = new MutableLiveData<String>();
    MutableLiveData Password = new MutableLiveData<String>();
    MutableLiveData Posts = new MutableLiveData<List<com.dragonslotos.foundation.model.Post>>();
    MutableLiveData Name = new MutableLiveData<String>();
    MutableLiveData Like = new MutableLiveData<Boolean>();
    MutableLiveData Owner = new MutableLiveData<String>();
    MutableLiveData PostOwner = new MutableLiveData<User>();
    List<com.dragonslotos.foundation.model.Post> origPosts = new ArrayList<com.dragonslotos.foundation.model.Post>();
    public PostViewModel(){
        Like.setValue(false);
    }
    public void getLikeFromeServeAll(){
        NetworkService.getInstance().getJSONApi()
                .getPosts("api/posts/like/get/" + Login.getValue() + "/" + Password.getValue())
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        Posts.setValue(response.body());

                        if(origPosts != null){
                            origPosts =response.body();
                            for(Post post: origPosts){
                                if(post.getName().equals(Name.getValue().toString())){
                                    Like.setValue(true);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                    }
                });
    }
    public void setLike(String name, String body){
        PostDTO post = new PostDTO();
        post.setName(name);
        post.setBody(body);
        Date date = new Date();
        String day          = (String) DateFormat.format("dd",   date); // 20
        String monthString  = (String) DateFormat.format("MMM",  date);
        String year         = (String) DateFormat.format("yyyy", date);
        post.setDate(day + " " + monthString + " " +year);
        post.setOwner(Login.getValue().toString());
        post.setThemes(new HashSet<Theme>());

        NetworkService.getInstance().getJSONApi().postDataPost("/api/posts/like/" + Login.getValue().toString() +
                                "/" + Password.getValue().toString(),
                        post)
                .enqueue(new Callback<PostDTO>() {
                    @Override
                    public void onResponse(Call<PostDTO> call, Response<PostDTO> response) {

                    }

                    @Override
                    public void onFailure(Call<PostDTO> call, Throwable t) {

                    }
                });
    }
    public void disLike(String name, String body){
        PostDTO post = new PostDTO();
        post.setName(name);
        post.setBody(body);
        Date date = new Date();
        String day          = (String) DateFormat.format("dd",   date); // 20
        String monthString  = (String) DateFormat.format("MMM",  date);
        String year         = (String) DateFormat.format("yyyy", date);
        post.setDate(day + " " + monthString + " " +year);
        post.setOwner(Login.getValue().toString());
        post.setThemes(new HashSet<Theme>());

        NetworkService.getInstance().getJSONApi().postDataPost("/api/posts/dislike/" + Login.getValue().toString() +
                                "/" + Password.getValue().toString(),
                        post)
                .enqueue(new Callback<PostDTO>() {
                    @Override
                    public void onResponse(Call<PostDTO> call, Response<PostDTO> response) {

                    }

                    @Override
                    public void onFailure(Call<PostDTO> call, Throwable t) {

                    }
                });
    }
    public void getOwner(){
        NetworkService.getInstance().getJSONApi().getUser("/api/auth/getuser/" + Owner.getValue() +"/" + Login.getValue().toString() +"/" + Password.getValue().toString())
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        PostOwner.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
    }
    public void setTablPoints(){
        //Пока пусть бует вдруг прегадится

        VKRequest request = new VKRequest("secure.addAppEvent", "5.131");
    }
}
