package com.example.reciepemad1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reciepemad1.Models.Recipe;
import com.example.reciepemad1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomReciepeAdapter extends RecyclerView.Adapter< RandomReciepeViewHolder> {
Context context;
List<Recipe> list;

    public RandomReciepeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RandomReciepeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomReciepeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_layout_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomReciepeViewHolder holder, int position) {
        holder.textview_title.setText(list.get(position).title);
        holder.textview_title.setSelected(true);
        holder.textView_likes.setText(list.get(position).aggregateLikes+"Likes");
        holder.textView_servings.setText(list.get(position).servings+"Servings");
        holder.textView_time.setText(list.get(position).readyInMinutes+"Minutes");
        Picasso.get().load(list.get(position).image).into(holder.imageView_food);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class RandomReciepeViewHolder extends RecyclerView.ViewHolder {
CardView random_list_container;
TextView textview_title,textView_servings,textView_likes,textView_time;
ImageView imageView_food;


    public RandomReciepeViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_container=itemView.findViewById(R.id.random_list_container);
        textview_title=itemView.findViewById(R.id.textview_title);
        textView_servings=itemView.findViewById(R.id.textView_servings);
        textView_likes=itemView.findViewById(R.id.textView_likes);
        textView_time=itemView.findViewById(R.id.textView_time);
        imageView_food=itemView.findViewById(R.id.imageView_food);
    }
}
