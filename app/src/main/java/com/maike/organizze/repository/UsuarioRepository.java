package com.maike.organizze.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.maike.organizze.config.ConfiguracaoFirebase;
import com.maike.organizze.helper.Base64Custom;
import com.maike.organizze.model.Usuario;

public class UsuarioRepository {
    private FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();

    public void salvar(Usuario usuario){
        databaseReference.child("usuarios").child(usuario.getIdUsuario()).setValue(usuario);
    }

    public DatabaseReference usuarioByIdDatabaseReference() {
        String emailUsuario = auth.getCurrentUser().getEmail();
        String IdUsuario = Base64Custom.codificarBase64(emailUsuario);

        return databaseReference.child("usuarios").child(IdUsuario);
    }

    public void atualizarDespesaTotal(Double despesa){
        String emailUsuario = auth.getCurrentUser().getEmail();
        String IdUsuario = Base64Custom.codificarBase64(emailUsuario);

        databaseReference.child("usuarios").child(IdUsuario).child("despesaTotal").setValue(despesa);
    }
}
