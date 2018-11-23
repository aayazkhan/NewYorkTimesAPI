package com.newyorktimesapi.example;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = activityTestRule.getActivity();
    }

    @Test
    public void testRoolbar() {
        View view = mainActivity.findViewById(R.id.toolbar);
        Assert.assertNotNull(view);
    }

    @Test
    public void testRecyclerView() {
        View view = mainActivity.findViewById(R.id.recyclerView);
        Assert.assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity.finish();
    }
}
