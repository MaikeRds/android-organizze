package com.maike.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.maike.organizze.R;
import com.maike.organizze.helper.DateCustom;
import com.maike.organizze.model.Movimentacao;
import com.maike.organizze.repository.MovimentacaoRepository;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText editData, editCategoria, editDescricao;
    private EditText editValor;
    private Movimentacao movimentacao;
    private MovimentacaoRepository movimentacaoRepository;

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
    }

    public void salvarDespesa(View view) {
        movimentacao = new Movimentacao();
        movimentacao.setValor(Double.parseDouble(editValor.getText().toString()));
        movimentacao.setCategoria(editCategoria.getText().toString());
        movimentacao.setDescricao(editDescricao.getText().toString());
        movimentacao.setData(editData.getText().toString());
        movimentacao.setTipo("D");

        movimentacaoRepository.salvar(movimentacao);
    }
}
