package com.example.databasebabysteps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.databasebabysteps.components.AddNote;
import com.example.databasebabysteps.components.Note;
import com.example.databasebabysteps.dao.NotesDAO;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button startButton, clearAllButton;
    TextView noContentPlaceholder;
    ListView notesList;
    private NotesDAO notesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get elements
        startButton = findViewById(R.id.start_button);
        clearAllButton = findViewById(R.id.clear_all_button);
        notesList = findViewById(R.id.notes_list);
        noContentPlaceholder = findViewById(R.id.text_view_first);

        // On click: move to `AddNote` activity
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startSaving = new Intent(MainActivity.this, AddNote.class);
                startActivity(startSaving);
            }
        });

        notesDAO = new NotesDAO(this);
        try {
            notesDAO.open();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    @Override
    protected void onResume() {

        try {
            notesDAO.open();
            super.onResume();
            // Uses `getAll()`
            List<Note> listOfNotes = notesDAO.getAll();
            // Creates a dynamic array to populate list
            ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, listOfNotes );
            notesList = findViewById(R.id.notes_list);
            notesList.setAdapter(adapter);

            if (listOfNotes.size() > 0) {
                noContentPlaceholder.setVisibility(View.INVISIBLE);
                noContentPlaceholder.setSystemUiVisibility(View.INVISIBLE);
            }

            // On click CLEAR: clear all list of notes:
            clearAllButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        notesDAO.open();
                        // Uses `getAll()`
                        List<Note> listOfNotes = notesDAO.getAll();
                        // For each
                        for (int i = 0; i < listOfNotes.size(); i++) {
                            notesDAO.delete(listOfNotes.get(i));
                        }
                    } catch (SQLException error) {
                        error.printStackTrace();
                    }
                }
            });

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        notesDAO.close();
        super.onPause();
    }

    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_note) {
            Intent intent = new Intent(this, AddNote.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
