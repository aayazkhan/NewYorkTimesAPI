package com.newyorktimesapi.example.presenter;

import com.newyorktimesapi.example.View.IMainActivityView;
import com.newyorktimesapi.example.model.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by ayyazkhan on 22/11/18.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private IMainActivityView mainActivityView;
    private ArrayList<Result> results;

    private String orderby = "Oldest";

    public MainActivityPresenter(IMainActivityView mainActivityView, ArrayList<Result> results) {
        this.mainActivityView = mainActivityView;
        this.results = results;

        Collections.sort(results, Result.ResultOldestDateComparator);
        updateResultbySort(this.results);
        showAllResult(this.results);
    }


    @Override
    public void showAllResult(ArrayList<Result> results) {
        mainActivityView.showAllResult(results);
    }

    @Override
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

            updateResultbySort(tmpResult);
            showAllResult(tmpResult);
        }
    }

    public void updateFilter(String orderby, ArrayList<Result> results) {
        this.orderby = orderby;
        updateResultbySort(results);
        showAllResult(results);
    }

    private void updateResultbySort(ArrayList<Result> result) {
        if (orderby.equalsIgnoreCase("Newest")) {
            Collections.sort(results, Result.ResultNewestDateComparator);
        } else if (orderby.equalsIgnoreCase("oldest")) {
            Collections.sort(results, Result.ResultOldestDateComparator);
        }
    }


}
