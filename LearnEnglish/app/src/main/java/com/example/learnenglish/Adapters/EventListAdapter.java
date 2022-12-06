package com.example.learnenglish.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Entities.EventListEntities;
import com.example.learnenglish.R;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    List<EventListEntities> lists;

    public EventListAdapter(List<EventListEntities> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setNote(lists.get(position));

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle,textDateTime,textNote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle=itemView.findViewById(R.id.shopping_heading);
            textNote=itemView.findViewById(R.id.shopping_list_text);
            textDateTime=itemView.findViewById(R.id.date_shop);
        }

        public void setNote(EventListEntities eventListEntities) {

            textTitle.setText(eventListEntities.getTitle());
            textNote.setText(eventListEntities.getNotetext());
            textDateTime.setText(eventListEntities.getDateTime());
        }
    }
}
