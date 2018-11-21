package com.newyorktimesapi.example.network.Retrofit;

import com.newyorktimesapi.example.network.Retrofit.ResponseModels.RmData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RFInterface {

    @GET("json")
    Call<RmData> getDetails();


}