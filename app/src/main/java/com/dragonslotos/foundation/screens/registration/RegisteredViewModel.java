package com.dragonslotos.foundation.screens.registration;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dragonslotos.foundation.DTO.SignUpDTO;
import com.dragonslotos.foundation.RetroFit.NetworkService;
import com.dragonslotos.foundation.model.User;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;
import com.vk.api.sdk.VKTokenExpiredHandler;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisteredViewModel extends ViewModel {
    MutableLiveData Login = new MutableLiveData<String>();
    MutableLiveData Password = new MutableLiveData<String>();
    MutableLiveData idVK = new MutableLiveData<String>();
    MutableLiveData Checking = new MutableLiveData<Boolean>();
    MutableLiveData Logging = new MutableLiveData<Boolean>();
    VKAccessToken accessToken;
    public RegisteredViewModel(){
        Checking.setValue(true);
        Logging.setValue(false);
        idVK.setValue("");
    }
    public void startUserRegistration() {
        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setUsername(Login.getValue().toString());
        signUpDTO.setPassword(Password.getValue().toString());
        signUpDTO.setIdVK(idVK.getValue().toString());
        NetworkService.getInstance()
                .getJSONApi()
                .postDataSignUp(signUpDTO)
                .enqueue(new Callback<SignUpDTO>() {
                    @Override
                    public void onResponse(Call<SignUpDTO> call, Response<SignUpDTO> response) {
                        Log.d("Xis", Integer.toString(response.code()));
                        if(response.code() == 400){
                            Checking.setValue(false);
                        }

                    }

                    @Override
                    public void onFailure(Call<SignUpDTO> call, Throwable t) {

                        t.printStackTrace();
                        Logging.setValue(true);
                        Log.d("check", "failru");
                    }
                });
    }
    public void vkRegistration(){
        VKRequest request = new VKRequest("account.getProfileInfo", "5.131");
        VK.execute(request, new VKApiCallback() {

            @Override
            public void fail(@NonNull Exception e) {

            }

            @Override
            public void success(Object o) {
                Log.d("VK", o.toString());
                try {
                    JSONObject obj = new JSONObject(o.toString());
                    String Name = obj.getJSONObject("response").getString("first_name");
                    String LastName = obj.getJSONObject("response").getString("last_name");
                    String id = obj.getJSONObject("response").getString("id");
                    Login.setValue(Name + " " + LastName);
                    Password.setValue(id);
                    idVK.setValue(id);
                    startUserRegistration();
                    Log.d("VK", Name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
