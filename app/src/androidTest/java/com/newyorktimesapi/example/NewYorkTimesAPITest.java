package com.newyorktimesapi.example;

import com.newyorktimesapi.example.Utils.Utility;
import com.newyorktimesapi.example.network.Retrofit.RFInterface;
import com.newyorktimesapi.example.network.Retrofit.ResponseModels.RmData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ayyazkhan on 23/11/18.
 */

public class NewYorkTimesAPITest {

    private RFInterface rfInterface = null;

    @Before
    public void setUp() throws Exception {

        rfInterface = Utility.getRetrofitInterface(MainActivity.service);

    }


    @Test
    public void getResult() throws IOException {

        Call<RmData> rmDataCall = rfInterface.getDetails(MainActivity.apikey);
        Response<RmData> rmDataResponse = rmDataCall.execute();

        assertEquals(200, rmDataResponse.code());
        assertEquals(true, rmDataResponse.isSuccessful());

    }

    @After
    public void tearDown() throws Exception {
        rfInterface = null;
    }

}
