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

import com.example.learnenglish.Activities.GrammerExplanation.TensesActivity;
import com.example.learnenglish.Activities.QuizQuestionsActivity;
import com.example.learnenglish.Models.PracticeQuizContentModel;
import com.example.learnenglish.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PracticeQuizContentAdapter extends RecyclerView.Adapter<PracticeQuizContentAdapter.ViewHolder> {
    private Context context;
    private List<PracticeQuizContentModel> practiceQuizContentModelList;


    public PracticeQuizContentAdapter(Context context, List<PracticeQuizContentModel> practiceQuizContentModelList) {
        this.context = context;
        this.practiceQuizContentModelList = practiceQuizContentModelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.practicequizcard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(practiceQuizContentModelList.get(position).getTitle());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuizQuestionsActivity.class);
                intent.putExtra("type",practiceQuizContentModelList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return practiceQuizContentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.practicequizcontent);
        }
    }
}
