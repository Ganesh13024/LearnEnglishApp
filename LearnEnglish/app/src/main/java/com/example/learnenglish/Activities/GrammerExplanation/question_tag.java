package com.example.learnenglish.Activities.GrammerExplanation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learnenglish.Activities.QuizQuestionsActivity;
import com.example.learnenglish.R;
import com.example.learnenglish.ui.home.HomeFragment;

public class question_tag extends AppCompatActivity {

    Button gototest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_tag);

        gototest=findViewById(R.id.gototag);


        gototest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(question_tag.this, QuizQuestionsActivity.class);
                intent.putExtra("type","questiontag");
                startActivity(intent);
            }
        });
    }
}