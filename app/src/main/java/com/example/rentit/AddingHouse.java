package com.example.rentit;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.protobuf.Empty;

public class AddingHouse extends AppCompatActivity {
    EditText ownerName,ownerNumber,ownerCity,houseType,houseRent,houseAddress;
    Button AddingButton;
    ProgressBar progressBar;
    ImageView houseImage;

    //FireStore
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    StorageReference storageReference;
    CollectionReference collectionReference=db.collection("Houses");
    //Firebase Auth
    String currentUserId;
    String currentUSerName;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser user;

    ActivityResultLauncher<String> mTakePhoto;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_house);

        ownerName=findViewById(R.id.ownerName);
        ownerNumber=findViewById(R.id.ownerNumber);
        ownerCity=findViewById(R.id.ownerCity);
        houseType=findViewById(R.id.houseType);
        houseRent=findViewById(R.id.houseRent);
        houseAddress=findViewById(R.id.houseAddress);
        AddingButton=findViewById(R.id.AddingButton);
        progressBar=findViewById(R.id.progressBar);
        houseImage=findViewById(R.id.houseImage);

        progressBar.setVisibility(View.INVISIBLE);

        //Firebase
        storageReference=FirebaseStorage.getInstance().getReference();
        //FireBase Auth
        firebaseAuth=FirebaseAuth.getInstance();

        if(user!=null){
            currentUserId=user.getUid();
            currentUSerName=user.getDisplayName();
        }

        mTakePhoto=registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri o) {
                        houseImage.setImageURI(o);
                        imageUri=o;

                    }

                }
        );
        houseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mTakePhoto.launch("image/*");
            }
        });

        AddingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.INVISIBLE);
                Intent intent=new Intent(AddingHouse.this, AddingHouse.class);
                Toast.makeText(AddingHouse.this,"Details Added",Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }

   /* private void addHouse() {
        String name = ownerName.getText().toString().trim();
        String number = ownerNumber.getText().toString().trim();
        String city = ownerCity.getText().toString().trim();
        String type = houseType.getText().toString().trim();
        String price = houseRent.getText().toString().trim();
        String address = houseAddress.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number) &&
                !TextUtils.isEmpty(city) && !TextUtils.isEmpty(type) &&
                !TextUtils.isEmpty(price) && !TextUtils.isEmpty(address) && imageUri != null) {

            progressBar.setVisibility(View.INVISIBLE);
            Intent intent=new Intent(AddingHouse.this, AddingHouse.class);
            startActivity(intent);

            final StorageReference filePath=storageReference.child("House")
           //         .child("img_"+ Timestamp.now().getSeconds());

            //uploading image
           // filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               //@Override
              //  public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               //     filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
               //         @Override
               //         public void onSuccess(Uri uri) {
               //             String imgHouse=uri.toString();

                            Houses house=new Houses();

                            house.setType(type);
                            house.setPrice(price);
                            house.setAddress(address);
                            house.setCity(city);
                 //           house.setImgHouse(imgHouse);
                            house.setName(name);
                            house.setNumber(number);

                            collectionReference.add(house).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Intent intent=new Intent(AddingHouse.this,HomePage.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(AddingHouse.this,"Upload Failed",Toast.LENGTH_LONG).show();
                                }
                            });

                        }*/





    @Override
    public void onStart(){
        super.onStart();
        user= firebaseAuth.getCurrentUser();
    }
}