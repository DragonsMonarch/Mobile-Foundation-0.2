package com.dragonslotos.foundation.screens.posts;

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

public class PostsViewModel extends ViewModel {
    MutableLiveData Login = new MutableLiveData<String>();
    MutableLiveData Password = new MutableLiveData<String>();
    MutableLiveData PostName = new MutableLiveData<String>();
    MutableLiveData Posts = new MutableLiveData<List<Post>>();
    List<Post> origPosts = new ArrayList<Post>();
    public PostsViewModel() {
    }
    public void getFromeServeAll(){
        NetworkService.getInstance().getJSONApi()
                .getPosts("api/posts/get/" + Login.getValue() + "/" + Password.getValue())
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
    public void getFromeServeNamed(){
        Log.d("xui", PostName.getValue().toString());
        NetworkService.getInstance().getJSONApi()
                .getPosts("api/posts/get/"+ PostName.getValue().toString() + "/" + Login.getValue().toString() + "/" + Password.getValue().toString())
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        Posts.setValue(response.body());
                        origPosts = response.body();
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                    }
                });
    }
}
