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

import com.example.reciepemad1.Listeners.RecipeClickLisreners;
import com.example.reciepemad1.Models.SimilarRecipeResponse;
import com.example.reciepemad1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeViewHolder>{
    Context context;
    List<SimilarRecipeResponse> list;
    RecipeClickLisreners listener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> list, RecipeClickLisreners listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
holder.textview_similar_title.setText(list.get(position).title);
holder.textview_similar_title.setSelected(true);
holder.textview_similar_serving.setText(list.get(position).servings+" persons");
        Picasso.get().load("https://spoonacular.com/recipeImages/"+list.get(position).id+"-556x370."+list.get(position).imageType).into(holder.imageView_similar);
   holder.similar_recipe_holder.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
       }
   });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class SimilarRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView similar_recipe_holder;
    TextView textview_similar_title;
ImageView imageView_similar;
TextView textview_similar_serving;
    public SimilarRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        similar_recipe_holder=itemView.findViewById(R.id.similar_recipe_holder);
        textview_similar_title=itemView.findViewById(R.id.textview_similar_title);
        imageView_similar=itemView.findViewById(R.id.imageView_similar);
        textview_similar_serving=itemView.findViewById(R.id.textview_similar_serving);



    }
}
