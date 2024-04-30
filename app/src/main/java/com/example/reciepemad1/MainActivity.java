package com.example.reciepemad1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reciepemad1.Adapter.RandomReciepeAdapter;
import com.example.reciepemad1.Listeners.RandomRecipeResponseListener;
import com.example.reciepemad1.Listeners.RecipeClickLisreners;
import com.example.reciepemad1.Models.RandomReciepeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ProgressDialog dailog;
RequestManager manager;
RandomReciepeAdapter randomReciepeAdapter;
RecyclerView recyclerView;
Spinner spinner;
List<String> tags= new ArrayList<>();
SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dailog=new ProgressDialog(this);
        dailog.setTitle("Loading");
        searchView=findViewById(R.id.searchView_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener,tags);
                dailog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        spinner=findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);
        manager=new RequestManager(this);
//        manager.getRandomRecipes(randomRecipeResponseListener);
//        dailog.show();
    }
    private  final RandomRecipeResponseListener randomRecipeResponseListener=new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomReciepeApiResponse response, String message) {
            dailog.dismiss();
            recyclerView=findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            randomReciepeAdapter=new RandomReciepeAdapter(MainActivity.this,response.recipes,recipeClickLisreners);
            recyclerView.setAdapter(randomReciepeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
  private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
tags.clear();
tags.add(adapterView.getSelectedItem().toString());
manager.getRandomRecipes(randomRecipeResponseListener,tags);
dailog.show();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
  };
  private final RecipeClickLisreners recipeClickLisreners=new RecipeClickLisreners() {
      @Override
      public void onRecipeClicked(String id) {
        startActivity(new Intent(MainActivity.this,RecipeDetailsActivity.class).putExtra("id",id));
      }
  };
}