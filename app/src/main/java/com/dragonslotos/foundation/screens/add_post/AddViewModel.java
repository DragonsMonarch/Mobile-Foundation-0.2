package com.dragonslotos.foundation.screens.add_post;

import android.text.format.DateFormat;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dragonslotos.foundation.DTO.LoginDto;
import com.dragonslotos.foundation.DTO.PostDTO;
import com.dragonslotos.foundation.RetroFit.NetworkService;
import com.dragonslotos.foundation.model.Theme;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddViewModel extends ViewModel {
    MutableLiveData Login = new MutableLiveData<String>();
    MutableLiveData Password = new MutableLiveData<String>();

    public void loginInSever(String name, String body){
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

        NetworkService.getInstance().getJSONApi().postDataPost("/api/posts/create/" + Login.getValue().toString() +
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
}
