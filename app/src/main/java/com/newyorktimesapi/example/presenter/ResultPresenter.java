package com.newyorktimesapi.example.presenter;

import com.newyorktimesapi.example.View.IResultView;
import com.newyorktimesapi.example.model.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

/**
 * Created by ayyazkhan on 22/11/18.
 */

public class ResultPresenter implements IResultPresenter {

    private IResultView mainActivityView;
    private ArrayList<Result> results;

    private String orderby = "Oldest";
    private ArrayList<String> sections = null;

    public ResultPresenter(IResultView mainActivityView, ArrayList<Result> results) {
        this.mainActivityView = mainActivityView;
        this.results = results;

        updateSections();

        ArrayList<Result> tmpResults = new ArrayList<Result>();
        tmpResults.addAll(results);
        updateResultbyFilter(tmpResults);
        showAllResult(tmpResults);
    }

    private void updateSections() {
        sections = new ArrayList<String>();
        for (Result result : results) {
            sections.add(result.getSection());
        }

        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(sections);
        sections.clear();
        sections.addAll(hashSet);

        setSections(sections);
    }

    @Override
    public void setSections(ArrayList<String> sections) {
        mainActivityView.setSections(sections);
    }

    @Override
    public void showAllResult(ArrayList<Result> results) {
        mainActivityView.showAllResult(results);
    }

    @Override
    public void filterOnSearch(String query) {
        ArrayList<Result> tmpResults = new ArrayList<Result>();
        if (query.equals("")) {
            tmpResults.addAll(results);
        } else {
            for (Result result : results) {
                if (result.getTitle().toLowerCase(Locale.getDefault()).contains(query.toLowerCase(Locale.getDefault()))) {
                    tmpResults.add(result);
                }
            }
        }

        updateResultbyFilter(tmpResults);
        showAllResult(tmpResults);
    }

    @Override
    public void updateFilter(String orderby, ArrayList<String> sections) {
        this.orderby = orderby;
        this.sections = sections;

        ArrayList<Result> tmpResults = new ArrayList<Result>();
        tmpResults.addAll(results);

        updateResultbyFilter(tmpResults);
        showAllResult(tmpResults);
    }

    public void updateResultbyFilter(ArrayList<Result> results) {

        for (int i = 0; i < results.size(); i++) {

            boolean flag = true;
            for (int j = 0; j < sections.size(); j++) {
                if (results.get(i).getSection().equalsIgnoreCase(sections.get(j))) {
                    flag = false;
                }
            }

            if (flag) {
                results.remove(i--);
            }
        }


        if (orderby.equalsIgnoreCase("Newest")) {
            Collections.sort(results, Result.ResultNewestDateComparator);
        } else if (orderby.equalsIgnoreCase("oldest")) {
            Collections.sort(results, Result.ResultOldestDateComparator);
        }
    }


}
