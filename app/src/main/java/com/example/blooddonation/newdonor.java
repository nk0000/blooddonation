package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newdonor extends AppCompatActivity {
    //  FirebaseDatabase database;
    static DatabaseReference myRef;
    EditText phone;
    EditText name;
    EditText bloodgroup;
    EditText medicalIssue;
    EditText city;
    // Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdonor);
        phone=findViewById(R.id.phone);
        ImageView img= findViewById(R.id.img);
        img.setImageResource(R.drawable.man);
        bloodgroup=findViewById(R.id.bloodgp);
        city=findViewById(R.id.city);
        name=findViewById(R.id.name);

        medicalIssue=findViewById(R.id.medIssue);
        // database =
        myRef = FirebaseDatabase.getInstance().getReference("donors");
        Button button=findViewById(R.id.butto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addvalue();
            }
        });
    }
    public void addvalue(){
        String ph=phone.getText().toString().trim();
        String bg=bloodgroup.getText().toString().trim();
        String nm=name.getText().toString().trim();
        String mi=phone.getText().toString().trim();
        String ct=city.getText().toString().trim();
        //Toast.makeText(newdonor.this, "", Toast.LENGTH_SHORT).show();
        if(TextUtils.isEmpty(ph)){

            Toast.makeText(newdonor.this, "Please fill your enteries.", Toast.LENGTH_SHORT).show();
        }
        else{
            details d=new details(nm,ph,bg,ct,mi);
            String s= myRef.push().getKey();
            myRef.child(s).setValue(d);
            Toast.makeText(this, "Congrats, you are added.", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(newdonor.this,datafinish.class);
            phone.setText("");
            name.setText("");
            bloodgroup.setText("");
            medicalIssue.setText("");
            city.setText("");
            startActivity(intent);
        }
    }
}
