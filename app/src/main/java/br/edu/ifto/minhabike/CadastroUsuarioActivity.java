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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.edu.ifto.minhabike.entity.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    private TextView user,email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        user = findViewById(R.id.idCadastroUsuario);
        email = findViewById(R.id.idCadastroEmail);
        pass = findViewById(R.id.idCadastroSenha);


    }


    public void cadastrarUsuario(View view) {
        Usuario usuario = new Usuario();
        usuario.setNome(user.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setSenha(pass.getText().toString());

        cadastrar(usuario);
        DatabaseReference usuarios = database.child("usuario");
        usuarios.push().setValue(usuario);
    }
    /**
     * Cadastrar usuário
     */

    public void cadastrar(Usuario u) {
        usuario.createUserWithEmailAndPassword(u.getEmail(), u.getSenha())
                .addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                    /**
                     *
                     * @param task objeto de retorno do firebase
                     */
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("NOVO", "Usuário cadastrado!");
                            Toast.makeText(getActivity(), "Usuário cadastrado!!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(),UsuarioActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            Log.i("NOVO", "Usuário não cadastrado!");
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                            builder1.setMessage("Usuário não cadastrado!!");
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        }
                    }
                });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, UsuarioActivity.class));
                finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }
    public Context getActivity(){
        return this;
    }
}