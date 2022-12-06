package com.example.learnenglish.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Models.ScoreModel;
import com.example.learnenglish.R;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    List<ScoreModel> scoreModelList;
    Context context;

    public ScoreAdapter(List<ScoreModel> scoreModelList, Context context) {
        this.scoreModelList = scoreModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ScoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScoreAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.practicequizcard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreAdapter.ViewHolder holder, int position) {

        holder.score.setText("Your Score"+scoreModelList.get(position).getScore()+"/10");
    }

    @Override
    public int getItemCount() {
        return scoreModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView score;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            score = itemView.findViewById(R.id.scorecard);
        }
    }
}
