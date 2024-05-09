package com.example.reciepemad1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reciepemad1.Models.Step;
import com.example.reciepemad1.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder>{
   Context context;
   List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_steps,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {
    holder.textview_instruction_step_number.setText(String.valueOf(list.get(position).number));
    holder.textview_instruction_step_title.setText(list.get(position).step);

    holder.recycler_instruction_ingredients.setHasFixedSize(true);
    holder.recycler_instruction_ingredients.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
    InstructionsIngredientAdapter instructionsIngredientAdapter=new InstructionsIngredientAdapter(context,list.get(position).ingredients);
    holder.recycler_instruction_ingredients.setAdapter(instructionsIngredientAdapter);

    holder.recycler_instruction_equipments.setHasFixedSize(true);
    holder.recycler_instruction_equipments.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
    InstructionsEquipmentAdapter instructionsEquipmentAdapter=new InstructionsEquipmentAdapter(context,list.get(position).equipment);
    holder.recycler_instruction_equipments.setAdapter(instructionsEquipmentAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionStepViewHolder extends RecyclerView.ViewHolder{
    TextView textview_instruction_step_number,textview_instruction_step_title;
    RecyclerView recycler_instruction_equipments,recycler_instruction_ingredients;
    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);
        textview_instruction_step_number=itemView.findViewById(R.id.textview_instruction_step_number);
       textview_instruction_step_title=itemView.findViewById(R.id.textview_instruction_step_title);
        recycler_instruction_equipments=itemView.findViewById(R.id.recycler_instruction_equipments);
        recycler_instruction_ingredients=itemView.findViewById(R.id.recycler_instruction_ingredients);


    }
}