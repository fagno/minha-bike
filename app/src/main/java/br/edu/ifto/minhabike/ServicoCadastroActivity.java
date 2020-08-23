package br.edu.ifto.minhabike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import br.edu.ifto.minhabike.entity.MaskEdit;
import br.edu.ifto.minhabike.entity.Servico;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ServicoCadastroActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayAdapter adapter;
    Spinner servico;
    EditText valor,descricao;
    TextInputEditText data,acessorio,componente,km;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico_cadastro);
        getSupportActionBar().setTitle("Cadastro Serviço");

        servico = findViewById(R.id.spinnerTipoServico);
        data = findViewById(R.id.idData);
        acessorio = findViewById(R.id.idAcessorio);
        componente = findViewById(R.id.idComponenteTroca);
        km = findViewById(R.id.idKmAtual);
        valor = findViewById(R.id.idValor);
        descricao = findViewById(R.id.idDescricao);

        final List<String> servicos = new ArrayList<>();
        servicos.add("Lavagem da Bicicleta");
        servicos.add("Troca de Cassete");
        servicos.add("Troca de Catraca");
        servicos.add("Troca de Corrente");
        servicos.add("Troca de Câmara");
        servicos.add("Troca de Pneu");
        servicos.add("Troca de Remendo");
        servicos.add("Regulagem de Freios");
        servicos.add("Revisão Programada");
        servicos.add("Regulagem de Cambios");
        servicos.add("Ajuste de Folgas");
        servicos.add("Reaperto Geral");
        servicos.add("Manutenção dos Cubos");
        servicos.add("Alinhamento de Rodas");
        servicos.add("Lubrificação de cabos");
        servicos.add("Desmontagem Geral");
        servicos.add("Regulagem Geral");
        servicos.add("Substituição dos Cabos/Conduítes");
        servicos.add("Montagem Geral");
        servicos.add("Desempeno de roda");
        servicos.add("Revisão de suspensão hidráulica");
        servicos.add("Serviço de Enraiação");
        servicos.add("Regulagem/Lubrificação de marcha ");
        servicos.add("Instalação de Garfo");
        servicos.add("Solda");

        TextWatcher mask = MaskEdit.mask(data, "##/##/####");
        data.addTextChangedListener(mask);

        adapter = new ArrayAdapter(this,R.layout.spinner_item, servicos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        servico.setAdapter(adapter);
    }

    public void cadastarServico(View view) {
        Servico s = new Servico();
        s.setTipo(servico.getSelectedItem().toString());
        s.setAcessorio(acessorio.getText().toString());
        s.setComponenteTroca(componente.getText().toString());
        s.setData(data.getText().toString());
        s.setDescricao(descricao.getText().toString());
        s.setKmAtual(km.getText().toString());
        s.setValor(valor.getText().toString());

        DatabaseReference servicos = database.child("servico");
        servicos.push().setValue(s);

        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}