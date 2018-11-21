package com.newyorktimesapi.example.network;

import android.content.Context;
import android.os.Bundle;

import com.newyorktimesapi.example.Utils.Utility;
import com.newyorktimesapi.example.network.Retrofit.RFInterface;
import com.newyorktimesapi.example.network.Retrofit.ResponseModels.RmData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WebServiceCalls {

    private RFInterface rfInterface = null;

    public WebServiceCalls(String service) {
        rfInterface = Utility.getRetrofitInterface(service);
    }

    public class Data {

        public void getDetails(final Context activity, String apikey , final NetworkOperations nwCall) {
            nwCall.onStart(activity, "Downloading... ");

            rfInterface.getDetails(apikey).enqueue(new Callback<RmData>() {
                @Override
                public void onResponse(Call<RmData> call, Response<RmData> response) {
                    try {
                        nwCall.onComplete();

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", response.body());

                        nwCall.onSuccess(bundle);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }

                @Override
                public void onFailure(Call<RmData> call, Throwable t) {
                    nwCall.onComplete();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("message", "Something went wrong, please try again");
                    nwCall.onFailure(bundle);
                }
            });

        }


    }


}
