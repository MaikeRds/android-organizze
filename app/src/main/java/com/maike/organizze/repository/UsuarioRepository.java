package com.maike.organizze.repository;

import com.google.firebase.database.DatabaseReference;
import com.maike.organizze.config.ConfiguracaoFirebase;
import com.maike.organizze.model.Usuario;

public class UsuarioRepository {
    private DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();

    public void salvar(Usuario usuario){
        databaseReference.child("usuarios").child(usuario.getIdUsuario()).setValue(usuario);
    }
}
