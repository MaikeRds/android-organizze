package com.maike.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.maike.organizze.R;
import com.maike.organizze.helper.DateCustom;
import com.maike.organizze.model.Movimentacao;
import com.maike.organizze.model.Usuario;
import com.maike.organizze.repository.MovimentacaoRepository;
import com.maike.organizze.repository.UsuarioRepository;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText editData, editCategoria, editDescricao;
    private EditText editValor;
    private Movimentacao movimentacao;
    private MovimentacaoRepository movimentacaoRepository;
    private UsuarioRepository usuarioRepository;
    private Double despesaTotal;
    private Double despesaGerada;
    private Double despesaAtualizada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        editData      = findViewById(R.id.editData);
        editCategoria = findViewById(R.id.editCategoria);
        editDescricao = findViewById(R.id.editDescricao);
        editValor     = findViewById(R.id.editValor);

        //Preencher o campo data co a date atual
        editData.setText(DateCustom.dataAtual());

        movimentacaoRepository = new MovimentacaoRepository();
        usuarioRepository = new UsuarioRepository();

        recuperarDespesaTotal();
    }

    public void salvarDespesa(View view) {

        if(validarCamposDespesa()){

            movimentacao = new Movimentacao();

            Double valorRecuperado = Double.parseDouble(editValor.getText().toString());
            movimentacao.setValor(valorRecuperado);
            movimentacao.setCategoria(editCategoria.getText().toString());
            movimentacao.setDescricao(editDescricao.getText().toString());
            movimentacao.setData(editData.getText().toString());
            movimentacao.setTipo("D");

            despesaGerada = valorRecuperado;
            despesaAtualizada = despesaTotal + despesaGerada;

            movimentacaoRepository.salvar(movimentacao);
            usuarioRepository.atualizarDespesaTotal(despesaAtualizada);
        }
    }

    public Boolean validarCamposDespesa(){

        if(editValor.getText().toString().isEmpty()){
            Toast.makeText(DespesasActivity.this, "Valor não foi preenchido!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(editCategoria.getText().toString().isEmpty()){
            Toast.makeText(DespesasActivity.this, "Categoria não foi preenchido!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(editDescricao.getText().toString().isEmpty()){
            Toast.makeText(DespesasActivity.this, "Descrição não foi preenchido!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(editData.getText().toString().isEmpty()){
            Toast.makeText(DespesasActivity.this, "Data não foi preenchido!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void recuperarDespesaTotal(){
        DatabaseReference usuarioRef = usuarioRepository.usuarioByIdDatabaseReference();
        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
