package com.newyorktimesapi.example.View;

import com.newyorktimesapi.example.model.Result;

import java.util.ArrayList;

/**
 * Created by ayyazkhan on 22/11/18.
 */

public interface IResultView {

    void setSections(ArrayList<String> sections);

    void showAllResult(ArrayList<Result> results);
}
