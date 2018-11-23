package com.newyorktimesapi.example.presenter;

import com.newyorktimesapi.example.model.Result;

import java.util.ArrayList;

/**
 * Created by ayyazkhan on 22/11/18.
 */

public interface IResultPresenter {

    void setSections(ArrayList<String> sections);

    void showAllResult(ArrayList<Result> results);

    void filterOnSearch(String query);

    void updateFilter(String orderby, ArrayList<String> sections);

    void updateResultbyFilter(ArrayList<Result> results);

}
