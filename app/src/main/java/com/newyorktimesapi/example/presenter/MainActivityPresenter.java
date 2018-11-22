package com.newyorktimesapi.example.presenter;

import com.newyorktimesapi.example.View.IMainActivityView;
import com.newyorktimesapi.example.model.Result;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ayyazkhan on 22/11/18.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private IMainActivityView mainActivityView;
    private ArrayList<Result> results;

    public MainActivityPresenter(IMainActivityView mainActivityView, ArrayList<Result> results) {
        this.mainActivityView = mainActivityView;
        this.results = results;

        showAllResult(this.results);
    }


    @Override
    public void showAllResult(ArrayList<Result> results) {
        mainActivityView.showAllResult(results);
    }

    public void filterOnSearch(String query) {
        if (query.equals("")) {
            showAllResult(this.results);
        } else {
            ArrayList<Result> tmpResult = new ArrayList<Result>();
            for (Result result : results) {
                if (result.getTitle().toLowerCase(Locale.getDefault()).contains(query.toLowerCase(Locale.getDefault()))) {
                    tmpResult.add(result);
                }
            }

            mainActivityView.showAllResult(tmpResult);

        }

    }
}
