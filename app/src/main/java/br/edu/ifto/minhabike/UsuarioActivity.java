package br.edu.ifto.minhabike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.edu.ifto.minhabike.entity.Usuario;

public class UsuarioActivity extends AppCompatActivity {

    /**
     * Objeto para manipular ações com usuários
     */
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    private TextView email, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        getSupportActionBar().setTitle("Login");

        email = findViewById(R.id.idLoginEmail);
        pass = findViewById(R.id.idLoginSenha);

    }

    public void autenticar(View view) {
        Usuario u = new Usuario();
        u.setEmail(email.getText().toString());
        u.setSenha(pass.getText().toString());
        if (u.getEmail().equals("") || u.getSenha().equals("")){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
            builder1.setMessage("Preencha Todos os Campos !!!");
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }else{
            autenticar(u);
        }

    }

    /**
     * Autenticar usuário
     *
     * @param u
     * @return
     */
    public void autenticar(Usuario u) {
        usuario.signInWithEmailAndPassword(u.getEmail(), u.getSenha())
                .addOnCompleteListener(UsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                    /**
                     * @param task objeto de retorno do firebase
                     */
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("LOGIN", "Login efetuado!");
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Log.i("LOGIN", "Dados do usuário inválido!!");
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                            builder1.setMessage("Dados do usuário inválido!!");
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        }
                    }
                });
    }

    public Context getActivity() {
        return this;
    }

    public void autenticado() {
        if (usuario.getCurrentUser() != null)
            Log.i("LOGADO", "Usuário Logado!" + usuario.getCurrentUser().getEmail());
        else
            Log.i("LOGADO", "Usuário NÃO Logado!");
    }

    public void logout() {
        usuario.signOut();
    }

    public void cadastarUsuario(View view) {
        Intent intent = new Intent(this, CadastroUsuarioActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}