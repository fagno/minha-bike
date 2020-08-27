package br.edu.ifto.minhabike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
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
import br.edu.ifto.minhabike.entity.Servico;
import br.edu.ifto.minhabike.entity.Tipo;

public class BikeCadastroActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    Spinner tiposBikes, marcasBike;
    ArrayAdapter adapter;

    ArrayList<String> marcas;
    ArrayList<String> tipos;
    //Cadastro da Bicicleta
    TextView txtNBike;
    EditText peso, notas, nomeBike;
    TextInputEditText modelo;
    LinearLayout linearLayout;
    Button salvar, editar;
    String nBike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        tiposBikes = findViewById(R.id.spinnerTipoBike);
        marcasBike = findViewById(R.id.spinnerMarcaBike);
        //Atribuindo ID do cadastro
        modelo = findViewById(R.id.idBikeModelo);
        peso = findViewById(R.id.idBikePeso);
        notas = findViewById(R.id.editTextTextMultiLine);
        nomeBike = findViewById(R.id.editNomeBike);
        txtNBike = findViewById(R.id.idNomeBikeAtt);
        linearLayout = findViewById(R.id.idllEditar);
        salvar = findViewById(R.id.btnSalvarBike);
        editar = findViewById(R.id.btnEditarBike);

        if (intent.getStringExtra("bNome") != null) {
            nBike = intent.getStringExtra("bNome");
            txtNBike.setText(intent.getStringExtra("bNome"));
            modelo.setText(intent.getStringExtra("bModelo"));
            peso.setText(intent.getStringExtra("bPeso"));
            notas.setText(intent.getStringExtra("bNotas"));
            getSupportActionBar().setTitle("Editar Bicicleta");
            nomeBike.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            salvar.setVisibility(View.GONE);
            editar.setVisibility(View.VISIBLE);
        } else {
            getSupportActionBar().setTitle("Cadastrar Bicicleta");
        }


//        Buscando Lista de Marcas no FireBase
        marcas = new ArrayList<String>();
        Query marcasB = database.child("marcas").orderByChild("nome");
        marcasB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                marcas.clear();
                for (DataSnapshot dado : dataSnapshot.getChildren()) {
                    Marcas b = dado.getValue(Marcas.class);
                    marcas.add(b.getNome());
                }
                adapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, marcas);
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
                for (DataSnapshot dado : dataSnapshot.getChildren()) {
                    Tipo b = dado.getValue(Tipo.class);
                    tipos.add(b.getTipo());
                }
                adapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, tipos);
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
        final Bicicleta bicicleta = new Bicicleta();
        bicicleta.setMarca(marcasBike.getSelectedItem().toString());
        bicicleta.setModelo(modelo.getText().toString());
        bicicleta.setTipo(tiposBikes.getSelectedItem().toString());
        bicicleta.setPeso(peso.getText().toString());
        bicicleta.setNotas(notas.getText().toString());
        bicicleta.setNome(nomeBike.getText().toString());

        Query nomeBike = database.child("bicicleta").orderByChild("nome").equalTo(bicicleta.getNome());
        nomeBike.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //se cair aqui, significa que encontrou um nick igual
                    Toast.makeText(BikeCadastroActivity.this,
                            "Esse nome já  existe, escolha outro por favor !",
                            Toast.LENGTH_LONG).show();
                } else {
                    DatabaseReference bikes = database.child("bicicleta").child(bicicleta.getNome());
                    bikes.setValue(bicicleta);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void acaoEditar(View view) {
        final Bicicleta b = new Bicicleta();
        b.setNome(nBike);
        b.setModelo(modelo.getText().toString());
        b.setMarca(marcasBike.getSelectedItem().toString());
        b.setTipo(tiposBikes.getSelectedItem().toString());
        b.setPeso(peso.getText().toString());
        b.setNotas(notas.getText().toString());

        Query query = database.child("bicicleta").orderByChild("nome").equalTo(b.getNome());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                for (DataSnapshot ex : dataSnapshot.getChildren()) {
                Toast.makeText(BikeCadastroActivity.this, "Dados Editados", Toast.LENGTH_LONG).show();
                database.child("bicicleta").child(nBike).setValue(b);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}