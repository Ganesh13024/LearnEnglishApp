package com.example.learnenglish;

import android.content.Context;
import android.widget.Toast;

import com.example.learnenglish.Models.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getWordMeaning(OnFetchDataListener listener,String word){
        CallDictionary callDictionary = retrofit.create(CallDictionary.class);
        Call<List<ApiResponse>> call = callDictionary.callMeanings(word);

        call.enqueue(new Callback<List<ApiResponse>>() {
            @Override
            public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {
                try{
                    listener.onFetchData(response.body().get(0),response.message());
                }
                catch (Exception e)
                {
                    Toast.makeText(context, "Nothing Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ApiResponse>> call, Throwable t) {

                listener.onError("Request Failed");
            }
        });
    }

    public RequestManager(Context context) {
        this.context = context;
    }

    public interface CallDictionary{
        @GET("entries/en/{word}")
        Call<List<ApiResponse>> callMeanings(
                @Path("word") String word
        );
    }
}


