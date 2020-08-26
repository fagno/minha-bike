package br.edu.ifto.minhabike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifto.minhabike.entity.Bicicleta;

public class BikeCadastroActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    Spinner tiposBikes,marcasBike;
    ArrayAdapter adapter;


    //Cadastro da Bicicleta
    TextView modelo;
    EditText peso, notas, nomeBike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Cadastrar Bicicleta");

        //Atribuindo ID do cadastro
        tiposBikes = findViewById(R.id.spinnerTipoBike);
        marcasBike  = findViewById(R.id.spinnerMarcaBike);
        modelo = findViewById(R.id.idBikeModelo);
        peso = findViewById(R.id.idBikePeso);
        notas = findViewById(R.id.editTextTextMultiLine);
        nomeBike = findViewById(R.id.editNomeBike);

        final List<String> tipos = new ArrayList<>();
        tipos.add("Bicicleta BMX");
        tipos.add("Bicicleta Estrada");
        tipos.add("Bicicleta Corrida");
        tipos.add("Bicicleta Eletrica");
        tipos.add("Bicicleta TT");
        tipos.add("Bicicleta Hibrida");
        tipos.add("Mountain Bike");
        tipos.add("Bicicleta Cross");

        final List<String> marcas = new ArrayList<>();
        marcas.add("Sense");
        marcas.add("Caloi");
        marcas.add("Audax");
        marcas.add("Soul");
        marcas.add("Groove");
        marcas.add("Oggi");
        marcas.add("TSW");
        marcas.add("RAVA");
        marcas.add("Dropp");
        marcas.add("Sutton");
        marcas.add("Rino");
        marcas.add("KSW");
        marcas.add("KLS");
        marcas.add("XKS");
        marcas.add("Benoá");


        adapter = new ArrayAdapter(this,R.layout.spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tiposBikes.setAdapter(adapter);

        adapter = new ArrayAdapter(this,R.layout.spinner_item, marcas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        marcasBike.setAdapter(adapter);

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
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setMarca(marcasBike.getSelectedItem().toString());
        bicicleta.setModelo(modelo.getText().toString());
        bicicleta.setTipo(tiposBikes.getSelectedItem().toString());
        bicicleta.setPeso(Float.valueOf(peso.getText().toString()));
        bicicleta.setNotas(notas.getText().toString());
        bicicleta.setNome(nomeBike.getText().toString());

        DatabaseReference bikes = database.child("bicicleta");
        bikes.push().setValue(bicicleta);

        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}