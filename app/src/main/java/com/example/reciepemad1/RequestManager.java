package com.example.reciepemad1;

import android.content.Context;

import com.example.reciepemad1.Listeners.RandomRecipeResponseListener;
import com.example.reciepemad1.Models.RandomReciepeApiResponse;

import java.lang.ref.Cleaner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

//to manage api calls
public class RequestManager {
    //create objects for context and retrofit
    Context context;


    Retrofit retrofit= new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //creating constructor
    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener){
      CallRandomRecipes callRandomRecipes=retrofit.create(CallRandomRecipes.class);
      Call<RandomReciepeApiResponse> call=callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"10");
      call.enqueue(new Callback<RandomReciepeApiResponse>() {
          @Override
          public void onResponse(Call<RandomReciepeApiResponse> call, Response<RandomReciepeApiResponse> response) {
              if(!response.isSuccessful()){
                  listener.didError(response.message());
                  return;
              }
              listener.didFetch(response.body(),response.message());

          }

          @Override
          public void onFailure(Call<RandomReciepeApiResponse> call, Throwable throwable) {
                listener.didError(throwable.getMessage());
          }
      });
    }
    private interface CallRandomRecipes{
        @GET("recipes/random")
            Call<RandomReciepeApiResponse> callRandomRecipe(
                    @Query("apiKey") String apiKey,
                   @Query("number")String number
            );
    }
}
