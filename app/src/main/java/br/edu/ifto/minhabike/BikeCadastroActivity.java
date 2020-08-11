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

import java.util.ArrayList;
import java.util.List;

public class BikeCadastroActivity extends AppCompatActivity {

    Spinner tiposBikes,marcasBike;
    ArrayAdapter adapter;


    //Cadastro da Bicicleta
    TextView modelo;
    EditText peso, notas;
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

    }
}