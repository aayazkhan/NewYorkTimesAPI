package com.newyorktimesapi.example.network.Retrofit;

import com.newyorktimesapi.example.network.Retrofit.ResponseModels.RmData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RFInterface {

    @GET("7.json")
    Call<RmData> getDetails(@Query("apikey") String apikey);


}