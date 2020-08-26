package br.edu.ifto.minhabike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.edu.ifto.minhabike.adapter.BikeAdapter;
import br.edu.ifto.minhabike.entity.Bicicleta;

public class MainActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    ListView listView;
    BikeAdapter mAdapter;
    ArrayList<Bicicleta> bicicletaArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Minha Bike");

        listView =  findViewById(R.id.ltViewMain);
        //Buscando Lista de Bicicletas Cadastradas
        bicicletaArrayList = new ArrayList<>();
        database.child("bicicleta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bicicletaArrayList.clear();
                for (DataSnapshot dado : dataSnapshot.getChildren()){
                    Bicicleta b =dado.getValue(Bicicleta.class);
                    bicicletaArrayList.add(b);
                }
                    mAdapter = new BikeAdapter(getApplicationContext(),bicicletaArrayList);
                    listView.setAdapter(mAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void acaoAddBike(View view) {
        Intent intent = new Intent(this, BikeCadastroActivity.class);
        startActivity(intent);
    }

}