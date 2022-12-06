package com.example.learnenglish.NotesFragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.learnenglish.Activities.AddNewRemainderActivity;
import com.example.learnenglish.Adapters.MyNoteAdapter;
import com.example.learnenglish.Adapters.RemainderAdapter;
import com.example.learnenglish.Database.MyNoteDatabase;
import com.example.learnenglish.Entities.MyNoteEntities;
import com.example.learnenglish.Entities.MyRemainderEntities;
import com.example.learnenglish.R;

import java.util.ArrayList;
import java.util.List;

public class RemainderFragment extends Fragment {

    ImageView addnewRemainder;
    public static final int REQUEST_CODE_ADD_NOTE=1;

    private RecyclerView noteRec;
    private List<MyRemainderEntities> noteEntitiesList;
    private RemainderAdapter myNoteAdapter;

    public RemainderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_remainder, container, false);

        addnewRemainder=root.findViewById(R.id.add_remainder);
        addnewRemainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), AddNewRemainderActivity.class),REQUEST_CODE_ADD_NOTE);
            }
        });

        noteRec = root.findViewById(R.id.remainder_Rec);
        noteRec.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        noteEntitiesList = new ArrayList<>();
        myNoteAdapter=new RemainderAdapter(noteEntitiesList);
        noteRec.setAdapter(myNoteAdapter);


        getAllReminder();
        return root;
    }

    private void getAllReminder() {

        class GetAllReminder extends AsyncTask<Void,Void,List<MyRemainderEntities>>{

            @Override
            protected List<MyRemainderEntities> doInBackground(Void... voids) {
                return MyNoteDatabase.getMyNoteDatabase(getActivity().getApplicationContext()).notesDao().getAllRemainder();
            }

            @Override
            protected void onPostExecute(List<MyRemainderEntities> myRemainderEntities) {
                super.onPostExecute(myRemainderEntities);

                if (noteEntitiesList.size()==0)
                {
                    noteEntitiesList.addAll(myRemainderEntities);
                    myNoteAdapter.notifyDataSetChanged();
                }
                else
                {
                    noteEntitiesList.add(0,myRemainderEntities.get(0));
                    myNoteAdapter.notifyItemInserted(0);
                }

                noteRec.smoothScrollToPosition(0);
            }
            }

        new GetAllReminder().execute();
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK)
        {
            getAllReminder();
        }
    }
    }
