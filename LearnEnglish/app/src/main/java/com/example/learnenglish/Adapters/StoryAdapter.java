package com.example.learnenglish.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.learnenglish.Models.StoryModel;
import com.example.learnenglish.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Locale;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    TextToSpeech t1;

    Context context;
    List<StoryModel> storyModelList;

    public StoryAdapter(Context context, List<StoryModel> storyModelList) {
        this.context = context;
        this.storyModelList = storyModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.storycard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        t1 = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });

        Glide.with(context).load(storyModelList.get(position).getImg_url()).into(holder.imageView);

        holder.title.setText(storyModelList.get(position).getTitle());
        holder.story1.setText("\t\t" + storyModelList.get(position).getStory1());
        holder.story2.setText("\t\t" + storyModelList.get(position).getStory2());
        holder.story3.setText("\t\t" + storyModelList.get(position).getStory3());
        holder.story4.setText("\t\t" + storyModelList.get(position).getStory4());
        holder.story5.setText("\t\t" + storyModelList.get(position).getStory5());
        holder.moral.setText("\t\t" + storyModelList.get(position).getMoral());




    }

    @Override
    public int getItemCount() {
        return storyModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,story1,story2,story3,story4,story5,moral;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.story_img);
            title=itemView.findViewById(R.id.story_title);
            story1=itemView.findViewById(R.id.story1);
            story2=itemView.findViewById(R.id.story2);
            story3=itemView.findViewById(R.id.story3);
            story4=itemView.findViewById(R.id.story4);
            story5=itemView.findViewById(R.id.story5);
            moral=itemView.findViewById(R.id.moral);
        }
    }

}
