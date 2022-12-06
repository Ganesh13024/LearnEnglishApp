package com.example.learnenglish.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Models.UserModel;
import com.example.learnenglish.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterationActivity extends AppCompatActivity {

    Button signUp;
    TextInputEditText name,email,password,phone;
    TextView signIn;
    FirebaseAuth auth;
    FirebaseDatabase database;

    Animation scaleUp,scaleDown;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        database= FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();

        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        signUp=findViewById(R.id.login_btn);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email_reg);
        password=findViewById(R.id.password_reg);
        phone=findViewById(R.id.reg_phone);
        signIn=findViewById(R.id.sign_in);

        scaleUp= AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaleDown= AnimationUtils.loadAnimation(this,R.anim.scale_down);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterationActivity.this, LoginActivity.class));
                finish();
            }
        });

        signUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    signUp.startAnimation(scaleUp);
                }
                else if (motionEvent.getAction()==MotionEvent.ACTION_UP)
                {
                    signUp.startAnimation(scaleDown);
                    createUser();
                    progressBar.setVisibility(View.VISIBLE);
                }

                return true;
            }
        });


    }

    private void createUser() {

        String userName = name.getText().toString();
        String userEmail=email.getText().toString();
        String userPassword = password.getText().toString();
        String userphone = phone.getText().toString();

        if (TextUtils.isEmpty(userName))
        {
            Toast.makeText(this, "Name is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userEmail))
        {
            Toast.makeText(this, "Email Must  Required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword))
        {
            Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length()<6)
        {
            Toast.makeText(this, "Password Length Must be Greater then 6", Toast.LENGTH_SHORT).show();
            return;
        }

        //create user in firebase

        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    UserModel userModel = new UserModel(userName,userEmail,userPassword,userphone);
                    String id = task.getResult().getUser().getUid();
                    database.getReference().child("Users").child(id).setValue(userModel);
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(RegisterationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterationActivity.this, "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}