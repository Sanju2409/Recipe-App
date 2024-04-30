package com.example.reciepemad1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reciepemad1.Adapter.IngredientsAdapter;
import com.example.reciepemad1.Listeners.RecipeDetailsListener;
import com.example.reciepemad1.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

public class RecipeDetailsActivity extends AppCompatActivity {
int id;
TextView textview_meal_name,textview_meal_source,textview_meal_summary;
ImageView imageView_meal_image;
RecyclerView recycler_meal_ingredients;
RequestManager manager;
ProgressDialog dialog;
IngredientsAdapter ingredientsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViews();

        id=Integer.parseInt(getIntent().getStringExtra("id"));

        manager=new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener,id);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading details...");
        dialog.show();
    }

    private void findViews() {
        textview_meal_name=findViewById(R.id.textview_meal_name);
        textview_meal_source=findViewById(R.id.textview_meal_source);
        textview_meal_summary=findViewById(R.id.textview_meal_summary);
        imageView_meal_image=findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients=findViewById(R.id.recycler_meal_ingredients);
    }

    private  final RecipeDetailsListener recipeDetailsListener=new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textview_meal_name.setText(response.title);
            textview_meal_source.setText(response.sourceName);
            textview_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);

            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter=new IngredientsAdapter(RecipeDetailsActivity.this,response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}