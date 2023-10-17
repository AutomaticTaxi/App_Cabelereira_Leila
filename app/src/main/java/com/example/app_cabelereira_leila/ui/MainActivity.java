package com.example.app_cabelereira_leila.ui;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app_cabelereira_leila.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button BtCadastre_se;
    private Button BtLoginEmail;
    private ImageButton Bt_Ver_Senha;

    private FirebaseAuth mAuth;
    private SignInButton BtLoginGoogle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vincularXMl();
        mAuth = FirebaseAuth.getInstance();
        acoes();

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }
    public void vincularXMl(){

        BtLoginGoogle = findViewById(R.id.Btsigngoogle);
        Email = findViewById(R.id.editTextTextEmailAddress);
        Password = findViewById(R.id.editTextTextPassword);
        BtLoginEmail = findViewById(R.id.BtLoginEmail);
        BtCadastre_se = findViewById(R.id.BtCadastroEmail);
        Bt_Ver_Senha = findViewById(R.id.imageButton);

    }
    public void acoes(){
        Bt_Ver_Senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Password.
            }
        });

        BtCadastre_se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Email.getText().toString().isEmpty()||Password.getText().toString().isEmpty()){
                    Snackbar snackbar = Snackbar.make(view
                            ,"Os Campos devem ser preenchidos"
                            , BaseTransientBottomBar.LENGTH_SHORT);
                    snackbar.show();
                }else{


                }
            }
        });
        BtLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        BtLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Email.getText().toString().isEmpty()||Password.getText().toString().isEmpty()){
                    Snackbar snackbar = Snackbar.make(view
                            ,"Os Campos devem ser preenchidos"
                            , BaseTransientBottomBar.LENGTH_SHORT);
                    snackbar.show();
                }else{
                LoginEmailSenha(Email.getText().toString(),Password.getText().toString());}
            }
        });
    }
    private void limpaCampos (){
        Email.setText("");
        Password.setText("");
    }
    public void LoginEmailSenha(String email, String senha ){
        mAuth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            limpaCampos();
                            Intent intent =new Intent(MainActivity.this,Tela_Pricipal.class);
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }
}