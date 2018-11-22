package com.newyorktimesapi.example;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.newyorktimesapi.example.View.IMainActivityView;
import com.newyorktimesapi.example.adapter.RecyclerViewAdapter;
import com.newyorktimesapi.example.model.Result;
import com.newyorktimesapi.example.network.NetworkOperations;
import com.newyorktimesapi.example.network.WebServiceCalls;
import com.newyorktimesapi.example.presenter.MainActivityPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    public final String service = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerViewAdapter recyclerViewAdapter;

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));

        WebServiceCalls.Data data = new WebServiceCalls(service).new Data();

        data.getDetails(MainActivity.this, "29c609617ba4444488768d34ba666018", new NetworkOperations(true) {
            @Override
            public void onSuccess(Bundle msg) {

                //             System.out.println(msg);

                ArrayList<Result> results = (ArrayList<Result>) msg.getSerializable("data");

                presenter = new MainActivityPresenter(MainActivity.this, results);

            }

            @Override
            public void onFailure(Bundle msg) {

            }
        });

    }

    @Override
    public void showAllResult(ArrayList<Result> results) {
        recyclerViewAdapter = new RecyclerViewAdapter(this, results);
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.textSearch));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                presenter.filterOnSearch(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.textSearch) {
            MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    presenter.filterOnSearch("");
                    return true;
                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    return true;
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
