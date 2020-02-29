package com.example.contactslist.components;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.contactslist.MainActivity;
import com.example.contactslist.R;
import com.example.contactslist.model.Amigos;

import java.util.ArrayList;
import java.util.List;

public class AmigosActivity extends AppCompatActivity {

    List<Amigos> amigosList;
    SQLiteDatabase mDatabase;
    ListView listViewAmigos;
    AmigosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos);

        listViewAmigos = (ListView) findViewById(R.id.listViewAmigos);
        amigosList = new ArrayList<>();

        //chamando a base de dados
        mDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);

        //Metodo para lista nossos amigos na base de dados
        showEmployeesFromDatabase();
    }

    private void showEmployeesFromDatabase() {

        //utilizamos o metodo rawQuery(sql, selectionargs) para consultar todos os amigos da tabela Amigos no banco
        Cursor cursorAmigos = mDatabase.rawQuery("SELECT * FROM amigos", null);

        //se o cursor tiver alguns dados em nossa base
        if (cursorAmigos.moveToFirst()) {
            //Fazemos um laço para percorrer todas as colunas da tabela
            do {
                //capturamos e adicionamos em nossa lista de Amigos cadastrados no banco de dados
                amigosList.add(new Amigos(
                        cursorAmigos.getInt(0),
                        cursorAmigos.getString(1),
                        cursorAmigos.getString(2),
                        cursorAmigos.getString(3),
                        cursorAmigos.getDouble(4)
                ));
            } while (cursorAmigos.moveToNext());
        }
        //fechamos nosso cursor
        cursorAmigos.close();

        //Criamos nosso obrjeto Adaptador creating the adapter object
        adapter = new AmigosAdapter(this, R.layout.list_layout_amigos, amigosList,mDatabase);

        //Adicionamos nosso dados em nossa listView
        listViewAmigos.setAdapter(adapter);
    }


    }


