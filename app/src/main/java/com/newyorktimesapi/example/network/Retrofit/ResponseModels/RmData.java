
package com.newyorktimesapi.example.network.Retrofit.ResponseModels;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;
import com.newyorktimesapi.example.model.Result;

public class RmData implements Serializable{

    @SerializedName("copyright")
    private String mCopyright;
    @SerializedName("num_results")
    private Long mNumResults;
    @SerializedName("results")
    private ArrayList<Result> mResults;
    @SerializedName("status")
    private String mStatus;

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

    public Long getNumResults() {
        return mNumResults;
    }

    public void setNumResults(Long numResults) {
        mNumResults = numResults;
    }

    public ArrayList<Result> getResults() {
        return mResults;
    }

    public void setResults(ArrayList<Result> results) {
        mResults = results;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
