package com.example.contactslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "amigosdatabase";

    TextView textViewListaAmigos;
    EditText editTextNome, editTextNumero;
    Spinner spinnerLocal;
    Button addAmigos;

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Criando nossa base de dados
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        criandoTabelaAmigos();

        textViewListaAmigos = (TextView) findViewById(R.id.textViewListaAmigos);
        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextNumero = (EditText) findViewById(R.id.editTextNumero);
        spinnerLocal = (Spinner) findViewById(R.id.spinnerLocal);

        //findViewById(R.id.buttonAddAmigo).setOnClickListener((View.OnClickListener) this);
        textViewListaAmigos.setOnClickListener((View.OnClickListener) this);

        addAmigos = (Button) findViewById(R.id.buttonAddAmigo);
        addAmigos.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) {
                       addAmigos();
                   }
        });



    }

    //Vamos verificar se o campo nome e numero são validos
    //O local não precisa pois seus valores são pré definidos no Array
    private boolean inputsAreCorrect(String nome, String numero) {
        if (nome.isEmpty()) {
            editTextNome.setError("Entre com um nome");
            editTextNome.requestFocus();
            return false;
        }

        if (numero.isEmpty() || Integer.parseInt(numero) <= 0) {
            editTextNumero.setError("Please enter salary");
            editTextNumero.requestFocus();
            return false;
        }
        return true;
    }

    //Este metodo vai adicionar nosso amigo na base
    private void addAmigos() {

        String nome = editTextNome.getText().toString().trim();
        String numero = editTextNumero.getText().toString().trim();
        String local = spinnerLocal.getSelectedItem().toString();

        //Vamos pegar a data e hora corrente do nosso aparelho
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String dataencontro = sdf.format(cal.getTime());

        //vamos validar nossos dados de entrada em nossa base
        if (inputsAreCorrect(nome, numero)) {
            String insertSQL = "INSERT INTO amigos \n" +
                    "(nome, local, dataencontro, numero)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?);";

            //Utilizamos o metodo execsql para inserir nosso valores
            //Nosso primeriro parametro é nosso sql  e o segundo são nosso valores
            mDatabase.execSQL(insertSQL, new String[]{nome, local, dataencontro, numero});

            Toast.makeText(this, "Seu novo amigo foi adicionado", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddAmigo:

                addAmigos();

                break;
            case R.id.textViewListaAmigos:

                startActivity(new Intent(this, AmigosActivity.class));

                break;
            default:
                throw new IllegalStateException("Valor não esperado: " + view.getId());
        }
    }

    private void criandoTabelaAmigos() {
        //mDatabase.execSQL("DROP TABLE IF EXISTS amigos");

        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS amigos (\n" +
                        "    id INTEGER NOT NULL CONSTRAINT amigos_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    nome varchar(200) NOT NULL,\n" +
                        "    local varchar(200) NOT NULL,\n" +
                        "    dataencontro datetime NOT NULL,\n" +
                        "    numero double NOT NULL\n" +
                        ");"
        );
    }

}
