package com.example.databasebabysteps.components;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.databasebabysteps.MainActivity;
import com.example.databasebabysteps.R;
import com.example.databasebabysteps.dao.NotesDAO;

import java.sql.SQLException;

public class AddNote extends AppCompatActivity {

    Button sendButton, backButton;
    private NotesDAO notesDAO;
    EditText noteImputed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Get elements
        backButton = findViewById(R.id.back_button);
        sendButton = findViewById(R.id.send_button);
        noteImputed = findViewById(R.id.edit_text_input);

        // Instantiate DAO
        notesDAO = new NotesDAO(this);
        try {
            // Initiate DAO interaction:
            notesDAO.open();

            // On click BACK
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent backView = new Intent(AddNote.this, MainActivity.class);
                    startActivity(backView);
                }
            });

            // Save data = send to database dealing
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String noteToSave = noteImputed.getEditableText().toString();
                    notesDAO.create(noteToSave);
                    finish();
                }
            });

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    @Override
    protected void onResume() {

        try {
            notesDAO.open();
            super.onResume();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onPause() {
        notesDAO.close();
        super.onPause();
    }
}
