package br.edu.ifto.minhabike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Minha Bike");
    }

    public void acaoAddBike(View view) {
        Intent intent = new Intent(this, BikeCadastroActivity.class);
        startActivity(intent);
    }
}