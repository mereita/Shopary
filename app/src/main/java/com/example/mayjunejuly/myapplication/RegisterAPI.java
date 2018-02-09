package com.example.mayjunejuly.myapplication;

/**
 * Created by MAYJUNEJULY on 1/30/2018.
 */

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterAPI {
    @GET("viewAll.php")
    Call<Value> view();
    @FormUrlEncoded
    @POST("testInventory.php")
    Call<Value> search(@Field("search") String search);
}

