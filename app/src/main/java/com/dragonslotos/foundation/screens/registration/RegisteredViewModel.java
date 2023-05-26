package com.dragonslotos.foundation.screens.registration;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dragonslotos.foundation.DTO.SignUpDTO;
import com.dragonslotos.foundation.RetroFit.NetworkService;
import com.dragonslotos.foundation.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisteredViewModel extends ViewModel {
    MutableLiveData Login = new MutableLiveData<String>();
    MutableLiveData Password = new MutableLiveData<String>();
    MutableLiveData Checking = new MutableLiveData<Boolean>();
    MutableLiveData Logging = new MutableLiveData<Boolean>();

    public RegisteredViewModel(){
        Checking.setValue(true);
        Logging.setValue(false);
    }
    public void startUserRegistration() {
        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setUsername(Login.getValue().toString());
        signUpDTO.setPassword(Password.getValue().toString());
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
}
