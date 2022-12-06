package com.example.learnenglish.Adapters;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Entities.MyRemainderEntities;
import com.example.learnenglish.R;

import java.util.List;

public class RemainderAdapter extends RecyclerView.Adapter<RemainderAdapter.ViewHolder> {

    List<MyRemainderEntities> remainderEntities;

    public RemainderAdapter(List<MyRemainderEntities> remainderEntities) {
        this.remainderEntities = remainderEntities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.remember_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setRemainder(remainderEntities.get(position));

    }

    @Override
    public int getItemCount() {
        return remainderEntities.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title,dateTime;
        private View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.remainder_heading);
            dateTime=itemView.findViewById(R.id.date_remainder);
            view=itemView.findViewById(R.id.view_remainder_11);
        }

        public void setRemainder(MyRemainderEntities myRemainderEntities) {

            title.setText(myRemainderEntities.getTitle());
            dateTime.setText(myRemainderEntities.getDateTime());

            GradientDrawable gradientDrawable=(GradientDrawable) view.getBackground();
            if (myRemainderEntities.getColor()!=null)
            {
                gradientDrawable.setColor(Color.parseColor(myRemainderEntities.getColor()));

            }
            else
            {
                gradientDrawable.setColor(Color.parseColor("#FFFB7B"));
            }

        }
    }
}
