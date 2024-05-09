package com.example.reciepemad1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reciepemad1.Models.Equipment;
import com.example.reciepemad1.Models.Ingredient;
import com.example.reciepemad1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionsEquipmentAdapter extends RecyclerView.Adapter<InstructionsEquipmentViewHolder>{
    Context context;
    List<Equipment> list;

    public InstructionsEquipmentAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionsEquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsEquipmentViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsEquipmentViewHolder holder, int position) {
    holder.textview_instructions_step_item.setText(list.get(position).name);
    holder.textview_instructions_step_item.setSelected(true);
//        Picasso.get().load("https://img.spoonacular.com/equipment_100x100/"+list.get(position).image).into(holder.imageView_instructions_step_items);
        Picasso.get().load(list.get(position).image).into(holder.imageView_instructions_step_items);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionsEquipmentViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView_instructions_step_items;
    TextView textview_instructions_step_item;
    public InstructionsEquipmentViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_instructions_step_items=itemView.findViewById(R.id.imageView_instructions_step_items);
        textview_instructions_step_item=itemView.findViewById(R.id.textview_instructions_step_item);


    }
}