package com.example.learnenglish.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Models.Quizmodel;
import com.example.learnenglish.R;
import com.example.learnenglish.ui.PracticeQuiz.PracticeQuizFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class QuizQuestionsActivity extends AppCompatActivity {

    private TextView questionTV,questionNumberTv,title_quiz;
    private Button option1btn,option2btn,option3btn,option4btn;
    ArrayList<Quizmodel> quizmodelArrayList;
    Random random;
    TextView userscore;
    RelativeLayout relativeLayout;
    int currentScore=0,questionAttempted = 0,currentPos;

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);


        userscore=findViewById(R.id.score);

        title_quiz=findViewById(R.id.title_quiz);
        fAuth=FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        questionTV=findViewById(R.id.idTvQuestions);
        questionNumberTv=findViewById(R.id.idTVQuestionsAttempted);
        option1btn=findViewById(R.id.idbtnOption1);
        option2btn=findViewById(R.id.idbtnOption2);
        option3btn=findViewById(R.id.idbtnOption3);
        option4btn=findViewById(R.id.idbtnOption4);
        relativeLayout = findViewById(R.id.idQuizlayout);


        quizmodelArrayList=new ArrayList<>();
        random = new Random();
        getQuizQuestions(quizmodelArrayList);
        Collections.shuffle(quizmodelArrayList);
        currentPos=0;
        setDataToViews(currentPos);

        option1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizmodelArrayList.get(currentPos).getAnswer().trim().toLowerCase(Locale.ROOT).equals(option1btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                    relativeLayout.setBackgroundResource(R.color.color_dark3);
                    option1btn.setClickable(false);
                    option2btn.setClickable(false);
                    option3btn.setClickable(false);
                    option4btn.setClickable(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            relativeLayout.setBackgroundResource(R.color.white);
                            questionAttempted++;
                            if(currentPos<=quizmodelArrayList.size()-1){
                                currentPos++;
                            }
                            if(currentPos>quizmodelArrayList.size()-1)
                            {
                                currentPos=0;
                            }

                            setDataToViews(currentPos);
                            option1btn.setClickable(true);
                            option2btn.setClickable(true);
                            option3btn.setClickable(true);
                            option4btn.setClickable(true);

                        }
                    },1000);
                }
                else
                {
                    relativeLayout.setBackgroundResource(R.color.color_dark1);
                    option1btn.setClickable(false);
                    option2btn.setClickable(false);
                    option3btn.setClickable(false);
                    option4btn.setClickable(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            relativeLayout.setBackgroundResource(R.color.white);
                            questionAttempted++;
                            if(currentPos<=quizmodelArrayList.size()-1){
                                currentPos++;
                            }
                            if(currentPos>quizmodelArrayList.size()-1)
                            {
                                currentPos=0;
                            }
                            setDataToViews(currentPos);
                            option1btn.setClickable(true);
                            option2btn.setClickable(true);
                            option3btn.setClickable(true);
                            option4btn.setClickable(true);
                        }
                    },1000);

                }
            }
        });



        option2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizmodelArrayList.get(currentPos).getAnswer().trim().toLowerCase(Locale.ROOT).equals(option2btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                    relativeLayout.setBackgroundResource(R.color.color_dark3);
                    option1btn.setClickable(false);
                    option2btn.setClickable(false);
                    option3btn.setClickable(false);
                    option4btn.setClickable(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            relativeLayout.setBackgroundResource(R.color.white);
                            questionAttempted++;
                            if(currentPos<=quizmodelArrayList.size()-1){
                                currentPos++;
                            }
                            if(currentPos>quizmodelArrayList.size()-1)
                            {
                                currentPos=0;
                            }
                            setDataToViews(currentPos);
                            option1btn.setClickable(true);
                            option2btn.setClickable(true);
                            option3btn.setClickable(true);
                            option4btn.setClickable(true);
                        }
                    },1000);

                }
                else
                {
                    relativeLayout.setBackgroundResource(R.color.color_dark1);
                    option1btn.setClickable(false);
                    option2btn.setClickable(false);
                    option3btn.setClickable(false);
                    option4btn.setClickable(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            relativeLayout.setBackgroundResource(R.color.white);
                            questionAttempted++;
                            if(currentPos<=quizmodelArrayList.size()-1){
                                currentPos++;
                            }
                            if(currentPos>quizmodelArrayList.size()-1)
                            {
                                currentPos=0;
                            }
                            setDataToViews(currentPos);
                            option1btn.setClickable(true);
                            option2btn.setClickable(true);
                            option3btn.setClickable(true);
                            option4btn.setClickable(true);
                        }
                    },1000);

                }

            }
        });

        option3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizmodelArrayList.get(currentPos).getAnswer().trim().toLowerCase(Locale.ROOT).equals(option3btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                    relativeLayout.setBackgroundResource(R.color.color_dark3);
                    option1btn.setClickable(false);
                    option2btn.setClickable(false);
                    option3btn.setClickable(false);
                    option4btn.setClickable(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            relativeLayout.setBackgroundResource(R.color.white);
                            questionAttempted++;
                            if(currentPos<=quizmodelArrayList.size()-1){
                                currentPos++;
                            }
                            if(currentPos>quizmodelArrayList.size()-1)
                            {
                                currentPos=0;
                            }
                            setDataToViews(currentPos);
                            option1btn.setClickable(true);
                            option2btn.setClickable(true);
                            option3btn.setClickable(true);
                            option4btn.setClickable(true);
                        }
                    },1000);
                }
                else
                {
                    relativeLayout.setBackgroundResource(R.color.color_dark1);
                    option1btn.setClickable(false);
                    option2btn.setClickable(false);
                    option3btn.setClickable(false);
                    option4btn.setClickable(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            relativeLayout.setBackgroundResource(R.color.white);
                            questionAttempted++;
                            if(currentPos<=quizmodelArrayList.size()-1){
                                currentPos++;
                            }
                            if(currentPos>quizmodelArrayList.size()-1)
                            {
                                currentPos=0;
                            }
                            setDataToViews(currentPos);
                            option1btn.setClickable(true);
                            option2btn.setClickable(true);
                            option3btn.setClickable(true);
                            option4btn.setClickable(true);
                        }
                    },1000);

                }
            }
        });

        option4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizmodelArrayList.get(currentPos).getAnswer().trim().toLowerCase(Locale.ROOT).equals(option4btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                    relativeLayout.setBackgroundResource(R.color.color_dark3);
                    option1btn.setClickable(false);
                    option2btn.setClickable(false);
                    option3btn.setClickable(false);
                    option4btn.setClickable(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            relativeLayout.setBackgroundResource(R.color.white);
                            questionAttempted++;
                            if(currentPos<=quizmodelArrayList.size()-1){
                                currentPos++;
                            }
                            if(currentPos>quizmodelArrayList.size()-1)
                            {
                                currentPos=0;
                            }
                            setDataToViews(currentPos);
                            option1btn.setClickable(true);
                            option2btn.setClickable(true);
                            option3btn.setClickable(true);
                            option4btn.setClickable(true);

                        }
                    },1000);
                }
                else
                {
                    relativeLayout.setBackgroundResource(R.color.color_dark1);
                    option1btn.setClickable(false);
                    option2btn.setClickable(false);
                    option3btn.setClickable(false);
                    option4btn.setClickable(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            relativeLayout.setBackgroundResource(R.color.white);
                            questionAttempted++;
                            if(currentPos<=quizmodelArrayList.size()-1){
                                currentPos++;
                            }
                            if(currentPos>quizmodelArrayList.size()-1)
                            {
                                currentPos=0;
                            }
                            setDataToViews(currentPos);
                            option1btn.setClickable(true);
                            option2btn.setClickable(true);
                            option3btn.setClickable(true);
                            option4btn.setClickable(true);
                        }
                    },1000);

                }
            }
        });
    }

    private void showBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(QuizQuestionsActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet,(LinearLayout)findViewById(R.id.idLscore));
        TextView scrollTv = bottomSheetView.findViewById(R.id.idVscore);
        Button restart = bottomSheetView.findViewById(R.id.idbtnRestart);
        Button cancel = bottomSheetView.findViewById(R.id.cancelbtn);
        scrollTv.setText("Your Score is \n "+currentScore+"/10");

        Intent i = getIntent();
        String type2 = i.getStringExtra("type");

        /*final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("score",score);
        cartMap.put("type",type2);
        fstore.collection("UserScores").document(fAuth.getCurrentUser().getUid()).collection("CurrentUserScore").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(QuizQuestionsActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();

            }
        });*/

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackgroundResource(R.color.white);
                bottomSheetDialog.dismiss();
                currentPos = random.nextInt(quizmodelArrayList.size());
                questionAttempted =0;
                currentScore=0;
                setDataToViews(currentPos);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void setDataToViews(int currentPos){
        questionNumberTv.setText("Questions Attempted "+questionAttempted+"/10");

        if (questionAttempted == 10){
            showBottomSheet();
        }
        else
        {
            questionTV.setText(quizmodelArrayList.get(currentPos).getQuestion());
            option1btn.setText(quizmodelArrayList.get(currentPos).getOption1());
            option2btn.setText(quizmodelArrayList.get(currentPos).getOption2());
            option3btn.setText(quizmodelArrayList.get(currentPos).getOption3());
            option4btn.setText(quizmodelArrayList.get(currentPos).getOption4());

        }

    }

    private void getQuizQuestions(ArrayList<Quizmodel> quizmodelArrayList) {

        Intent intent = getIntent();
        String type=intent.getStringExtra("type");


        if (type!=null && type.toLowerCase(Locale.ROOT).equalsIgnoreCase("tenses")) {

            title_quiz.setText("Tenses");

            quizmodelArrayList.add(new Quizmodel("The Earth____round the Sun.","move","moves","moved","moving","moves"));
            quizmodelArrayList.add(new Quizmodel("I_____him only one letter up to now.","sent","have sent","send","had sent","have sent"));
            quizmodelArrayList.add(new Quizmodel("I'm sure I _____him at the party last night.","saw","have seen","had seen","seen","saw"));
            quizmodelArrayList.add(new Quizmodel("He thanked me foe what I_____.","have done","had done","have been doing"," doing","had done"));
            quizmodelArrayList.add(new Quizmodel("Don't disturb me.I_____my homework.","do","did","am doing","done","am doing"));
            quizmodelArrayList.add(new Quizmodel("It____since early morning,","rained","is raining","has been raining","had been raining","has been raining"));
            quizmodelArrayList.add(new Quizmodel("The plane____at 3.30.","arrives","will arrive","arrive","arrived","arrives"));
            quizmodelArrayList.add(new Quizmodel("This book is not long.I___it by lunch time.","will be reading","will have read","read","red","will have read"));
            quizmodelArrayList.add(new Quizmodel("I_____ a strange noise.","hear","am hearing","have been hearing","heared","hear"));
            quizmodelArrayList.add(new Quizmodel("She_____unconscious since four o'clock.","is","was","has been","have been","has been"));

        }

        else if (type!=null && type.toLowerCase(Locale.ROOT).equalsIgnoreCase("parts")) {

            title_quiz.setText("Parts Of Speech");

            quizmodelArrayList.add(new Quizmodel(" I bought a \uD835\uDE57\uD835\uDE5A\uD835\uDE56\uD835\uDE6A\uD835\uDE69\uD835\uDE5E\uD835\uDE5B\uD835\uDE6A\uD835\uDE61 dress at the mall.", "preposition", "adjective", "noun", "4", "adjective"));
            quizmodelArrayList.add(new Quizmodel("What did \uD835\uDE68\uD835\uDE5D\uD835\uDE5A ask you to do?", "conjunction", "preposition", "pronoun", "4", "pronoun"));
            quizmodelArrayList.add(new Quizmodel("I left my shoes \uD835\uDE6A\uD835\uDE63\uD835\uDE59\uD835\uDE5A\uD835\uDE67 the kitchen table", "adjective", "preposition", "pronoun", "4", "preposition"));
            quizmodelArrayList.add(new Quizmodel("If we finish our work \uD835\uDE66\uD835\uDE6A\uD835\uDE5E\uD835\uDE58\uD835\uDE60\uD835\uDE61\uD835\uDE6E we can go to the movies", "adverb", "conjunction", "verb", "4", "adverb"));
            quizmodelArrayList.add(new Quizmodel("On Saturdays I \uD835\uDE6C\uD835\uDE64\uD835\uDE67\uD835\uDE60 from nine to five", "verb", "preposition", "adverb", "4", "verb"));
            quizmodelArrayList.add(new Quizmodel("I want to go to a \uD835\uDE6A\uD835\uDE63\uD835\uDE5E\uD835\uDE6B\uD835\uDE5A\uD835\uDE67\uD835\uDE68\uD835\uDE5E\uD835\uDE69\uD835\uDE6E in the United States.", "adjective", "preposition", "noun", "4", "noun"));
            quizmodelArrayList.add(new Quizmodel("I'm sure I've \uD835\uDE62\uD835\uDE5A\uD835\uDE69 your girlfriend before", "verb", "preposition", "pronoun", "4", "verb"));
            quizmodelArrayList.add(new Quizmodel("\uD835\uDE52\uD835\uDE5A\uD835\uDE61\uD835\uDE61, I don't think I'll be home before 6", "interjection", "preposition", "pronoun", "4", "interjection"));
            quizmodelArrayList.add(new Quizmodel("Andy knocked on the door \uD835\uDE57\uD835\uDE6A\uD835\uDE69 nobody answered.", "adverb", "adjective", "conjunction", "4", "conjunction"));
            quizmodelArrayList.add(new Quizmodel(" \uD835\uDE3C\uD835\uDE5B\uD835\uDE69\uD835\uDE5A\uD835\uDE67 lunch let's go out for a coffee.", "pronoun", "preposition", "verb", "4", "preposition"));

            option4btn.setVisibility(View.GONE);

        }

        else if (type!=null && type.toLowerCase(Locale.ROOT).equalsIgnoreCase("activevoice")) {

            title_quiz.setText("Active Voice And Passive Voice");

            quizmodelArrayList.add(new Quizmodel("I ate a piece of chocolate cake.","active","passive","active","","active"));
            quizmodelArrayList.add(new Quizmodel("The librarian read the book to the students.","active","passive","passive", "active","active"));
            quizmodelArrayList.add(new Quizmodel("The money was stolen.", "active", "passive", "active", "passive", "passive"));
            quizmodelArrayList.add(new Quizmodel("They are paid on Fridays.", "active", "passive", "passive", "active", "passive"));
            quizmodelArrayList.add(new Quizmodel("The movie is being made in Hollywood.", "active", "passive", "active", "passive", "passive"));
            quizmodelArrayList.add(new Quizmodel("I washed my car three weeks ago.", "active", "passive", "passive", "active", "active"));
            quizmodelArrayList.add(new Quizmodel("His hair was cut by a professional.", "active", "passive", "active", "passive", "passive"));
            quizmodelArrayList.add(new Quizmodel("I will introduce you to my boss this week", "active", "passive", "active", "passive", "active"));
            quizmodelArrayList.add(new Quizmodel("It would have been fixed at the weekend.", "active", "passive", "passive", "active", "passive"));
            quizmodelArrayList.add(new Quizmodel(" The national anthem is being sung by Jason this time.", "passive", "active", "passive", "active", "passive"));

            option3btn.setVisibility(View.GONE);
            option4btn.setVisibility(View.GONE);
        }



        else if (type!=null && type.toLowerCase(Locale.ROOT).equalsIgnoreCase("subject")) {

            title_quiz.setText("Subject And Predicate");

            quizmodelArrayList.add(new Quizmodel("\uD835\uDE4F\uD835\uDE5D\uD835\uDE5A \uD835\uDE57\uD835\uDE5A\uD835\uDE56\uD835\uDE6A\uD835\uDE69\uD835\uDE5E\uD835\uDE5B\uD835\uDE6A\uD835\uDE61 \uD835\uDE6C\uD835\uDE64\uD835\uDE62\uD835\uDE56\uD835\uDE63 was wearing a red dress.","subject","predicate","active","","subject"));
            quizmodelArrayList.add(new Quizmodel("Next month, \uD835\uDE62\uD835\uDE6E \uD835\uDE59\uD835\uDE56\uD835\uDE6A\uD835\uDE5C\uD835\uDE5D\uD835\uDE69\uD835\uDE5A\uD835\uDE67 is getting married in London.","subject","predicate","passive", "active","subject"));
            quizmodelArrayList.add(new Quizmodel("My father and my uncle \uD835\uDE56\uD835\uDE67\uD835\uDE5A \uD835\uDE59\uD835\uDE5E\uD835\uDE68\uD835\uDE58\uD835\uDE6A\uD835\uDE68\uD835\uDE68\uD835\uDE5E\uD835\uDE63\uD835\uDE5C \uD835\uDE5D\uD835\uDE64\uD835\uDE6C \uD835\uDE69\uD835\uDE64 \uD835\uDE68\uD835\uDE5A\uD835\uDE69 \uD835\uDE6A\uD835\uDE65 \uD835\uDE56 \uD835\uDE63\uD835\uDE5A\uD835\uDE6C \uD835\uDE57\uD835\uDE6A\uD835\uDE68\uD835\uDE5E\uD835\uDE63\uD835\uDE5A\uD835\uDE68\uD835\uDE68 \uD835\uDE69\uD835\uDE64\uD835\uDE5C\uD835\uDE5A\uD835\uDE69\uD835\uDE5D\uD835\uDE5A\uD835\uDE67", "subject", "predicate", "active", "passive", "predicate"));
            quizmodelArrayList.add(new Quizmodel("My students \uD835\uDE56\uD835\uDE61\uD835\uDE6C\uD835\uDE56\uD835\uDE6E\uD835\uDE68 \uD835\uDE59\uD835\uDE64 \uD835\uDE69\uD835\uDE5D\uD835\uDE5A\uD835\uDE5E\uD835\uDE67 \uD835\uDE5D\uD835\uDE64\uD835\uDE62\uD835\uDE5A\uD835\uDE6C\uD835\uDE64\uD835\uDE67\uD835\uDE60", "subject", "predicate", "passive", "active", "predicate"));
            quizmodelArrayList.add(new Quizmodel("The teacher and the administration \uD835\uDE56\uD835\uDE67\uD835\uDE5A \uD835\uDE5B\uD835\uDE5A\uD835\uDE59 \uD835\uDE6A\uD835\uDE65 \uD835\uDE6C\uD835\uDE5E\uD835\uDE69\uD835\uDE5D \uD835\uDE69\uD835\uDE5D\uD835\uDE5A \uD835\uDE57\uD835\uDE5A\uD835\uDE5D\uD835\uDE56\uD835\uDE6B\uD835\uDE5E\uD835\uDE64\uD835\uDE67 \uD835\uDE64\uD835\uDE5B \uD835\uDE56 \uD835\uDE59\uD835\uDE5E\uD835\uDE5B\uD835\uDE5B\uD835\uDE5E\uD835\uDE58\uD835\uDE6A\uD835\uDE61\uD835\uDE69 \uD835\uDE68\uD835\uDE69\uD835\uDE6A\uD835\uDE59\uD835\uDE5A\uD835\uDE63\uD835\uDE69.", "subject", "predicate", "active", "passive", "predicate"));
            quizmodelArrayList.add(new Quizmodel("\uD835\uDE43\uD835\uDE5A has a huge beautiful house.", "subject", "predicate", "passive", "active", "Subject"));
            quizmodelArrayList.add(new Quizmodel("The soup \uD835\uDE69\uD835\uDE56\uD835\uDE68\uD835\uDE69\uD835\uDE5A\uD835\uDE68 \uD835\uDE5C\uD835\uDE64\uD835\uDE64\uD835\uDE59.", "subject", "predicate", "active", "passive", "predicate"));
            quizmodelArrayList.add(new Quizmodel("They \uD835\uDE56\uD835\uDE67\uD835\uDE5A \uD835\uDE6C\uD835\uDE56\uD835\uDE69\uD835\uDE58\uD835\uDE5D\uD835\uDE5E\uD835\uDE63\uD835\uDE5C \uD835\uDE56 \uD835\uDE5D\uD835\uDE64\uD835\uDE67\uD835\uDE67\uD835\uDE64\uD835\uDE67 \uD835\uDE5B\uD835\uDE5E\uD835\uDE61\uD835\uDE62.", "subject", "predicate", "active", "passive", "predicate"));
            quizmodelArrayList.add(new Quizmodel("Every weekend, \uD835\uDE62\uD835\uDE6E \uD835\uDE6C\uD835\uDE5E\uD835\uDE5B\uD835\uDE5A goes jogging.", "subject", "predicate", "passive", "active", "subject"));
            quizmodelArrayList.add(new Quizmodel("A rich pretty woman \uD835\uDE6C\uD835\uDE56\uD835\uDE68 \uD835\uDE6C\uD835\uDE56\uD835\uDE61\uD835\uDE60\uD835\uDE5E\uD835\uDE63\uD835\uDE5C \uD835\uDE5D\uD835\uDE64\uD835\uDE62\uD835\uDE5A \uD835\uDE56\uD835\uDE69 \uD835\uDE63\uD835\uDE5E\uD835\uDE5C\uD835\uDE5D\uD835\uDE69 \uD835\uDE56\uD835\uDE61\uD835\uDE64\uD835\uDE63\uD835\uDE5C \uD835\uDE56 \uD835\uDE59\uD835\uDE56\uD835\uDE67\uD835\uDE60 \uD835\uDE67\uD835\uDE64\uD835\uDE56\uD835\uDE59. ", "subject", "predicate", "passive", "active", "predicate"));

            option3btn.setVisibility(View.GONE);
            option4btn.setVisibility(View.GONE);

        }

        else if (type!=null && type.toLowerCase(Locale.ROOT).equalsIgnoreCase("pharse")) {

            title_quiz.setText("Phrase and Clause");

            quizmodelArrayList.add(new Quizmodel("\uD835\uDE41\uD835\uDE56\uD835\uDE58\uD835\uDE5A\uD835\uDE59 \uD835\uDE6C\uD835\uDE5E\uD835\uDE69\uD835\uDE5D \uD835\uDE68\uD835\uDE64 \uD835\uDE62\uD835\uDE56\uD835\uDE63\uD835\uDE6E \uD835\uDE65\uD835\uDE67\uD835\uDE64\uD835\uDE57\uD835\uDE61\uD835\uDE5A\uD835\uDE62\uD835\uDE68, I decided to get professional help.","Phrase","Clause","active","","Phrase"));
            quizmodelArrayList.add(new Quizmodel("\uD835\uDE4E\uD835\uDE5D\uD835\uDE5A \uD835\uDE56\uD835\uDE67\uD835\uDE67\uD835\uDE5E\uD835\uDE6B\uD835\uDE5A\uD835\uDE59 \uD835\uDE69\uD835\uDE64 \uD835\uDE6C\uD835\uDE64\uD835\uDE67\uD835\uDE60 \uD835\uDE64\uD835\uDE63 \uD835\uDE69\uD835\uDE5E\uD835\uDE62\uD835\uDE5A in spite of leaving home so late.","Phrase","Clause","passive", "active","Clause"));
            quizmodelArrayList.add(new Quizmodel("Before taking any medicine, \uD835\uDE44 \uD835\uDE56\uD835\uDE61\uD835\uDE6C\uD835\uDE56\uD835\uDE6E\uD835\uDE68 \uD835\uDE68\uD835\uDE65\uD835\uDE5A\uD835\uDE56\uD835\uDE60 \uD835\uDE69\uD835\uDE64 \uD835\uDE62\uD835\uDE6E \uD835\uDE59\uD835\uDE64\uD835\uDE58\uD835\uDE69\uD835\uDE64\uD835\uDE67.", "Phrase", "Clause", "active", "passive", "Clause"));
            quizmodelArrayList.add(new Quizmodel("Mark has lived outside of his country \uD835\uDE5B\uD835\uDE64\uD835\uDE67 14 \uD835\uDE6E\uD835\uDE5A\uD835\uDE56\uD835\uDE67\uD835\uDE68.", "Phrase", "Clause", "passive", "active", "Phrase"));
            quizmodelArrayList.add(new Quizmodel("In the morning \uD835\uDE5E\uD835\uDE69’\uD835\uDE68 \uD835\uDE57\uD835\uDE5A\uD835\uDE68\uD835\uDE69 \uD835\uDE69\uD835\uDE64 \uD835\uDE5C\uD835\uDE5A\uD835\uDE69 \uD835\uDE6A\uD835\uDE65 \uD835\uDE5A\uD835\uDE56\uD835\uDE67\uD835\uDE61\uD835\uDE6E.", "Phrase", "Clause", "active", "passive", "Clause"));
            quizmodelArrayList.add(new Quizmodel("\uD835\uDE43\uD835\uDE56\uD835\uDE6B\uD835\uDE5E\uD835\uDE63\uD835\uDE5C \uD835\uDE56\uD835\uDE61\uD835\uDE6C\uD835\uDE56\uD835\uDE6E\uD835\uDE68 \uD835\uDE57\uD835\uDE5A\uD835\uDE5A\uD835\uDE63 \uD835\uDE60\uD835\uDE5A\uD835\uDE5A\uD835\uDE63 \uD835\uDE64\uD835\uDE63 \uD835\uDE58\uD835\uDE56\uD835\uDE67\uD835\uDE5E\uD835\uDE63\uD835\uDE5C \uD835\uDE5B\uD835\uDE64\uD835\uDE67 \uD835\uDE65\uD835\uDE5A\uD835\uDE64\uD835\uDE65\uD835\uDE61\uD835\uDE5A, Susan decided to become a nurse.", "Phrase", "Clause", "passive", "active", "Phrase"));
            quizmodelArrayList.add(new Quizmodel("They were annoyed \uD835\uDE57\uD835\uDE6E \uD835\uDE69\uD835\uDE5D\uD835\uDE5A \uD835\uDE57\uD835\uDE56\uD835\uDE57\uD835\uDE6E \uD835\uDE58\uD835\uDE67\uD835\uDE6E\uD835\uDE5E\uD835\uDE63\uD835\uDE5C \uD835\uDE68\uD835\uDE64 \uD835\uDE61\uD835\uDE64\uD835\uDE6A\uD835\uDE59\uD835\uDE61\uD835\uDE6E.", "Phrase", "Clause", "active", "passive", "Phrase"));
            quizmodelArrayList.add(new Quizmodel("Although injured, \uD835\uDE45\uD835\uDE64\uD835\uDE5D\uD835\uDE63 \uD835\uDE62\uD835\uDE56\uD835\uDE63\uD835\uDE56\uD835\uDE5C\uD835\uDE5A\uD835\uDE59 \uD835\uDE69\uD835\uDE64 \uD835\uDE58\uD835\uDE67\uD835\uDE56\uD835\uDE6C\uD835\uDE61 \uD835\uDE69\uD835\uDE64 \uD835\uDE68\uD835\uDE56\uD835\uDE5B\uD835\uDE5A\uD835\uDE69\uD835\uDE6E.", "Phrase", "Clause", "active", "passive", "Clause"));
            quizmodelArrayList.add(new Quizmodel(".I will be running for President.", "Phrase", "Clause", "passive", "active", "Phrase"));
            quizmodelArrayList.add(new Quizmodel("I like the guy with the green shirt.", "Phrase", "Clause", "passive", "active", "Clause"));

            option3btn.setVisibility(View.GONE);
            option4btn.setVisibility(View.GONE);

        }

        else if (type!=null && type.toLowerCase(Locale.ROOT).equalsIgnoreCase("questiontag")) {

            title_quiz.setText("Question Tag");

            quizmodelArrayList.add(new Quizmodel("This tree can’t bear fruit,","can’t it?"," can it?","will it?",""," can it?"));
            quizmodelArrayList.add(new Quizmodel(" She was feeling cold,","wasn’t she?","was she?","doesn’t she?", "","wasn’t she?"));
            quizmodelArrayList.add(new Quizmodel("She is singing,", " isn’t she?", "is she?", "doesn’t she?", "", " isn’t she?"));
            quizmodelArrayList.add(new Quizmodel("I am working,", "am I?", "amn’t I?", "aren’t I?", "", "aren’t I?"));
            quizmodelArrayList.add(new Quizmodel("The plumber fixes pipes,", "does he?", "didn’t he?", "doesn’t he?", "", "doesn’t he?"));
            quizmodelArrayList.add(new Quizmodel("Mr.Sachin heads the meeting,", "doesn’t he?", "does he?", "will n’t he?", "", "doesn’t he?"));
            quizmodelArrayList.add(new Quizmodel("Dhoni has a motor cycle,", "hasn’t he?", "don’t he?", " doesn’t he?", "", "hasn’t he?"));
            quizmodelArrayList.add(new Quizmodel("They had a meeting last week,", " haven’t they?", "hadn’t they?", "have they?", "", "hadn’t they?"));
            quizmodelArrayList.add(new Quizmodel("She has a problem to solve,", "haven’t she?", "has she?", "hasn’t she?", "", "hasn’t she?"));
            quizmodelArrayList.add(new Quizmodel("Mohammad never gets up early,", "is he?", "does he?", "doesn't he?", "", "does he?"));

            option4btn.setVisibility(View.GONE);

        }






    }
}