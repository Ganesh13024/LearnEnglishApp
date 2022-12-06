package com.example.learnenglish.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Database.MyNoteDatabase;
import com.example.learnenglish.Entities.EventListEntities;
import com.example.learnenglish.Entities.MyNoteEntities;
import com.example.learnenglish.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNewEventsListActivity extends AppCompatActivity {

    private EditText inputNoteTitle,inputNoteText;
    private TextView textDateTime,saveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_events_list);

        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        inputNoteText=findViewById(R.id.text_list);
        inputNoteTitle=findViewById(R.id.event_list_title);
        textDateTime=findViewById(R.id.event_date_time);
        saveNote=findViewById(R.id.save_list);

        textDateTime.setText(new SimpleDateFormat("EEEE,dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date()));

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNotes();
            }
        });
    }

    private void saveNotes() {

        if (inputNoteTitle.getText().toString().trim().isEmpty()){

            Toast.makeText(this, "Note Title can't Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (inputNoteText.getText().toString().trim().isEmpty()){

            Toast.makeText(this, "Note Text can't Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        final EventListEntities myNoteEntities = new EventListEntities();
        myNoteEntities.setTitle(inputNoteTitle.getText().toString());
        myNoteEntities.setNotetext(inputNoteText.getText().toString());
        myNoteEntities.setDateTime(textDateTime.getText().toString());

        class Savenotes extends AsyncTask<Void,Void,Void>
        {
            @Override
            protected Void doInBackground(Void... voids) {

                MyNoteDatabase.getMyNoteDatabase(getApplicationContext()).notesDao().insertList(myNoteEntities);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);

                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        }
        new Savenotes().execute();
    }
}