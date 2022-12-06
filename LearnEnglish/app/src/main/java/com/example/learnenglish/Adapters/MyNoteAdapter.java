package com.example.learnenglish.Adapters;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Entities.MyNoteEntities;
import com.example.learnenglish.Listeners.MyNotesListener;
import com.example.learnenglish.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MyNoteAdapter extends RecyclerView.Adapter<MyNoteAdapter.ViewHolder> {

    List<MyNoteEntities> noteEntitiesList;

    MyNotesListener myNotesListener;

    private List<MyNoteEntities> noteSearch;
    private Timer timer;

    public MyNoteAdapter(List<MyNoteEntities> noteEntitiesList, MyNotesListener myNotesListener) {
        this.noteEntitiesList = noteEntitiesList;
        this.myNotesListener = myNotesListener;
        noteSearch = noteEntitiesList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.setNote(noteEntitiesList.get(position));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myNotesListener.myNoteClick(noteEntitiesList.get(position),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteEntitiesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title,textNote,datetime;
        private LinearLayout linearLayout;
        RoundedImageView roundedImageView;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            title=itemView.findViewById(R.id.textTitle11);
            textNote=itemView.findViewById(R.id.textNote11);
            datetime=itemView.findViewById(R.id.textDateTime2);
            linearLayout=itemView.findViewById(R.id.layoutNote);
            roundedImageView=itemView.findViewById(R.id.image_note_item);
        }

        public void setNote(MyNoteEntities noteEntities) {
            title.setText(noteEntities.getTitle());
            textNote.setText(noteEntities.getNotetext());
            datetime.setText(noteEntities.getDateTime());


            GradientDrawable gradientDrawable=(GradientDrawable) linearLayout.getBackground();
            if (noteEntities.getColor()!=null)
            {
                gradientDrawable.setColor(Color.parseColor(noteEntities.getColor()));

            }
            else
            {
                gradientDrawable.setColor(Color.parseColor("#FF937B"));
            }

            if (noteEntities.getImagepath()!=null){
                roundedImageView.setImageBitmap(BitmapFactory.decodeFile(noteEntities.getImagepath()));
                roundedImageView.setVisibility(View.VISIBLE);
            }
            else {
                roundedImageView.setVisibility(View.GONE);
            }
        }
    }

    public void searchNote(final String searchKeyword){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (searchKeyword.trim().isEmpty())
                {
                    noteEntitiesList=noteSearch;
                }
                else {
                    ArrayList<MyNoteEntities> temp = new ArrayList<>();
                    for (MyNoteEntities entities : noteSearch)
                    {
                        if (entities.getTitle().toLowerCase().contains(searchKeyword.toLowerCase())||entities.getNotetext().toLowerCase().contains(searchKeyword.toLowerCase())){

                            temp.add(entities);

                        }
                    }

                    noteEntitiesList = temp;
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        },500);


    }

    public void cancleTimer(){
        if (timer!=null)
        {
            timer.cancel();
        }
    }
}
