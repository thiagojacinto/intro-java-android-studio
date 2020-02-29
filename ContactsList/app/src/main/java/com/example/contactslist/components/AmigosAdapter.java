package com.example.contactslist.components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AmigosAdapter extends ArrayAdapter<Amigos> {

    Context mCtx;
    int listLayoutRes;
    List<Amigos> amigosList;
    SQLiteDatabase mDatabase;

    public AmigosAdapter(Context mCtx, int listLayoutRes, List<Amigos> amigosList, SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, amigosList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.amigosList = amigosList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        //Retorna a posisao da lista de amigos
       final Amigos amigos = amigosList.get(position);


        //getting views
        TextView textViewNome = view.findViewById(R.id.textViewNome);
        TextView textViewLocal = view.findViewById(R.id.textViewLocal);
        TextView textViewNumero = view.findViewById(R.id.textViewNumero);
        TextView textViewRegistro = view.findViewById(R.id.textViewRegistro);

        //adding data to views
        textViewNome.setText(amigos.getNome());
        textViewLocal.setText(amigos.getLocal());
        textViewNumero.setText(String.valueOf(amigos.getNumero()));
        textViewRegistro.setText(amigos.getDataEncontro());

        //we will use these buttons later for update and delete operation
        Button buttonDelete = view.findViewById(R.id.buttonDeleteAmigo);
        Button buttonEdit = view.findViewById(R.id.buttonEditAmigo);

        //Atualizar dados de seu amigo na lista quando clicar no botao
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAmigos(amigos);
            }
        });

        //Apagando um amigo da lista
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Voçê deseja apagar seu coleginha?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM amigos WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{amigos.getId()});
                        reloadAmigosFromDatabase();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertaApagar = builder.create();
                alertaApagar.show();
            }
        });

        return view;
    }


    private void updateAmigos(final Amigos amigos) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_amigos, null);
        builder.setView(view);

        final EditText editTextNome = view.findViewById(R.id.editTextNome);
        final EditText editTextNumero = view.findViewById(R.id.editTextNumero);
        final Spinner spinnerLocal = view.findViewById(R.id.spinnerLocal);

        editTextNome.setText(amigos.getNome());
        editTextNumero.setText(String.valueOf(amigos.getNumero()));

        final AlertDialog alerta = builder.create();
        alerta.show();


        view.findViewById(R.id.buttonUpdateAmigo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editTextNome.getText().toString().trim();
                String numero = editTextNumero.getText().toString().trim();
                String local = spinnerLocal.getSelectedItem().toString();

                if (nome.isEmpty()) {
                    editTextNome.setError("O campo nome não pode ficar em Branco");
                    editTextNome.requestFocus();
                    return;
                }

                if (numero.isEmpty()) {
                    editTextNumero.setError("O campo numero não pode ficar em Branco");
                    editTextNumero.requestFocus();
                    return;
                }

                String sql = "UPDATE amigos \n" +
                        "SET nome = ?, \n" +
                        "local = ?, \n" +
                        "numero = ? \n" +
                        "WHERE id = ?;\n";

                mDatabase.execSQL(sql, new String[]{nome, local, numero, String.valueOf(amigos.getId())});
                Toast.makeText(mCtx, "Amigo Atualizado", Toast.LENGTH_SHORT).show();
                reloadAmigosFromDatabase();

                alerta.dismiss();
            }
        });

    }

    private void reloadAmigosFromDatabase() {
        Cursor cursorAmigos = mDatabase.rawQuery("SELECT * FROM amigos", null);
        if (cursorAmigos.moveToFirst()) {
            amigosList.clear();
            do {
                amigosList.add(new Amigos(
                        cursorAmigos.getInt(0),
                        cursorAmigos.getString(1),
                        cursorAmigos.getString(2),
                        cursorAmigos.getString(3),
                        cursorAmigos.getDouble(4)
                ));
            } while (cursorAmigos.moveToNext());
        }
        cursorAmigos.close();
        notifyDataSetChanged();
    }


}
