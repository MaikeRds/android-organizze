package com.maike.organizze;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.maike.organizze.activity.CadastroActivity;
import com.maike.organizze.activity.LoginActivity;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        setButtonNextVisible(false);
        setButtonBackVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .backgroundDark(android.R.color.white)
                .fragment(R.layout.intro_1, R.style.AppTheme)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .backgroundDark(android.R.color.white)
                .fragment(R.layout.intro_2, R.style.AppTheme)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .backgroundDark(android.R.color.white)
                .fragment(R.layout.intro_3, R.style.AppTheme)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .backgroundDark(android.R.color.white)
                .fragment(R.layout.intro_4, R.style.AppTheme)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .backgroundDark(android.R.color.white)
                .fragment(R.layout.intro_cadastro, R.style.AppTheme)
                .canGoForward(false)
                .build());
    }

    public void btEntrar(View view){
       startActivity(new Intent(this, LoginActivity.class));

    }

    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}
