package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class register extends AppCompatActivity {
    EditText userName,userNumber,userEmail,userPassword;
    Button register;
    TextView signIn;

    // auth
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;
    //Connection
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference collectionReference=db.collection("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName=findViewById(R.id.userName);
        userNumber=findViewById(R.id.userNumber);
        userEmail=findViewById(R.id.userEmail);
        userPassword=findViewById(R.id.userPassword);
        register=findViewById(R.id.registerButton);
        signIn=findViewById(R.id.signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });


        // firebase Auth
        firebaseAuth=FirebaseAuth.getInstance();

        //for listening the change in authentication states and respond accordingly to
        //change of state

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser= firebaseAuth.getCurrentUser();

                //check if user is oged in or not
                if(currentUser!=null){
                    //user is logged in
                }
                else {
                    //user is logged out
                }

            }
        };

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(userEmail.getText().toString())&&
                        !TextUtils.isEmpty(userPassword.getText().toString())&&
                        !TextUtils.isEmpty(userName.getText().toString())&&
                        !TextUtils.isEmpty(userNumber.getText().toString()))
                {

                    String email=userEmail.getText().toString().trim();
                    String password=userPassword.getText().toString().trim();
                    String name=userName.getText().toString().trim();
                    String number=userNumber.getText().toString().trim();

                    CreateUserAccount(email,password,name,number);

                }
                else {
                    Toast.makeText(register.this,"Fill all fields",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
    public void CreateUserAccount(String email,String password,String name,String number){
        if(!TextUtils.isEmpty(email)&&
                !TextUtils.isEmpty(password)&&
                !TextUtils.isEmpty(name)&&
                !TextUtils.isEmpty(number)){
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(register.this, "Succesfully registered", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(), login.class);
                    startActivity(intent);
                }
            });
        }
        else {
            Toast.makeText(register.this,"Fill all fields",Toast.LENGTH_LONG).show();
        }
    }
}

