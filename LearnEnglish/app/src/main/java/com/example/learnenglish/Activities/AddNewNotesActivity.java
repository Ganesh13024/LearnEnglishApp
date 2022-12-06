package com.example.learnenglish.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Database.MyNoteDatabase;
import com.example.learnenglish.Entities.MyNoteEntities;
import com.example.learnenglish.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNewNotesActivity extends AppCompatActivity {

    private EditText inputNoteTitle,inputNoteText;
    private TextView textDateTime,saveNote;
    private View indicator1,indicator2;
    String selectedColor;

    ImageView addImage;
    private String imagePath;

    private MyNoteEntities alreadyAvailable;

    private static final int STORAGE_PERMISSION=1;
    private static final int SELECT_IMG=1;

    private AlertDialog alertDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_notes);

        indicator1=findViewById(R.id.viewindicator);
        indicator2=findViewById(R.id.viewindicator2);
        saveNote=findViewById(R.id.save_note);
        inputNoteText=findViewById(R.id.input_note_text);
        inputNoteTitle=findViewById(R.id.input_note_title);
        textDateTime=findViewById(R.id.textDateTime);

        addImage=findViewById(R.id.image_note);

        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        selectedColor="#FF937B";
        imagePath="";

        if (getIntent().getBooleanExtra("updateOrView",false)){
            alreadyAvailable=(MyNoteEntities) getIntent().getSerializableExtra("myNotes");
            setViewUpdate();
        }

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNotes();
            }
        });
        textDateTime.setText(new SimpleDateFormat("EEEE,dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date()));

        findViewById(R.id.img_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImage.setImageBitmap(null);
                addImage.setVisibility(View.GONE);
                findViewById(R.id.img_remove).setVisibility(View.GONE);

                imagePath = "";
                findViewById(R.id.img_remove).setVisibility(View.VISIBLE);
            }
        });
        bottomSheet();
        setViewColor();
    }

    private void setViewUpdate() {

        inputNoteTitle.setText(alreadyAvailable.getTitle());
        inputNoteText.setText(alreadyAvailable.getNotetext());
        textDateTime.setText(alreadyAvailable.getDateTime());


        if (alreadyAvailable.getImagepath()!=null && !alreadyAvailable.getImagepath().trim().isEmpty()){

            addImage.setImageBitmap(BitmapFactory.decodeFile(alreadyAvailable.getImagepath()));
            addImage.setVisibility(View.VISIBLE);
            findViewById(R.id.img_remove).setVisibility(View.VISIBLE);
            imagePath = alreadyAvailable.getImagepath();
        }
    }

    private void setViewColor() {
        GradientDrawable gradientDrawable = (GradientDrawable) indicator1.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedColor));

        GradientDrawable gradientDrawable2 = (GradientDrawable) indicator2.getBackground();
        gradientDrawable2.setColor(Color.parseColor(selectedColor));


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

            final MyNoteEntities myNoteEntities = new MyNoteEntities();
            myNoteEntities.setTitle(inputNoteTitle.getText().toString());
            myNoteEntities.setNotetext(inputNoteText.getText().toString());
            myNoteEntities.setDateTime(textDateTime.getText().toString());
            myNoteEntities.setColor(selectedColor);
            myNoteEntities.setImagepath(imagePath);

            if (alreadyAvailable !=null){
                myNoteEntities.setId(alreadyAvailable.getId());
            }


            class Savenotes extends AsyncTask<Void,Void,Void>
            {
                @Override
                protected Void doInBackground(Void... voids) {

                    MyNoteDatabase.getMyNoteDatabase(getApplicationContext()).notesDao().insertNote(myNoteEntities);
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

    private void bottomSheet()
    {
        final LinearLayout linearLayout = findViewById(R.id.bottom_layout);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        linearLayout.findViewById(R.id.bottom_text).setOnClickListener(new View.OnClickListener() {
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

        final ImageView imageColor1 = linearLayout.findViewById(R.id.imageColor1);
        final ImageView imageColor2 = linearLayout.findViewById(R.id.imageColor2);
        final ImageView imageColor3 = linearLayout.findViewById(R.id.imageColor3);
        final ImageView imageColor4 = linearLayout.findViewById(R.id.imageColor4);
        final ImageView imageColor5 = linearLayout.findViewById(R.id.imageColor5);
        final ImageView imageColor6 = linearLayout.findViewById(R.id.imageColor6);

        linearLayout.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "#FF937B";
                imageColor1.setImageResource(R.drawable.doneicon);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setViewColor();
            }
        });

        linearLayout.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "#FFFB7B";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(R.drawable.doneicon);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setViewColor();
            }
        });

        linearLayout.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "#ADFF7B";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(R.drawable.doneicon);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setViewColor();
            }
        });

        linearLayout.findViewById(R.id.viewColor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "#96FFEA";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(R.drawable.doneicon);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setViewColor();
            }
        });


        linearLayout.findViewById(R.id.viewColor5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "#969CFF";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(R.drawable.doneicon);
                imageColor6.setImageResource(0);
                setViewColor();
            }
        });


        linearLayout.findViewById(R.id.viewColor6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "#FF96F5";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(R.drawable.doneicon);
                setViewColor();
            }
        });


        if(alreadyAvailable!=null && alreadyAvailable.getColor()!=null && !alreadyAvailable.getColor().trim().isEmpty()){

            switch (alreadyAvailable.getColor()){
                case "#FFFB7B":
                    linearLayout.findViewById(R.id.viewColor2).performClick();
                    break;

                case "#ADFF7B":
                    linearLayout.findViewById(R.id.viewColor3).performClick();
                    break;

                case "#96FFEA":
                    linearLayout.findViewById(R.id.viewColor4).performClick();
                    break;

                case "#969CFF":
                    linearLayout.findViewById(R.id.viewColor5).performClick();
                    break;

                case "#FF96F5":
                    linearLayout.findViewById(R.id.viewColor6).performClick();
                    break;
            }
        }



        linearLayout.findViewById(R.id.add_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AddNewNotesActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION);
                }
                else
                {
                    selectYourImage();
                }
            }
        });

        if (alreadyAvailable!=null)
        {
            linearLayout.findViewById(R.id.remove).setVisibility(View.VISIBLE);
            linearLayout.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    showDeleteDialog();
                }
            });
        }
    }

    private void showDeleteDialog() {

        if (alertDialog == null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(AddNewNotesActivity.this);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_delete_note,(ViewGroup) findViewById(R.id.layoutDeleteNote_Container));
            builder.setView(view);
            alertDialog=builder.create();
            if (alertDialog.getWindow()!=null){
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            view.findViewById(R.id.textDeleteNote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    class DeleteNoteTask extends AsyncTask<Void,Void,Void>{

                        @Override
                        protected Void doInBackground(Void... voids) {

                            MyNoteDatabase.getMyNoteDatabase(getApplicationContext()).notesDao().deleteNotes(alreadyAvailable);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);

                            Intent intent =new Intent();
                            intent.putExtra("isNoteDeleted",true);
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    }

                    new DeleteNoteTask().execute();
                }
            });

            view.findViewById(R.id.textCancelNote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
        }

        alertDialog.show();
    }

    private void selectYourImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,SELECT_IMG);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==STORAGE_PERMISSION&&grantResults.length>0){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                selectYourImage();
            }else {
                Toast.makeText(this, "PERMISSION DELAYED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==SELECT_IMG && resultCode==RESULT_OK)
        {
            if (data != null)
            {
                Uri selectImageUri = data.getData();
                if (selectImageUri!=null){
                    try {

                        InputStream inputStream = getContentResolver().openInputStream(selectImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        addImage.setImageBitmap(bitmap);
                        addImage.setVisibility(View.VISIBLE);
                        imagePath=getPathFromUri(selectImageUri);
                    }
                    catch (Exception exception)
                    {
                        Toast.makeText(this,exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getPathFromUri(Uri uri)
    {
        String filepath;
        Cursor cursor = getContentResolver().query(uri,null,null,null);
        if (cursor==null)
        {
            filepath=uri.getPath();
        }
        else
        {
            cursor.moveToFirst();
            int index=cursor.getColumnIndex("_data");
            filepath=cursor.getString(index);
            cursor.close();
        }
        return filepath;
    }

}