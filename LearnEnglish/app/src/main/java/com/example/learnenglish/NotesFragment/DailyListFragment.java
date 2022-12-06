package com.example.learnenglish.NotesFragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.learnenglish.Activities.AddNewEventsListActivity;
import com.example.learnenglish.Adapters.EventListAdapter;
import com.example.learnenglish.Adapters.MyNoteAdapter;
import com.example.learnenglish.Database.MyNoteDatabase;
import com.example.learnenglish.Entities.EventListEntities;
import com.example.learnenglish.Entities.MyRemainderEntities;
import com.example.learnenglish.R;

import java.util.ArrayList;
import java.util.List;


public class DailyListFragment extends Fragment {

    ImageView addnewlist;

    RecyclerView noteRec;
    List<EventListEntities> noteEntitiesList;
    EventListAdapter myNoteAdapter;
    public static final int REQUEST_CODE_ADD_NOTE = 1;


    public DailyListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_daily_list, container, false);

        addnewlist = root.findViewById(R.id.add_list);
        addnewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), AddNewEventsListActivity.class), REQUEST_CODE_ADD_NOTE);
            }
        });


        noteRec = root.findViewById(R.id.eventlist_rec);
        noteRec.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        noteEntitiesList = new ArrayList<>();
        myNoteAdapter = new EventListAdapter(noteEntitiesList);
        noteRec.setAdapter(myNoteAdapter);


        getAllEventList();

        return root;
    }

    private void getAllEventList() {

        class GetAllReminder extends AsyncTask<Void, Void, List<EventListEntities>> {

            @Override
            protected List<EventListEntities> doInBackground(Void... voids) {
                return MyNoteDatabase.getMyNoteDatabase(getActivity().getApplicationContext()).notesDao().getAllEventsList();
            }

            @Override
            protected void onPostExecute(List<EventListEntities> myRemainderEntities) {
                super.onPostExecute(myRemainderEntities);

                if (noteEntitiesList.size() == 0) {
                    noteEntitiesList.addAll(myRemainderEntities);
                    myNoteAdapter.notifyDataSetChanged();
                } else {
                    noteEntitiesList.add(0, myRemainderEntities.get(0));
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
            getAllEventList();
        }
    }
}