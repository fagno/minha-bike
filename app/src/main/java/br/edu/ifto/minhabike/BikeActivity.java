package br.edu.ifto.minhabike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BikeActivity extends AppCompatActivity {
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    TextView nome, modelo, marca, tipo, peso, notas;
    String nomeBike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);
        getSupportActionBar().setTitle("Dados Bicicleta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        nome = findViewById(R.id.idDetalhesNomeBike);
        modelo = findViewById(R.id.idDetalhesModeloBike);
        marca = findViewById(R.id.idDetalhesBikeMarca);
        tipo = findViewById(R.id.idDetalhesBikeTipo);
        peso = findViewById(R.id.idDetalhesBikePeso);
        notas = findViewById(R.id.idDetalhesBikeNotas);

        nomeBike = intent.getStringExtra("bikeNome");

        nome.setText(intent.getStringExtra("bikeNome"));
        modelo.setText(intent.getStringExtra("bikeModelo"));
        marca.setText(intent.getStringExtra("bikeMarca"));
        tipo.setText(intent.getStringExtra("bikeTipo"));
        peso.setText(intent.getStringExtra("bikePeso"));
        notas.setText(intent.getStringExtra("bikeNotas"));
    }

    public void acaoEditarBike(View view) {
        Intent intent = new Intent(view.getContext(), BikeCadastroActivity.class);
        intent.putExtra("bNome", nome.getText().toString());
        intent.putExtra("bModelo", modelo.getText().toString());
        intent.putExtra("bTipo", tipo.getText().toString());
        intent.putExtra("bPeso", peso.getText().toString());
        intent.putExtra("bNotas", notas.getText().toString());
        intent.putExtra("bMarca", marca.getText().toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        view.getContext().startActivity(intent);
    }

    public void acaoExcluirBike(View v) {
        Query apagar = database.child("bicicleta").child(nomeBike);
        apagar.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ex : dataSnapshot.getChildren()) {
                    ex.getRef().removeValue();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        v.getContext().startActivity(intent);
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
//}public Dialog onCreateDialog(Bundle savedInstanceState) {
//    // Use the Builder class for convenient dialog construction
//    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//    builder.setMessage(R.string.dialog_fire_missiles)
//            .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    // FIRE ZE MISSILES!
//                }
//            })
//            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    // User cancelled the dialog
//                }
//            });
//    // Create the AlertDialog object and return it
//    return builder.create();