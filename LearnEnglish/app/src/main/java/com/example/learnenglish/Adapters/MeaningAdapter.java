package com.example.learnenglish.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Models.Meaning;
import com.example.learnenglish.R;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder> {
    private Context context;
    private List<Meaning> meaningList;

    public MeaningAdapter(Context context, List<Meaning> meaningList) {
        this.context = context;
        this.meaningList = meaningList;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.meaning_list,parent,false);
        return new MeaningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {

        holder.textView_part_of_speech.setText("Parts of Speech "+meaningList.get(position).getPartOfSpeech());
        holder.recyclerView_definition.setHasFixedSize(true);
        holder.recyclerView_definition.setLayoutManager(new GridLayoutManager(context,1));

        DefinitionsAdapter definitionsAdapter = new DefinitionsAdapter(context,meaningList.get(position).getDefinitions());
        holder.recyclerView_definition.setAdapter(definitionsAdapter);

    }

    @Override
    public int getItemCount() {
        return meaningList.size();
    }

    public class MeaningViewHolder extends RecyclerView.ViewHolder {

        TextView textView_part_of_speech;
        RecyclerView recyclerView_definition;

        public MeaningViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_part_of_speech=itemView.findViewById(R.id.text_part_speech);
            recyclerView_definition=itemView.findViewById(R.id.recycler_definitions);
        }
    }
}
