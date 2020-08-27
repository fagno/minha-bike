package br.edu.ifto.minhabike;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifto.minhabike.entity.Servico;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServicoCadastroActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayAdapter adapter;
    Spinner servico;
    TextView bikeServico;
    EditText valor,descricao;
    TextInputEditText km;
    Button cadastro,atualizar;
    String nomeBike,data,uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent =getIntent();
        servico = findViewById(R.id.spinnerTipoServico);
        km = findViewById(R.id.idKmAtual);
        valor = findViewById(R.id.idValor);
        descricao = findViewById(R.id.idDescricao);
        bikeServico = findViewById(R.id.idBikeServico);
        cadastro = findViewById(R.id.btnCadastroServico);
        atualizar = findViewById(R.id.btnAtualizarServico);
        bikeServico.setText(intent.getStringExtra("modeloBike"));
        nomeBike = intent.getStringExtra("nomeBike");
        data = intent.getStringExtra("servicoData");
        uid = intent.getStringExtra("uidServico");


    if(intent.getStringExtra("servicoBike")!=null){
        nomeBike = intent.getStringExtra("nomeBike");
        km.setText(intent.getStringExtra("servicoKm"));
        descricao.setText(intent.getStringExtra("servicoDescricao"));
        valor.setText(intent.getStringExtra("servicoValor"));
        bikeServico.setText(intent.getStringExtra("servicoBike"));
        getSupportActionBar().setTitle("Atualizar  Serviço");
        cadastro.setVisibility(View.GONE);
        atualizar.setVisibility(View.VISIBLE);
    }else {
        getSupportActionBar().setTitle("Cadastro de Serviço");
    }


        final List<String> servicos = new ArrayList<>();
        servicos.add("Manutenção");
        servicos.add("Troca De Componente");
        servicos.add("Acessório");

        adapter = new ArrayAdapter(this,R.layout.spinner_item, servicos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        servico.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void cadastarServico(View view) {
        LocalDate agora = LocalDate.now();
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DatabaseReference servicos = database.child("servico");
        Servico s = new Servico();
        s.setBikeServico(bikeServico.getText().toString());
        s.setTipo(servico.getSelectedItem().toString());
        s.setData(String.valueOf(formatterData.format(agora)));
        s.setDescricao(descricao.getText().toString());
        s.setKmAtual(km.getText().toString());
        s.setBikeNome(nomeBike);
        s.setValor(valor.getText().toString());
        s.setUid(servicos.push().getKey());


        servicos.child(s.getUid()).setValue(s);

        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void acaoAtualizar(View view) {

        final Servico s = new Servico();
        s.setBikeNome(nomeBike);
        s.setBikeServico(bikeServico.getText().toString());
        s.setTipo(servico.getSelectedItem().toString());
        s.setData(data);
        s.setDescricao(descricao.getText().toString());
        s.setKmAtual(km.getText().toString());
        s.setValor(valor.getText().toString());
        s.setUid(uid);

        final Query query = database.child("servico").orderByChild("nomeBike").equalTo(nomeBike);
        query.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                for (DataSnapshot ex : dataSnapshot.getChildren()) {
                    Toast.makeText(getApplicationContext(),"Servico Atualizado !!!", Toast.LENGTH_LONG).show();
                    database.child("servico").child(s.getUid()).setValue(s);

//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }
}