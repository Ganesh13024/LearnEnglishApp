package com.example.learnenglish.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import com.example.learnenglish.Adapters.StoryAdapter;
import com.example.learnenglish.Models.StoryModel;
import com.example.learnenglish.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainStoryActivity extends AppCompatActivity {

    FloatingActionButton play,stop;
    TextToSpeech t1;

    FirebaseFirestore firestore;

    List<StoryModel> storyModelList;
    StoryAdapter storyAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_story);


        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });

        stop=findViewById(R.id.stopbtn);
        stop.setVisibility(View.GONE);
        play=findViewById(R.id.playbutton);
        firestore=FirebaseFirestore.getInstance();

        String type=getIntent().getStringExtra("type");
        String story=getIntent().getStringExtra("story");
        recyclerView=findViewById(R.id.story_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        storyModelList = new ArrayList<>();
        storyAdapter = new StoryAdapter(this,storyModelList);

        recyclerView.setAdapter(storyAdapter);

        if (type != null && type.equalsIgnoreCase("story1"))
        {
            firestore.collection("DailyStories").whereEqualTo("type","story1").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                         for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments())
                         {
                             StoryModel storyModel = documentSnapshot.toObject(StoryModel.class);
                             storyModelList.add(storyModel);
                             storyAdapter.notifyDataSetChanged();
                         }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("story2"))
        {
            firestore.collection("DailyStories").whereEqualTo("type","story2").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments())
                    {
                        StoryModel storyModel = documentSnapshot.toObject(StoryModel.class);
                        storyModelList.add(storyModel);
                        storyAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("story3"))
        {
            firestore.collection("DailyStories").whereEqualTo("type","story3").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments())
                    {
                        StoryModel storyModel = documentSnapshot.toObject(StoryModel.class);
                        storyModelList.add(storyModel);
                        storyAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("story4"))
        {
            firestore.collection("DailyStories").whereEqualTo("type","story4").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments())
                    {
                        StoryModel storyModel = documentSnapshot.toObject(StoryModel.class);
                        storyModelList.add(storyModel);
                        storyAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.speak(story,TextToSpeech.QUEUE_FLUSH,null);
                stop.setVisibility(View.VISIBLE);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.stop();
                stop.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        t1.stop();
    }

}