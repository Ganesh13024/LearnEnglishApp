package com.example.learnenglish.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Database.MyNoteDatabase;
import com.example.learnenglish.Entities.MyNoteEntities;
import com.example.learnenglish.Entities.MyRemainderEntities;
import com.example.learnenglish.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNewRemainderActivity extends AppCompatActivity {

    private EditText title;
    private TextView textDateTime,saveNote;
    private View view;
    private String selectRemainderColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_remainder);


        TextView saveNote = findViewById(R.id.save_remainder);
        view =findViewById(R.id.view_remainder);
        title=findViewById(R.id.input_note_title);
        textDateTime=findViewById(R.id.textDateTime);


        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        selectRemainderColor="#FFFB7B";

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveReminder();
            }
        });

        textDateTime.setText(new SimpleDateFormat("EEEE,dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date()));

        bottomSheet();
        setViewColor();
    }

    private void saveReminder() {

        if (title.getText().toString().trim().isEmpty()){

            Toast.makeText(this, "Note Title can't Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        final MyRemainderEntities myNoteEntities = new MyRemainderEntities();
        myNoteEntities.setTitle(title.getText().toString());
        myNoteEntities.setDateTime(textDateTime.getText().toString());
        myNoteEntities.setColor(selectRemainderColor);

        class SaveRemainder extends AsyncTask<Void,Void,Void>
        {

            @Override
            protected Void doInBackground(Void... voids) {
                MyNoteDatabase.getMyNoteDatabase(getApplicationContext()).notesDao().insertRemainder(myNoteEntities);
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

        new SaveRemainder().execute();

        bottomSheet();
        setViewColor();
    }

    private void setViewColor() {

        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectRemainderColor));
    }

    private void bottomSheet() {

        final LinearLayout linearLayout = findViewById(R.id.remainder_bottom);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        linearLayout.findViewById(R.id.remainder_bottom_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        final ImageView imageColor1 = linearLayout.findViewById(R.id.remainder_imageColor1);
        final ImageView imageColor2 = linearLayout.findViewById(R.id.remainder_imageColor2);
        final ImageView imageColor3 = linearLayout.findViewById(R.id.remainder_imageColor3);
        final ImageView imageColor4 = linearLayout.findViewById(R.id.remainder_imageColor4);
        final ImageView imageColor5 = linearLayout.findViewById(R.id.remainder_imageColor5);
        final ImageView imageColor6 = linearLayout.findViewById(R.id.remainder_imageColor6);

        linearLayout.findViewById(R.id.remainder_viewColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRemainderColor = "#FF937B";
                imageColor1.setImageResource(R.drawable.doneicon);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setViewColor();
            }
        });

        linearLayout.findViewById(R.id.remainder_viewColor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRemainderColor = "#FFFB7B";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(R.drawable.doneicon);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setViewColor();
            }
        });

        linearLayout.findViewById(R.id.remainfer_viewColor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRemainderColor = "#ADFF7B";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(R.drawable.doneicon);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setViewColor();
            }
        });

        linearLayout.findViewById(R.id.remainder_imageColor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRemainderColor = "#96FFEA";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(R.drawable.doneicon);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setViewColor();
            }
        });


        linearLayout.findViewById(R.id.remainder_viewColor5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRemainderColor = "#969CFF";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(R.drawable.doneicon);
                imageColor6.setImageResource(0);
                setViewColor();
            }
        });


        linearLayout.findViewById(R.id.remainder_viewColor6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRemainderColor = "#FF96F5";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(R.drawable.doneicon);
                setViewColor();
            }
        });

    }


}