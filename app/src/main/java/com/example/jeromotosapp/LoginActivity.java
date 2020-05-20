package com.example.jeromotosapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginActivity extends AppCompatActivity {

  EditText editTextEmail;
  EditText editTextPWd;
  Button btnSignUp;
  FirebaseAuth mFirebaseAuth;

  @Override
  public void onBackPressed() {

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    editTextEmail = findViewById(R.id.editTextEmail);
    editTextPWd = findViewById(R.id.editTextPwd);
    btnSignUp = findViewById(R.id.btnSingUp);
    mFirebaseAuth = FirebaseAuth.getInstance();
    //editTextEmail.setText("cartamayo12@hotmail.com");

    btnSignUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = editTextEmail.getText().toString();
        String pwd = editTextPWd.getText().toString();


        if(email.isEmpty() && pwd.isEmpty()){
          Toast.makeText(LoginActivity.this, "Llenar los Campos", Toast.LENGTH_SHORT).show();
        }
        else if(email.isEmpty()){
          editTextEmail.setError("Ingrese Correo Electronico");
        }
        else if(pwd.isEmpty()){
          editTextPWd.setError("Ingrese Contraseña");
        }

        else {

          mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(!task.isSuccessful()){
                Toast.makeText(LoginActivity.this, "No completado", Toast.LENGTH_SHORT).show();
              }
              else{
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                //Toast.makeText(LoginActivity.this, uid, Toast.LENGTH_SHORT).show();

                FirebaseMessaging.getInstance().subscribeToTopic(uid)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                            String msg = getString(R.string.msg_subscribed);
                            if (!task.isSuccessful()) {
                              msg = getString(R.string.msg_subscribe_failed);
                            }
                            Log.d("msg firebase  =>", msg);
                            //Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(i);
                          }
                        });

              }
            }
          });
        }
      }
    });
  }



  }

