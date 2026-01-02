package com.example.fbasevideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name, course, email, surl;
    Button btnAdd, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name=(EditText)findViewById(R.id.txtName);
        course=(EditText)findViewById(R.id.txtCourse);
        email=(EditText) findViewById(R.id.txtEmail);
        surl=(EditText) findViewById(R.id.txtImageUrl);

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnBack=(Button) findViewById(R.id.btnBack);

//--------------------------------------------------------------------
//Botón que AGREGA (ADD) un registro nuevo a la Base de Datos Firebase
//--------------------------------------------------------------------
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();            //Llamo a la función insertData() y la ejecuta
                clearAll();              //Dejo todos los campos en blanco (vacíos)
            }
        });
//----------------------------------------------------------------------------------------
//Botón para REGRESAR (BACK) hacia ATRÁS por si me arrepiento de agregar un nuevo registro
//----------------------------------------------------------------------------------------

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertData(){
        Map<String , Object> map=new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("course", course.getText().toString());
        map.put("email", email.getText().toString());
        map.put("surl", surl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("students").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "Error while Insertion", Toast.LENGTH_SHORT).show();

                    }
                });

    }
    private void clearAll(){

        name.setText("");
        course.setText("");
        email.setText("");
        surl.setText("");
    }



}