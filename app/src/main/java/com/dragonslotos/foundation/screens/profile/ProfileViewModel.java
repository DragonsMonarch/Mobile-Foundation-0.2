package com.dragonslotos.foundation.screens.profile;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dragonslotos.foundation.RetroFit.NetworkService;
import com.dragonslotos.foundation.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    MutableLiveData Login = new MutableLiveData<String>();
    MutableLiveData Password = new MutableLiveData<String>();
    MutableLiveData Posts = new MutableLiveData<List<Post>>();
    List<Post> origPosts = new ArrayList<Post>();
    MutableLiveData PostsLike = new MutableLiveData<List<Post>>();
    public void getFromeServeAll(){
        NetworkService.getInstance().getJSONApi()
                .getPosts("api/posts/getown/" + Login.getValue() + "/" + Password.getValue())
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        origPosts = response.body();
                        Posts.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                    }
                });
    }
    public void getLikeFromeServeAll(){
        NetworkService.getInstance().getJSONApi()
                .getPosts("api/posts/like/get/" + Login.getValue() + "/" + Password.getValue())
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        PostsLike.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                    }
                });
    }
}
