package com.example.app_cabelereira_leila.ui;


import static java.lang.Float.parseFloat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.app_cabelereira_leila.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import Model.Cabelereiras;
import ViewHolder.OnListClick;
import adapter.RecycleViewAdapter;

public class Tela_Pricipal extends AppCompatActivity {

    private EditText Nome;
    private EditText Telefone;
    private RatingBar  Avaliacao;
    private Button BtSalvar;
    private Button BtVolvar;
    private Button BtEditar;
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
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
        OnListClick CabelereirasListener = new OnListClick() {
            @Override
            public void onClickDelete(Cabelereiras cabelereiras,int pos) {
                String Nome_da_cabelereira_clicada = cabelereiras.getNome().toString();
                FireBaseRemover(Nome_da_cabelereira_clicada);

                recycleViewAdapter.notifyDataSetChanged();
                FireBaseLer();

            }

            @Override
            public void onClickEditar(Cabelereiras cabelereiras,int pos) {
                String Nome_da_cabelereira_clicada = cabelereiras.getNome().toString();
                Nome.setText(cabelereiras.getNome().toString());
                Telefone.setText(cabelereiras.getTelefone().toString());
                Avaliacao.setRating(cabelereiras.getAvaliacao().floatValue());
                Nome.setText(cabelereiras.getNome().toString());
                BtEditar.setVisibility(View.VISIBLE);
                BtSalvar.setVisibility(View.INVISIBLE);

                BtEditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Nome.getText().toString().isEmpty()==false){
                            Toast.makeText(Tela_Pricipal.this, "Atualizado com sucesso",
                                    Toast.LENGTH_SHORT).show();
                            Cabelereiras cabelereiraAtualizada = new Cabelereiras();
                            cabelereiraAtualizada.setNome(Nome.getText().toString());
                            cabelereiraAtualizada.setTelefone(Telefone.getText().toString());
                            cabelereiraAtualizada.setAvaliacao(Avaliacao.getRating());
                            FireBaseUpdate(Nome_da_cabelereira_clicada, cabelereiraAtualizada);

                            FireBaseLer();
                            recycleViewAdapter.notifyDataSetChanged();
                            limpar_campos();




                            BtEditar.setVisibility(View.INVISIBLE);
                            BtSalvar.setVisibility(View.VISIBLE);
                        }else {
                            Toast.makeText(Tela_Pricipal.this, "Preenca o campo nome",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        };
       recycleViewAdapter = new RecycleViewAdapter(lista,CabelereirasListener);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));




    }
    public void FireBaseRemover(String nome){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir");
        builder.setMessage("Deseja Realmente Excluir ");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String Nome_da_cabelereiara_Remover = nome;
                DatabaseReference cabelereirasRef = databaseReference.child("Cabelereiras");

                Query query = cabelereirasRef.orderByChild("nome").equalTo(Nome_da_cabelereiara_Remover);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            String chaveDoObjetoEncontrado = data.getKey();

                            DatabaseReference cabelereiraRefToDelete = cabelereirasRef.child(chaveDoObjetoEncontrado);

                            cabelereiraRefToDelete.setValue(null);

                            break;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Lidar com erros, se necessário
                        Log.d("FireBase", databaseError.toString());
                    }
                });
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    public void FireBaseUpdate(String nome,Cabelereiras cabelereiras ){
        String Nome_da_cabelereiara_Antigo = nome;
        DatabaseReference cabelereirasRef = databaseReference.child("Cabelereiras");

        Query query = cabelereirasRef.orderByChild("nome").equalTo(Nome_da_cabelereiara_Antigo);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String chaveDoObjetoEncontrado = data.getKey();

                    DatabaseReference cabelereiraRefToUpdate = cabelereirasRef.child(chaveDoObjetoEncontrado);

                    cabelereiraRefToUpdate.setValue(cabelereiras);


                    break;
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Lidar com erros, se necessário
            }
        });


    }

    public void FireBaseSalvar(Cabelereiras cabelereiras){
        DatabaseReference cabelereirasbd =databaseReference.child("Cabelereiras");
        cabelereirasbd.push().setValue(cabelereiras);
        recycleViewAdapter.notifyDataSetChanged();

    }

    public void FireBaseLer(){
        DatabaseReference Cabelereiras =  databaseReference.child("Cabelereiras");
        Cabelereiras.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue()!= null) {
                    Log.i("FireBase", snapshot.getValue().toString());
                    lista.clear();

                    for (DataSnapshot dados : snapshot.getChildren()) {
                        // Log.i("FireBase",dados.child("01").getValue().toString());


                        lista.add(dados.getValue(Model.Cabelereiras.class));
                    }
                    recycleViewAdapter.notifyDataSetChanged();
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
        BtEditar = findViewById(R.id.Id_Bt_Editar);
        BtEditar.setVisibility(View.INVISIBLE);

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

                    if (Nome.getText().toString().isEmpty()) {
                        Toast.makeText(Tela_Pricipal.this,"Preencha o campo Nome",Toast.LENGTH_LONG).show();
                    }else {
                        Cabelereiras cabelereiras = new Cabelereiras();
                        cabelereiras.setNome(Nome.getText().toString());
                        cabelereiras.setTelefone(Telefone.getText().toString());
                        cabelereiras.setAvaliacao(Avaliacao.getRating());

                        FireBaseSalvar(cabelereiras);
                        limpar_campos();
                    }



            }
        });
    }

    public void limpar_campos(){
        Nome.setText("");
        Telefone.setText("");
        Avaliacao.setRating(0);
    }
}