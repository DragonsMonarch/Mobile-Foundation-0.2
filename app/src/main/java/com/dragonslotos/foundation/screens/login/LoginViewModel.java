package com.dragonslotos.foundation.screens.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dragonslotos.foundation.DTO.LoginDto;
import com.dragonslotos.foundation.RetroFit.NetworkService;

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
}
