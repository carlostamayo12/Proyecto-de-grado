package com.example.jeromotosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
  //firebaseprueba

  @Override
  public void onBackPressed() {

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //Intent i = new Intent(MainActivity.this,ContactActivity.class);
    //startActivity(i);

    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
          Intent i = new Intent(MainActivity.this,ContactActivity.class);
          //Intent i = new Intent(MainActivity.this,LoginActivity.class);
          startActivity(i);
        }else{
          Intent i = new Intent(MainActivity.this,LoginActivity.class);
          startActivity(i);
        }
      }
    },2000);
  }

  @Override
  public void onStart() {
    super.onStart();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    if (user != null) {
      //Intent i = new Intent(MainActivity.this,HomeActivity.class);
      //Intent i = new Intent(MainActivity.this,LoginActivity.class);
      //startActivity(i);
    }else{
      //Intent i = new Intent(MainActivity.this,LoginActivity.class);
      //startActivity(i);
    }
  }

}
