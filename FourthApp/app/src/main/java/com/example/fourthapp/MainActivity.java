package com.example.fourthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int CONSTANT_SCREEN_ONE = 10;
    public static final int CONSTANT_SCREEN_TWO = 20;
    
    Button goToFirstScreen = (Button) findViewById(R.id.firstScreen);
    Button goToSecondScreen = (Button) findViewById(R.id.secondScreen);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        goToFirstScreen.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendDataFromScreenOne(view);
                    }
                }
        );
        
        goToSecondScreen.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendDataFromSecondScreen(view);
                    }
                }
        );
    }

    public void sendDataFromScreenOne(View view){

        EditText nome = (EditText) findViewById(R.id.nameInput);
        EditText email = (EditText) findViewById(R.id.emailInput);

        Bundle params = new Bundle();
        params.putString("nome", nome.getText().toString());
        params.putString("email", email.getText().toString());

        Intent intent = new Intent(this, FirstScreen.class);
        intent.putExtras(params);

        startActivityForResult(intent, CONSTANT_SCREEN_ONE );
    }

    public void sendDataFromSecondScreen(View view){

        EditText nome = (EditText) findViewById(R.id.nameInput);
        EditText email = (EditText) findViewById(R.id.emailInput);

        Bundle params = new Bundle();
        params.putString("nome", nome.getText().toString());
        params.putString("email", email.getText().toString());

        Intent intent = new Intent(this, SecondScreen.class);
        intent.putExtras(params);

        startActivityForResult(intent, CONSTANT_SCREEN_TWO );
    }

    protected void onActivityResult(int screenCode, int result, Intent intent) {

        if (screenCode == CONSTANT_SCREEN_ONE) {
            Bundle params = intent.getExtras();
            if (params != null) {
                String msg = params.getString("msg");
                Toast.makeText(this, "Tela 1 -> Resultado: " + result + " | Msg: " + msg, Toast.LENGTH_LONG).show();
            }
        } else if (screenCode == CONSTANT_SCREEN_TWO) {
            Bundle params = intent.getExtras();
            if (params != null) {
                String msg = params.getString("msg");
                Toast.makeText(this, "Tela 2 -> Resultado: " + result + " | Msg: " + msg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
