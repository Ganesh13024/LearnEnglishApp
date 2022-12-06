package com.example.learnenglish;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnenglish.Activities.MainStoryActivity;
import com.example.learnenglish.Activities.Notes.NotesMainActivity;
import com.example.learnenglish.Activities.SplashScreenActivity;
import com.example.learnenglish.Activities.WelcomeActivity;


public class DailyNotesFragment extends Fragment {

    ImageView imageView;
    TextView textView;

    public DailyNotesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_daily_notes, container, false);

        imageView=root.findViewById(R.id.notes_splash_image);
        textView=root.findViewById(R.id.notes_splash_text);

        // Inflate the layout for this fragment
        imageView.animate().alpha(0f).setDuration(0);
        textView.animate().alpha(0f).setDuration(0);

        imageView.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animator animation) {
                textView.animate().alpha(1f).setDuration(800);

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getActivity(),NotesMainActivity.class);
                startActivity(intent);
            }
        },2000);
        return root;
    }
}