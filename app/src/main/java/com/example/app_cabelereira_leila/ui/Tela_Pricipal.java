package com.example.app_cabelereira_leila.ui;


import static java.lang.Float.parseFloat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.app_cabelereira_leila.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import Model.Cabelereiras;
import adapter.RecycleViewAdapter;

public class Tela_Pricipal extends AppCompatActivity {

    private EditText Nome;
    private EditText Telefone;
    private RatingBar  Avaliacao;
    private Button BtSalvar;
    private Button BtVolvar;
    private RecyclerView recyclerView;
    private List lista;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pricipal);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        VincularXML();
        acoes();
        lista= new LinkedList();
        FireBaseLer();
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(lista);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



    }

    public void FireBaseSalvar(Cabelereiras cabelereiras){
        DatabaseReference cabelereirasbd =databaseReference.child("Cabelereiras");
        cabelereirasbd.push().setValue(cabelereiras);
    }
    public void FireBaseLer(){
        DatabaseReference Cabelereiras =  databaseReference.child("Cabelereiras");
        Cabelereiras.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.i("FireBase",snapshot.getValue().toString());
                for (DataSnapshot dados:snapshot.getChildren()){
                   // Log.i("FireBase",dados.child("01").getValue().toString());

                    lista.add(dados.getValue(Model.Cabelereiras.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("FireBase",error.toString());
            }
        });
    }
    public void VincularXML(){
        Nome = findViewById(R.id.editTextNome);
        Telefone = findViewById(R.id.editTextPhone);
        Avaliacao = findViewById(R.id.ratingBar);
        recyclerView = findViewById(R.id.Recycleview);
        BtSalvar = findViewById(R.id.buttonSalvar);
        BtVolvar = findViewById(R.id.buttonVoltar);

    }
    public void acoes(){

        BtVolvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                FirebaseAuth.getInstance().signOut();
            }
        });
        BtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Cabelereiras cabelereiras = new Cabelereiras();
                    cabelereiras.setNome(Nome.getText().toString());
                    cabelereiras.setTelefone(Telefone.getText().toString());
                    cabelereiras.setAvaliacao(Avaliacao.getRating());

                    FireBaseSalvar(cabelereiras);



            }
        });
    }
}