package com.maike.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.maike.organizze.R;
import com.maike.organizze.config.ConfiguracaoFirebase;
import com.maike.organizze.helper.Base64Custom;
import com.maike.organizze.model.Usuario;
import com.maike.organizze.repository.UsuarioRepository;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome, editEmail, editSenha;
    private Button botaoCadastrar;

    private FirebaseAuth autenticacao;
    private Usuario usuario;

    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);



        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);

        botaoCadastrar = findViewById(R.id.buttonCadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textNome = editNome.getText().toString();
                String textEmail = editEmail.getText().toString();
                String textSenha = editSenha.getText().toString();

                //Validar se os campos foram preenchidos

                if (!textNome.isEmpty()) {
                    if (!textEmail.isEmpty()) {
                        if (!textSenha.isEmpty()) {

                            usuario = new Usuario();
                            usuario.setNome(textNome);
                            usuario.setEmail(textEmail);
                            usuario.setSenha(textSenha);

                            cadastrarUsuarios(usuario);
                        } else {
                            Toast.makeText(CadastroActivity.this, "Preencha o senha!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this, "Preencha o email!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void cadastrarUsuarios(Usuario usuario){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);
                    usuarioRepository.salvar(usuario);
                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch ( FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";
                    }catch ( FirebaseAuthInvalidCredentialsException e){
                        excecao = "Por favor, digite um e-mail válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Essa conta já foi cadastrada";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage() ;
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
