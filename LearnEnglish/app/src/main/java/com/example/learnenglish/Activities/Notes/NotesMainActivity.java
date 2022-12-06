package com.example.learnenglish.Activities.Notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.learnenglish.NotesFragment.DailyListFragment;
import com.example.learnenglish.NotesFragment.MyNotesFragment;
import com.example.learnenglish.NotesFragment.RemainderFragment;
import com.example.learnenglish.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class NotesMainActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_main);

        chipNavigationBar=findViewById(R.id.bottom_navigation_bar);
        chipNavigationBar.setItemSelected(R.id.notes_home,true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyNotesFragment()).commit();

        bottomMenu();
    }

    private void bottomMenu() {

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i)
                {
                    case R.id.notes_home:
                        fragment = new MyNotesFragment();
                        break;
                    case R.id.notes_remainder:
                        fragment = new RemainderFragment();
                        break;
                    case R.id.notes_list:
                        fragment = new DailyListFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
    }
}