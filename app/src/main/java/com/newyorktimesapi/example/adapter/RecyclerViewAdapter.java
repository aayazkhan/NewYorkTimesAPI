package com.newyorktimesapi.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newyorktimesapi.example.R;
import com.newyorktimesapi.example.model.Result;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ayyazkhan on 22/11/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Result> results;

    public RecyclerViewAdapter(Context context, ArrayList<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_row, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(itemView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Result result = results.get(position);

        holder.setTitle(result.getTitle());
        holder.setAuther(result.getSource());
        holder.setDate(result.getPublishedDate());


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        private ImageView imageView;

        private TextView textTitle, textAuther, textDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textTitle = (TextView) itemView.findViewById(R.id.textTitle);
            textAuther = (TextView) itemView.findViewById(R.id.textAuther);
            textDate = (TextView) itemView.findViewById(R.id.textDate);

        }

        public View getItemView() {
            return itemView;
        }

        public void setImage(final Context context, final String imageURL) {
            Picasso.with(context).load(imageURL).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(context).load(imageURL).into(imageView);
                }
            });
        }

        public void setTitle(String title) {
            textTitle.setText(title);
        }

        public void setAuther(String auther) {
            textAuther.setText(auther);
        }

        public void setDate(String date) {
            textDate.setText(date);
        }

    }
}
