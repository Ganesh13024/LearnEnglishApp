package com.example.learnenglish.ui.PracticeQuiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Activities.Notes.NotesMainActivity;
import com.example.learnenglish.Activities.QuizQuestionsActivity;
import com.example.learnenglish.Adapters.HomeAdapter;
import com.example.learnenglish.Adapters.PracticeQuizContentAdapter;
import com.example.learnenglish.Adapters.ScoreAdapter;
import com.example.learnenglish.MainActivity;
import com.example.learnenglish.Models.HomeModel;
import com.example.learnenglish.Models.PracticeQuizContentModel;
import com.example.learnenglish.Models.Quizmodel;
import com.example.learnenglish.Models.ScoreModel;
import com.example.learnenglish.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class PracticeQuizFragment extends Fragment {

    RecyclerView quizrec;
    List<ScoreModel> scoreModelList;
    ScoreAdapter scoreAdapter;
    List<PracticeQuizContentModel> practiceQuizContentModelList;
    FirebaseFirestore db;
    FirebaseAuth fAuth;
    PracticeQuizContentAdapter practiceQuizContentAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_practice_quiz,container,false);

        quizrec = root.findViewById(R.id.practicequizrec);
        db=FirebaseFirestore.getInstance();
        fAuth=FirebaseAuth.getInstance();

        quizrec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        scoreModelList=new ArrayList<>();
        scoreAdapter=new ScoreAdapter(scoreModelList,getActivity());
        practiceQuizContentModelList = new ArrayList<>();
        practiceQuizContentAdapter=new PracticeQuizContentAdapter(getActivity(),practiceQuizContentModelList);
        quizrec.setAdapter(practiceQuizContentAdapter);

        /*db.collection("UserScores").document(fAuth.getCurrentUser().getUid()).collection("CurrentUserScore").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        ScoreModel scoreModel = documentSnapshot.toObject(ScoreModel.class);
                        scoreModelList.add(scoreModel);
                        scoreAdapter.notifyDataSetChanged();
                    }
                }
            }
        });*/

        db.collection("GrammerCollections")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PracticeQuizContentModel homeModel = document.toObject(PracticeQuizContentModel.class);
                                practiceQuizContentModelList.add(homeModel);
                                practiceQuizContentAdapter.notifyDataSetChanged();


                            }
                        } else {

                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });


        return root;
    }
}