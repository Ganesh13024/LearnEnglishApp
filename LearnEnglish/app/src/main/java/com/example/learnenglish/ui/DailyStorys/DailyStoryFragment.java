package com.example.learnenglish.ui.DailyStorys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Activities.GrammerExplanation.TensesActivity;
import com.example.learnenglish.Adapters.DailystoryAdapter;
import com.example.learnenglish.Models.DailystoryModel;
import com.example.learnenglish.Models.HomeModel;
import com.example.learnenglish.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DailyStoryFragment extends Fragment {

    RecyclerView dailystoryrec;
    FirebaseFirestore db;

    ScrollView scrollView;
    ProgressBar progressBar;


    DailystoryAdapter dailystoryAdapter;
    List<DailystoryModel> dailystoryModelList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_daily_storys,container,false);

        db=FirebaseFirestore.getInstance();

        scrollView = root.findViewById(R.id.dailystory_scroll);

        progressBar = root.findViewById(R.id.progressss);


        scrollView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        dailystoryrec=root.findViewById(R.id.dailystory_rec);

        dailystoryrec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        dailystoryModelList=new ArrayList<>();

        dailystoryAdapter=new DailystoryAdapter(getActivity(),dailystoryModelList);

        dailystoryrec.setAdapter(dailystoryAdapter);

        db.collection("DailyStories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DailystoryModel dailystoryModel = document.toObject(DailystoryModel.class);
                                dailystoryModelList.add(dailystoryModel);
                                dailystoryAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);

                            }
                        } else {

                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });




        return root;
    }
}