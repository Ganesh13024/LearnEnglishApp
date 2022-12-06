package com.example.learnenglish.Activities.GrammerExplanation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learnenglish.Activities.QuizQuestionsActivity;
import com.example.learnenglish.R;

public class subject_predicate extends AppCompatActivity {

    Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_predicate);
        test=findViewById(R.id.gotosubject);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(subject_predicate.this, QuizQuestionsActivity.class);
                intent.putExtra("type","subject");
                startActivity(intent);
            }
        });
    }
}