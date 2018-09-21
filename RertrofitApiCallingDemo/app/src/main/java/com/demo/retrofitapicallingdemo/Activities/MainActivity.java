package com.demo.retrofitapicallingdemo.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.demo.retrofitapicallingdemo.Adapter.CustomeAdapter;
import com.demo.retrofitapicallingdemo.Interfaces.ApiInterface;
import com.demo.retrofitapicallingdemo.Model.ModelClass;
import com.demo.retrofitapicallingdemo.R;
import com.demo.retrofitapicallingdemo.Utils.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<ModelClass> modelClassArrayList;
    ModelClass modelClass;
    RecyclerView recyclerView;
    CustomeAdapter customeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modelClassArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        fetchServerData();

    }

    public void fetchServerData(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<ModelClass>> call = apiService.getModelClass();
        call.enqueue(new Callback<List<ModelClass>>(){

            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                List<ModelClass> movies = response.body();
                Log.d("MainActivity", "Number of movies received: " + movies.size());

                for (int i = 0; i < movies.size(); i++){
                    modelClass = new ModelClass();

                    modelClass.setName(movies.get(i).getName());
                    Log.d("MainActivity", "Number of movies received: " + movies.get(i).getName());
                    modelClass.setImageurl(movies.get(i).getImageurl());
                    Log.d("MainActivity", "Number of IMAGES received: " + movies.get(i).getName());
                    modelClassArrayList.add(modelClass);
                }

                customeAdapter = new CustomeAdapter(modelClassArrayList,modelClass,MainActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(customeAdapter);
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("MainActivity","Getting Failure Response "+t.getMessage());
            }
        });
    }
}
