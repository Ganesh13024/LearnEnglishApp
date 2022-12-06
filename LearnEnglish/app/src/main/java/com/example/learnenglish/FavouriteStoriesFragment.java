package com.example.learnenglish;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.learnenglish.Adapters.FavouriteStoryAdapter;
import com.example.learnenglish.Models.FavouriteStoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class FavouriteStoriesFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    FavouriteStoryAdapter favouriteStoryAdapter;
    List<FavouriteStoryModel> favouriteStoryModelList;

    public FavouriteStoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_nav_favourite_stories, container, false);

        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        recyclerView=root.findViewById(R.id.favstory_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        favouriteStoryModelList=new ArrayList<>();
        favouriteStoryAdapter=new FavouriteStoryAdapter(getActivity(),favouriteStoryModelList);
        recyclerView.setAdapter(favouriteStoryAdapter);

        db.collection("FavouriteStories").document(auth.getCurrentUser().getUid()).collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        String documentId = documentSnapshot.getId();
                        FavouriteStoryModel favouriteStoryModel =documentSnapshot.toObject(FavouriteStoryModel.class);

                        favouriteStoryModel.setDocumentId(documentId);
                        favouriteStoryModelList.add(favouriteStoryModel);
                        favouriteStoryAdapter.notifyDataSetChanged();
                    }
                }
            }
        });



        return root;
    }
}