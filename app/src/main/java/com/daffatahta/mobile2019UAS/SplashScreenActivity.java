package com.daffatahta.mobile2019UAS;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.daffatahta.mobile2019UAS.activities.login.Login;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen conf = new EasySplashScreen(SplashScreenActivity.this)
                .withBackgroundColor(Color.parseColor("#000000"))
                .withTargetActivity(Login.class)
                .withSplashTimeOut(3000)
                .withLogo(R.mipmap.splashlogo)
                .withFooterText("Daffa Tahta A 1741720152")
                .withAfterLogoText("SplashScreen");

        conf.getFooterTextView().setTextColor(Color.WHITE);
        View v = conf.create();
        setContentView(v);
    }
}
