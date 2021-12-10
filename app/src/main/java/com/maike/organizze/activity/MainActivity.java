package com.maike.organizze.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.maike.organizze.R;
import com.maike.organizze.config.ConfiguracaoFirebase;

public class MainActivity extends IntroActivity {

    private FirebaseAuth autenticacao;

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

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btEntrar(View view){
       startActivity(new Intent(this, LoginActivity.class));

    }

    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }

    public void verificarUsuarioLogado(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        if(autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }

    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
    }
}
