package com.example.learnenglish.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Adapters.MeaningAdapter;
import com.example.learnenglish.Adapters.PhoneticsAdapter;
import com.example.learnenglish.Models.ApiResponse;
import com.example.learnenglish.OnFetchDataListener;
import com.example.learnenglish.R;
import com.example.learnenglish.RequestManager;

public class Dictionary_Activity extends AppCompatActivity {
    SearchView searchView;
    TextView textViewWord;
    RecyclerView recyclerView_phonetics,recyclerView_meanings;
    ProgressDialog loading;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        searchView = findViewById(R.id.word_search);
        textViewWord = findViewById(R.id.textview_word);
        recyclerView_phonetics = findViewById(R.id.recycler_phonetics);
        recyclerView_meanings = findViewById(R.id.recycler_meaning);



        loading = new ProgressDialog(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                loading.setTitle("Fetching Data For "+s);
                loading.show();
                RequestManager manager = new RequestManager(Dictionary_Activity.this);
                manager.getWordMeaning(listener,s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    public OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(ApiResponse apiResponse, String message) {
            if (apiResponse == null) {
                Toast.makeText(Dictionary_Activity.this, "No Data Found", Toast.LENGTH_SHORT).show();

            }
            showData(apiResponse);
            loading.dismiss();
        }

        @Override
        public void onError(String message) {

            loading.dismiss();
            Toast.makeText(Dictionary_Activity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private void showData(ApiResponse apiResponse) {
        textViewWord.setText("Word :"+apiResponse.getWord());

        recyclerView_phonetics.setHasFixedSize(true);
        recyclerView_phonetics.setLayoutManager(new GridLayoutManager(this,1));
        phoneticsAdapter = new PhoneticsAdapter(this,apiResponse.getPhonetics());
        recyclerView_phonetics.setAdapter(phoneticsAdapter);

        recyclerView_meanings.setHasFixedSize(true);
        recyclerView_meanings.setLayoutManager(new GridLayoutManager(this,1));
        meaningAdapter = new MeaningAdapter(this,apiResponse.getMeanings());
        recyclerView_meanings.setAdapter(meaningAdapter);
    }
}