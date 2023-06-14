package com.dragonslotos.foundation.screens.login;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dragonslotos.foundation.DTO.LoginDto;
import com.dragonslotos.foundation.RetroFit.NetworkService;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;
import com.vk.api.sdk.requests.VKRequest;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    MutableLiveData Login = new MutableLiveData<String>();
    MutableLiveData Password = new MutableLiveData<String>();
    MutableLiveData EnterSystem = new MutableLiveData<Boolean>();
    MutableLiveData LoggingError = new MutableLiveData<Boolean>();
    boolean log = false;
    int code = 0;
    public LoginViewModel(){
        EnterSystem.setValue(false);

    }
    public void enterSystem(){
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(Login.getValue().toString());
        loginDto.setPassword(Password.getValue().toString());

        NetworkService.getInstance().getJSONApi().postDataSignIn(loginDto).enqueue(
                new Callback<LoginDto>() {
                    @Override
                    public void onResponse(Call<LoginDto> call, Response<LoginDto> response) {

                        LoggingError.setValue(true);
                    }

                    @Override
                    public void onFailure(Call<LoginDto> call, Throwable t) {
                        EnterSystem.setValue(true);


                    }

                }
        );

    }
    public void vkLogin(){
        VKRequest request = new VKRequest("account.getProfileInfo", "5.131");
        VK.execute(request, new VKApiCallback() {

            @Override
            public void fail(@NonNull Exception e) {

            }

            @Override
            public void success(Object o) {
                try {
                    JSONObject obj = new JSONObject(o.toString());
                    String Name = obj.getJSONObject("response").getString("first_name");
                    String LastName = obj.getJSONObject("response").getString("last_name");
                    String id = obj.getJSONObject("response").getString("id");
                    Login.setValue(Name + " " + LastName);
                    Password.setValue(id);
                    enterSystem();
                    Log.d("VK", Name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
