package com.newyorktimesapi.example;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.newyorktimesapi.example.network.NetworkOperations;
import com.newyorktimesapi.example.network.WebServiceCalls;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public  final String service = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        WebServiceCalls.Data data = new WebServiceCalls(service).new Data();

        data.getDetails(MainActivity.this, "29c609617ba4444488768d34ba666018", new NetworkOperations(false) {
            @Override
            public void onSuccess(Bundle msg) {

                System.out.println(msg);
            }

            @Override
            public void onFailure(Bundle msg) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.textSearch));


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
