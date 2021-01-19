package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class olddonor extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView text;
    TextView errorMsg;
    TextView otp;
    String codeSent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olddonor);
        mAuth=FirebaseAuth.getInstance();
        text= findViewById(R.id.number);
        errorMsg=findViewById(R.id.errorMsg);
        Button butt= findViewById(R.id.butt);
        otp= findViewById(R.id.otp);
        Button proceed= findViewById(R.id.proceed);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();
            }
        });
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                verifyCode();
            }
        });
    }
    private void verifyCode(){

        String code=otp.getText().toString();
        if(code.equals("")){
            Toast.makeText(this, "You are missing any blank..", Toast.LENGTH_SHORT).show();return;}
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(codeSent,code);
        signInWithPhoneAuthCredential(credential);
    }
    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"LoginComplete",Toast.LENGTH_SHORT).show();
                    errorMsg.setVisibility(View.INVISIBLE);
                    Intent i=new Intent(olddonor.this,datafinish.class);
                    text.setText("");
                    otp.setText("");
                    startActivity(i);
                }
                else{
                    if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                        errorMsg.setText("Wrong Otp");
                        errorMsg.setVisibility(View.VISIBLE);}
                }
            }
        });
    }
    private void sendVerificationCode(){

        String phone= text.getText().toString();
        if(phone.isEmpty()){
            errorMsg.setText("Enter Phone number!!");
            errorMsg.setVisibility(View.VISIBLE);
            text.requestFocus();
            return;
        }
        if(phone.length()<13){
            errorMsg.setText("length of phone number is less than 10");
            errorMsg.setVisibility(View.VISIBLE);
            text.requestFocus();
            return;
        }
        else{
            errorMsg.setVisibility(View.INVISIBLE);
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                60,
                TimeUnit.SECONDS,
                this,
                mCallBack);
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // String code= phoneAuthCredential.getSmsCode();
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            errorMsg.setText(e.getMessage());
            errorMsg.setVisibility(View.VISIBLE);
            Toast.makeText(olddonor.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent= s;
        }
    };
}
