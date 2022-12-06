package com.example.learnenglish.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Activities.GrammerExplanation.TensesActivity;
import com.example.learnenglish.Activities.MainStoryActivity;
import com.example.learnenglish.Models.DailystoryModel;
import com.example.learnenglish.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

public class DailystoryAdapter extends RecyclerView.Adapter<DailystoryAdapter.Viewholder> {

    private Context context;
    private List<DailystoryModel> dailystoryModelList;
    FirebaseAuth fAuth=FirebaseAuth.getInstance();
    FirebaseFirestore fstore=FirebaseFirestore.getInstance();

    public DailystoryAdapter() {
    }

    public DailystoryAdapter(Context context, List<DailystoryModel> dailystoryModelList) {
        this.context = context;
        this.dailystoryModelList = dailystoryModelList;
    }

    @NonNull
    @Override
    public DailystoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dailystorycard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DailystoryAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(dailystoryModelList.get(position).getTitle());
        String text = ("" + dailystoryModelList.get(position).getStory1() + "" + dailystoryModelList.get(position).getStory2() + "" + dailystoryModelList.get(position).getStory3() + "" + dailystoryModelList.get(position).getStory4() + "" + dailystoryModelList.get(position).getStory5());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, MainStoryActivity.class);
                intent.putExtra("type",dailystoryModelList.get(position).getType());
                intent.putExtra("story",text);
                context.startActivity(intent);
            }
        });


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final HashMap<String,Object> cartMap = new HashMap<>();


                cartMap.put("title",dailystoryModelList.get(position).getTitle());
                cartMap.put("no",dailystoryModelList.get(position).getNo());
                cartMap.put("story1",dailystoryModelList.get(position).getStory1());
                cartMap.put("story2",dailystoryModelList.get(position).getStory2());
                cartMap.put("story3",dailystoryModelList.get(position).getStory3());
                cartMap.put("story4",dailystoryModelList.get(position).getStory4());
                cartMap.put("story5",dailystoryModelList.get(position).getStory5());
                cartMap.put("moral",dailystoryModelList.get(position).getMoral());
                cartMap.put("img_url",dailystoryModelList.get(position).getImg_url());
                cartMap.put("type",dailystoryModelList.get(position).getType());

                fstore.collection("FavouriteStories").document(fAuth.getCurrentUser().getUid()).collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return dailystoryModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView title;
        TextView no;

        CheckBox checkBox;

        Button button;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.dailystory_title);

            button=itemView.findViewById(R.id.add_to_bookmark_btn);
        }
    }
}
