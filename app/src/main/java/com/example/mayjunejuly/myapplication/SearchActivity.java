package com.example.mayjunejuly.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private List<Product> results = new ArrayList<>();
    private AdapterProduct viewAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Inventory Checking");

        viewAdapter = new AdapterProduct(results,SearchActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        loadData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        RegisterAPI apiService = ApiClient.getClient().create(RegisterAPI.class);
        Call<Value> call = apiService.view();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                Value listProductModel = response.body();
                if(listProductModel.getValue()==1){
                    List<Product> listProduct = listProductModel.getListProduct();
                    AdapterProduct productAdapter = new AdapterProduct(listProduct,SearchActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(productAdapter);
                }else{
                    Toast.makeText(SearchActivity.this, listProductModel.getValue(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.search_main, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Search..");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        RegisterAPI apiService = ApiClient.getClient().create(RegisterAPI.class);
        Call<Value> call = apiService.search(newText);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                Value listProductModel = response.body();
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                if(listProductModel.getValue()==1){
                    List<Product> listProduct  = listProductModel.getListProduct();
                    viewAdapter = new AdapterProduct(listProduct,SearchActivity.this);
                    recyclerView.setAdapter(viewAdapter);
                }
            }
            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
        return true;
    }
}
