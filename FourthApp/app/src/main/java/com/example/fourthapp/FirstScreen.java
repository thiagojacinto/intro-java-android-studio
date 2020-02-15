package com.example.fourthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        Intent intent = getIntent();
        if(intent != null){
            Bundle params = intent.getExtras();
            if(params != null){
                String name = params.getString("name");
                String email = params.getString("email");

                TextView nomeTV = (TextView) findViewById(R.id.nameInput);
                TextView emailTV = (TextView) findViewById(R.id.emailInput);

                nomeTV.setText("Nome: "+ name);
                emailTV.setText("Email: "+ email);
            }
        }
    }

    public void accept(View view){
        Intent intent = new Intent();
        intent.putExtra("msg", "Aceitou");

        setResult(10, intent);
        finish();
    }

    public void reject(View view){
        Intent intent = new Intent();
        intent.putExtra("msg", "Rejeitou");

        setResult(20, intent);
        finish();
    }
}
