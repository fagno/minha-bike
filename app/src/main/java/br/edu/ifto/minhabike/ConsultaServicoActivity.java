package br.edu.ifto.minhabike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.edu.ifto.minhabike.adapter.ServicosAdapter;
import br.edu.ifto.minhabike.entity.Servico;

public class ConsultaServicoActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    RecyclerView recyclerView;
    ServicosAdapter mAdapter;
    ArrayList<Servico> servicosArrayList;
    TextView bikeModelo, bikeTipo, bikeMarca;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_servico);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Serviços");

        bikeModelo = findViewById(R.id.idCardServicoModelo);
        bikeTipo = findViewById(R.id.idCardServicoTipo);
        bikeMarca = findViewById(R.id.idCardServicoMarca);
        recyclerView = (RecyclerView) findViewById(R.id.rcvServico);

        Intent intent = getIntent();
        bikeModelo.setText(intent.getStringExtra("modeloBike"));
        bikeMarca.setText(intent.getStringExtra("marcaBike"));
        bikeTipo.setText(intent.getStringExtra("tipoBike"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Buscando Lista de Serviços cadastrados que tenham o mesmo modelo de bike

        servicosArrayList = new ArrayList<>();
        database.child("servico").orderByChild("bikeServico").equalTo(bikeModelo.getText().toString())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        servicosArrayList.clear();
                        for (DataSnapshot dado : dataSnapshot.getChildren()) {
                            Servico s = dado.getValue(Servico.class);
                            servicosArrayList.add(s);
                        }
                        mAdapter = new ServicosAdapter(servicosArrayList, getApplicationContext());
                        recyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void acaoFiltro(View view) {

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