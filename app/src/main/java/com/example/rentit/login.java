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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    Button login;
    EditText email,password;
    TextView signup;
    // firebase auth
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.editTextEmail);
        password=findViewById(R.id.editTextPassword);
        login=findViewById(R.id.loginButton);
        signup=findViewById(R.id.signupBtn);

        signup.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(), register.class);
            startActivity(intent);
        });

         // firebase authentication
        firebaseAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(v -> logEmailPass(email.getText().toString().trim(),password.getText().toString().trim()));
    }
    private void logEmailPass(String email,String password){
        if(!TextUtils.isEmpty(email)&&!(TextUtils.isEmpty(password))){
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {

                FirebaseUser user=firebaseAuth.getCurrentUser();
                Intent i=new Intent(this, HomePage.class);
                startActivity(i);
            });
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnFailureListener(e -> Toast.makeText(login.this,"Invalid Login",Toast.LENGTH_LONG).show());

        }
        else{
            Toast.makeText(this,"Fill all fields",Toast.LENGTH_LONG).show();
        }
    }
}