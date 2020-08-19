package br.edu.ifto.minhabike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Usuario u = new Usuario();
        u.setEmail("usuario@gmail.com");
        u.setSenha("123jks");

        //cadastrar(u);
        autenticado();
        //autenticar(u);
    }

    /**
     * Cadastrar usuário
     */
    public void cadastrar(Usuario u) {
        usuario.createUserWithEmailAndPassword(u.getEmail(), u.getSenha())
                .addOnCompleteListener(UsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                    /**
                     *
                     * @param task objeto de retorno do firebase
                     */
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("NOVO", "Usuário cadastrado!");
                        } else {
                            Log.i("NOVO", "Usuário não cadastrado!");
                        }
                    }
                });
    }

    /**
     * Autenticar usuário
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
                        } else {
                            Log.i("LOGIN", "Dados do usuário inválido!!");
                        }
                    }
                });
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

}