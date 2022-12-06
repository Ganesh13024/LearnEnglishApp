package com.example.learnenglish.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Activities.GrammerExplanation.question_tag;
import com.example.learnenglish.Activities.GrammerExplanation.subject_predicate;
import com.example.learnenglish.Models.HomeModel;
import com.example.learnenglish.R;
import com.example.learnenglish.Activities.GrammerExplanation.TensesActivity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    private Context context;
    private List<HomeModel> homeModelList;

    public HomeAdapter(Context context, List<HomeModel> homeModelList) {
        this.context = context;
        this.homeModelList = homeModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_contents,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(homeModelList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type= homeModelList.get(position).getType();
                if (type!=null && type.equalsIgnoreCase("tenses")){
                    context.startActivity(new Intent(context, TensesActivity.class));
                }

                if (type!=null && type.equalsIgnoreCase("questiontag")){
                    context.startActivity(new Intent(context, question_tag.class));
                }

                if (type!=null && type.equalsIgnoreCase("subject")){
                    context.startActivity(new Intent(context, subject_predicate.class));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return homeModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.home_content_title);
        }
    }
}
