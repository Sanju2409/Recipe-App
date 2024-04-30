package com.example.reciepemad1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reciepemad1.Adapter.RandomReciepeAdapter;
import com.example.reciepemad1.Listeners.RandomRecipeResponseListener;
import com.example.reciepemad1.Models.RandomReciepeApiResponse;

public class MainActivity extends AppCompatActivity {
ProgressDialog dailog;
RequestManager manager;
RandomReciepeAdapter randomReciepeAdapter;
RecyclerView recyclerView;

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
        manager=new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);
        dailog.show();
    }
    private  final RandomRecipeResponseListener randomRecipeResponseListener=new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomReciepeApiResponse response, String message) {
            dailog.dismiss();
            recyclerView=findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            randomReciepeAdapter=new RandomReciepeAdapter(MainActivity.this,response.recipes);
            recyclerView.setAdapter(randomReciepeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}