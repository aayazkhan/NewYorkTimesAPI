package com.newyorktimesapi.example;

import com.newyorktimesapi.example.Utils.Utility;
import com.newyorktimesapi.example.View.IResultView;
import com.newyorktimesapi.example.network.Retrofit.RFInterface;
import com.newyorktimesapi.example.network.Retrofit.ResponseModels.RmData;
import com.newyorktimesapi.example.presenter.ResultPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ayyazkhan on 24/11/18.
 */

public class ResultPresenterTest {

    private RFInterface rfInterface = null;

    private ResultPresenter resultPresenterv;

    private IResultView iResultView;

    private Response<RmData> rmDataResponse = null;


    @Before
    public void setUp() throws Exception {
        rfInterface = Utility.getRetrofitInterface(MainActivity.service);

        Call<RmData> rmDataCall = rfInterface.getDetails(MainActivity.apikey);
        rmDataResponse = rmDataCall.execute();

        resultPresenterv= new ResultPresenter(iResultView, rmDataResponse.body().getResults());

    }

    @Test
    public void presenterTest() {

    }

    @After
    public void tearDown() throws Exception {

    }

}
