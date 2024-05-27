package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    // Firebase Auth
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;
    // FirebaseFireStore
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference collectionReference=db.collection("Houses");
    // FirebaseStorage
    private StorageReference storageReference;
    Button add;
    RecyclerView recyclerView;
    List<Houses> housee;
    MyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        add=findViewById(R.id.addBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(),AddingHouse.class);
                startActivity(i2);
            }
        });


      //Firebase Auth
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();

        //Widgets
        recyclerView=findViewById(R.id.recyclerView);
        housee=new ArrayList<>();
        adapter=new MyAdapter(HomePage.this,housee);

        recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));
       // recyclerView.setHasFixedSize(true);




    }
        //Adding menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId= item.getItemId();
        if(itemId==R.id.add) {
            if(user!=null&&firebaseAuth!=null){
                    Intent i=new Intent(this, Add_House.class);
                    startActivity(i);
            }
        }
        else if(itemId==R.id.signOut){
            if(user!=null&&firebaseAuth!=null){
                    firebaseAuth.signOut();
                    Intent j=new Intent(this, login.class);
                    startActivity(j);
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        collectionReference.whereEqualTo("city","Bhilai").get().addOnSuccessListener(queryDocumentSnapshots -> {

            for(QueryDocumentSnapshot houses:queryDocumentSnapshots){
                Houses houseee=houses.toObject(Houses.class);
                housee.add(houseee);
            }
            adapter=new MyAdapter(HomePage.this,housee);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });
    }

    }