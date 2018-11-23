package com.newyorktimesapi.example;

import android.app.Dialog;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
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
import android.widget.Button;
import android.widget.Spinner;

import com.newyorktimesapi.example.Utils.PopMessage;
import com.newyorktimesapi.example.Utils.Utility;
import com.newyorktimesapi.example.View.IResultView;
import com.newyorktimesapi.example.adapter.RecyclerViewAdapter;
import com.newyorktimesapi.example.ccontrol.MultipleSelectionSpinner;
import com.newyorktimesapi.example.model.Result;
import com.newyorktimesapi.example.network.NetworkOperations;
import com.newyorktimesapi.example.network.WebServiceCalls;
import com.newyorktimesapi.example.presenter.ResultPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IResultView {

    public static final String service = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/";
    public static final String apikey = "29c609617ba4444488768d34ba666018";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private WebServiceCalls.Data data;

    private RecyclerViewAdapter recyclerViewAdapter;
    private ResultPresenter presenter;

    private ArrayList<String> sections = null;

    private Dialog filterDialog = null;
    private Spinner spinnerSortOrder = null;
    private MultipleSelectionSpinner multipleSelectionSpinner = null;


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

        data = new WebServiceCalls(service).new Data();

        downloadDataAndShow();
    }

    private void downloadDataAndShow() {
        data.getDetails(MainActivity.this, apikey, new NetworkOperations(true) {

            @Override
            public void onSuccess(Bundle msg) {
                ArrayList<Result> results = (ArrayList<Result>) msg.getSerializable("data");
                presenter = new ResultPresenter(MainActivity.this, results);
            }

            @Override
            public void onFailure(Bundle msg) {
                PopMessage.makelongtoast(MainActivity.this, msg.getString("message"));
            }
        });
    }

    @Override
    public void setSections(ArrayList<String> sections) {
        this.sections = sections;
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

        if (item.getItemId() == R.id.textFilterList) {
            //TODO
            if (filterDialog == null) {
                filterDialog = Utility.createDialog(MainActivity.this, R.layout.dialog_filter, false);
                spinnerSortOrder = (Spinner) filterDialog.findViewById(R.id.spinnerSortOrder);
                multipleSelectionSpinner = (MultipleSelectionSpinner) filterDialog.findViewById(R.id.multiSelectSpinnerSection);
                multipleSelectionSpinner.setItems(sections);
            }


            Button btnSubmit = (Button) filterDialog.findViewById(R.id.btnSubmit);

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String orderBy = spinnerSortOrder.getSelectedItem().toString();
                    ArrayList<String> sections = multipleSelectionSpinner.getSelectedItemsAsString();

                    if (sections.size() == 0) {
                        sections.addAll(MainActivity.this.sections);
                    }

                    presenter.updateFilter(orderBy, sections);

                    filterDialog.hide();
                }
            });

            filterDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
