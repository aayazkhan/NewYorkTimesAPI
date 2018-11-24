package com.newyorktimesapi.example;

import android.support.test.rule.ActivityTestRule;

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

import static junit.framework.Assert.assertNotNull;

/**
 * Created by ayyazkhan on 24/11/18.
 */

public class ResultPresenterTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;


    private RFInterface rfInterface = null;

    private ResultPresenter resultPresenterv;

    private Response<RmData> rmDataResponse = null;


    @Before
    public void setUp() throws Exception {

        mainActivity = activityTestRule.getActivity();

        rfInterface = Utility.getRetrofitInterface(MainActivity.service);

        Call<RmData> rmDataCall = rfInterface.getDetails(MainActivity.apikey);
        rmDataResponse = rmDataCall.execute();

        resultPresenterv= new ResultPresenter(mainActivity, rmDataResponse.body().getResults());

    }

    @Test
    public void presenterTest() {
        //TODO
    }

    @After
    public void tearDown() throws Exception {
        mainActivity.finish();
    }

}
