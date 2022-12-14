package com.example.learnenglish.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Models.Phonetics;
import com.example.learnenglish.R;

import java.io.IOException;
import java.util.List;

public class PhoneticsAdapter extends RecyclerView.Adapter<PhoneticsAdapter.PhoneticsViewHolder> {
    private Context context;
    private List<Phonetics> phoneticsList;

    public PhoneticsAdapter(Context context, List<Phonetics> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }

    @NonNull
    @Override
    public PhoneticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phonetics_lists,parent,false);
        return new PhoneticsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView_phonetics.setText(phoneticsList.get(position).getText());
        MediaPlayer player = new MediaPlayer();
        holder.imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    player.reset();
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource(phoneticsList.get(position).getAudio());
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "cannot Play audio", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }

    public class PhoneticsViewHolder extends RecyclerView.ViewHolder {
        TextView textView_phonetics;
        ImageButton imageButtonPlay;
        public PhoneticsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_phonetics=itemView.findViewById(R.id.textview_phonetics);
            imageButtonPlay=itemView.findViewById(R.id.imgBtn);
        }
    }
}
