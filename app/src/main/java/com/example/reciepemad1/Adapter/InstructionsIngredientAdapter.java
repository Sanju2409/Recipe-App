package com.example.reciepemad1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reciepemad1.Models.Ingredient;
import com.example.reciepemad1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionsIngredientAdapter extends RecyclerView.Adapter<InstructionsIngredientViewHolder>{
    Context context;
    List<Ingredient> list;

    public InstructionsIngredientAdapter(Context context, List<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionsIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsIngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsIngredientViewHolder holder, int position) {
holder.textview_instructions_step_item.setText(list.get(position).name);
    holder.textview_instructions_step_item.setSelected(true);
        Picasso.get().load("https://img.spoonacular.com/ingredients_100x100/"+list.get(position).image).into(holder.imageView_instructions_step_items);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionsIngredientViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView_instructions_step_items;
    TextView textview_instructions_step_item;
    public InstructionsIngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_instructions_step_items=itemView.findViewById(R.id.imageView_instructions_step_items);
        textview_instructions_step_item=itemView.findViewById(R.id.textview_instructions_step_item);


    }
}