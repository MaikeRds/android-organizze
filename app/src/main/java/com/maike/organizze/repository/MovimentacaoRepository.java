package com.maike.organizze.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.maike.organizze.config.ConfiguracaoFirebase;
import com.maike.organizze.helper.Base64Custom;
import com.maike.organizze.helper.DateCustom;
import com.maike.organizze.model.Movimentacao;
import com.maike.organizze.model.Usuario;

public class MovimentacaoRepository {

    private DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();

    public void salvar(Movimentacao movimentacao){

        String idUsuario = Base64Custom.codificarBase64(auth.getCurrentUser().getEmail());

        databaseReference.child("movimentacao")
                .child(idUsuario)
                .child(DateCustom.mesAnoDataEscolhida(movimentacao.getData()))
                .push()
                .setValue(movimentacao);
    }

    public void recuperarDespesaTotal(){

    }
}
