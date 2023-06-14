package com.dragonslotos.foundation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKTokenExpiredHandler;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.api.sdk.auth.VKAuthCallback;
import com.vk.api.sdk.utils.VKUtils;

public class MainActivity extends AppCompatActivity {
    static VKAccessToken AccessToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VK.addTokenExpiredHandler(tokenExpiredHandler);
        VK.initialize(this);
        String[] fingerprints = VKUtils.getCertificateFingerprint(this, this.getPackageName());
        for (String i : fingerprints){
            Log.d("VK", i);
        }
    }
    public void changeFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public VKAccessToken requireToken(){
        return AccessToken;
    }
    public void switchContent(int id, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(id, fragment, fragment.toString());
        ft.addToBackStack(null);
        ft.commit();
    }
    VKTokenExpiredHandler tokenExpiredHandler = new VKTokenExpiredHandler() {
        @Override
        public void onTokenExpired() {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        VKAuthCallback callback = new VKAuthCallback() {
            @Override
            public void onLogin(@NonNull VKAccessToken vkAccessToken) {

            }

            @Override
            public void onLoginFailed(int i) {

            }
        };
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)){
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}