package com.example.sarah.coursetool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class BaseNavigationActivity extends AppCompatActivity {

    private DrawerLayout navDrawerLayout;
    private MenuItem selectedMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_navigation_drawer);

        initNavigationDrawer();
        initActionToolbar();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        FrameLayout contentFrame = findViewById(R.id.content_frame);
        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(layoutResID, contentFrame);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Get a reference to the navigation drawer elements and configure a listener that will
     * navigate to the selected activity screen.
     */
    private void initNavigationDrawer(){
        // get the elements that make up the navigation menu
        navDrawerLayout = findViewById(R.id.nav_drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);

        // add a listener for when the user selects a navigation choice from the nav drawer
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectNavigationMenuItem(menuItem);
                return true;
            }
        });
    }

    /**
     * Navigates to the desired activity upon selecting a menu item
     *
     * @param menuItem - The selected menu item from the Navigation Drawer
     */
    private void selectNavigationMenuItem (MenuItem menuItem){
        // clear the previous selection if there was one
        if (selectedMenuItem != null){
            selectedMenuItem.setChecked(false);
        }

        // mark the chosen navigation menu item as selected in the UI
        menuItem.setChecked(true);
        selectedMenuItem = menuItem;

        Class nextActivity = null;

        switch(menuItem.getItemId()){
            case R.id.nav_main_menu:
                nextActivity = MainActivity.class;
                break;
            case R.id.nav_view_schedule:
                break;
            case R.id.nav_view_courses:
                break;
            case R.id.nav_add_drop_courses:
                break;
            case R.id.nav_logout:
                // perform logout and set nextActivity to the login screen
                break;
        }

        if (nextActivity != null){
            Intent myIntent = new Intent(this, nextActivity);
            startActivity(myIntent);
        }

        navDrawerLayout.closeDrawers();
    }

    /**
     * Create the action toolbar with the hamburger menu
     */
    private void initActionToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_hamburger_menu);
    }

}
