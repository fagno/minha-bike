package br.edu.ifto.minhabike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifto.minhabike.entity.Bicicleta;
import br.edu.ifto.minhabike.entity.Marcas;
import br.edu.ifto.minhabike.entity.Tipo;

public class BikeCadastroActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    Spinner tiposBikes,marcasBike;
    ArrayAdapter adapter;

    ArrayList<String> marcas;
    ArrayList<String> tipos;
    //Cadastro da Bicicleta
    TextView modelo;
    EditText peso, notas,nomeBike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Cadastrar Bicicleta");

        tiposBikes = findViewById(R.id.spinnerTipoBike);
        marcasBike  = findViewById(R.id.spinnerMarcaBike);
        //Atribuindo ID do cadastro
        modelo = findViewById(R.id.idBikeModelo);
        peso = findViewById(R.id.idBikePeso);
        notas = findViewById(R.id.editTextTextMultiLine);
        nomeBike = findViewById(R.id.editNomeBike);

//        Buscando Lista de Marcas no FireBase
        marcas = new ArrayList<String>();
        Query marcasB = database.child("marcas").orderByChild("nome");
        marcasB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                marcas.clear();
                for (DataSnapshot dado : dataSnapshot.getChildren()){
                    Marcas b =dado.getValue(Marcas.class);
                    marcas.add(b.getNome());
                }
                adapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_item, marcas);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                marcasBike.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        Buscando Lista de tipos no FireBase
        tipos = new ArrayList<String>();
        Query tiposBike = database.child("tipos").orderByChild("tipo");
        tiposBike.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tipos.clear();
                for (DataSnapshot dado : dataSnapshot.getChildren()){
                    Tipo b = dado.getValue(Tipo.class);
                    tipos.add(b.getTipo());
                }
                adapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_item, tipos);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                tiposBikes.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

    public void cadastarBicicleta(View view) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
        builder1.setMessage("Preencha Todos os Campos !!!");
        AlertDialog alert11 = builder1.create();
        alert11.show();

//        Bicicleta bicicleta = new Bicicleta();
//        bicicleta.setMarca(marcasBike.getSelectedItem().toString());
//        bicicleta.setModelo(modelo.getText().toString());
//        bicicleta.setTipo(tiposBikes.getSelectedItem().toString());
//        bicicleta.setPeso(Float.valueOf(peso.getText().toString()));
//        bicicleta.setNotas(notas.getText().toString());
//
//        DatabaseReference bikes = database.child("bicicleta");
//        bikes.push().setValue(bicicleta);
//
//        Intent intent = new Intent(this,MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
    }
}