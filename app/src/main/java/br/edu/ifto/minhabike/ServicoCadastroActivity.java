package br.edu.ifto.minhabike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class ServicoCadastroActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    Spinner servico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico_cadastro);
        getSupportActionBar().setTitle("Cadastro Serviço");

        servico = findViewById(R.id.spinnerTipoServico);

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

        adapter = new ArrayAdapter(this,R.layout.spinner_item, servicos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        servico.setAdapter(adapter);
    }

    public void cadastarServico(View view) {
    }
}