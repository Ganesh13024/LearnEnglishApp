package com.example.learnenglish.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Activities.ProfileActivity;
import com.example.learnenglish.Adapters.HomeAdapter;
import com.example.learnenglish.Models.HomeModel;
import com.example.learnenglish.R;
import com.example.learnenglish.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView homerec;
    FirebaseFirestore db;

    HomeAdapter homeAdapter;
    List<HomeModel> homeModelList;

    ScrollView scrollView;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container,false);

        db=FirebaseFirestore.getInstance();


        homerec=root.findViewById(R.id.grammer_contents);
        scrollView=root.findViewById(R.id.homescroll);
        progressBar=root.findViewById(R.id.progresson);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        homerec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        homeModelList = new ArrayList<>();
        homeAdapter=new HomeAdapter(getActivity(),homeModelList);
        homerec.setAdapter(homeAdapter);

        db.collection("GrammerCollections")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeModel homeModel = document.toObject(HomeModel.class);
                                homeModelList.add(homeModel);
                                homeAdapter.notifyDataSetChanged();

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