package com.example.learnenglish.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Activities.MainStoryActivity;
import com.example.learnenglish.Models.FavouriteStoryModel;
import com.example.learnenglish.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FavouriteStoryAdapter extends RecyclerView.Adapter<FavouriteStoryAdapter.ViewHolder> {

    Context context;
    List<FavouriteStoryModel> favouriteStoryModelList;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;


    public FavouriteStoryAdapter(Context context, List<FavouriteStoryModel> favouriteStoryModelList) {
        this.context = context;
        this.favouriteStoryModelList = favouriteStoryModelList;
        fstore=FirebaseFirestore.getInstance();
        fAuth=FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_stories_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(favouriteStoryModelList.get(position).getTitle());

        String text = ("" + favouriteStoryModelList.get(position).getStory1() + "" + favouriteStoryModelList.get(position).getStory2() + "" + favouriteStoryModelList.get(position).getStory3() + "" + favouriteStoryModelList.get(position).getStory4() + "" + favouriteStoryModelList.get(position).getStory5());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, MainStoryActivity.class);
                intent.putExtra("type",favouriteStoryModelList.get(position).getType());
                intent.putExtra("story",text);
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fstore.collection("FavouriteStories").document(fAuth.getCurrentUser().getUid()).collection("CurrentUser").document(favouriteStoryModelList.get(position).getDocumentId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            favouriteStoryModelList.remove(favouriteStoryModelList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Favourite Story Removed", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            Toast.makeText(context, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });




    }

    @Override
    public int getItemCount() {
        return favouriteStoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.fav_story_title);
            delete=itemView.findViewById(R.id.delete_fav_stories);
        }
    }
}
