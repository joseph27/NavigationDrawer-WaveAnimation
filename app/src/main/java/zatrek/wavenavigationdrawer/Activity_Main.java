/*
 * *
 *  * Created by Youssef Assad on 5/12/18 8:41 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 5/12/18 8:34 PM
 *
 */

package zatrek.wavenavigationdrawer;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class Activity_Main extends AppCompatActivity {

    private int AnimateNumber = 1 ;
    DrawerLayout mDrawer;
    NavigationView navigationView;
    RecyclerView recyclerView;
    RelativeLayout WaveContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);

        recyclerView = navigationView.findViewById(R.id.nav_drawer_recycler_view);
        WaveContainer = navigationView.findViewById(R.id.WaveContainer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter_menu adapter_menu =   new Adapter_menu(this, new Adapter_menu.ListenerOnMenuItemClick() {
            @Override
            public void Item(int Position) {
                mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawer.closeDrawer(GravityCompat.START);
            }
        });
        recyclerView.setAdapter(adapter_menu);

        mDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {


            }
            @Override
            public void onDrawerOpened(View drawerView) {
                StartAnimation();
            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        if (Build.VERSION.SDK_INT < 21) {
            AnimatedVectorDrawableCompat drawable =  AnimatedVectorDrawableCompat.create(this,R.drawable.animate_wave_1);
            recyclerView.setBackground(drawable);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void  StartAnimation(){


        AnimatedVectorDrawableCompat drawable = null;
        switch (AnimateNumber){
            case 1:
                drawable =  AnimatedVectorDrawableCompat.create(this,R.drawable.animate_wave_1);
                break;
            case 2:
                drawable =  AnimatedVectorDrawableCompat.create(this,R.drawable.animate_wave_2);
                break;
            case 3:
                drawable =  AnimatedVectorDrawableCompat.create(this,R.drawable.animate_wave_3);
                break;
            case 4:
                drawable =  AnimatedVectorDrawableCompat.create(this,R.drawable.animate_wave_4);
                break;
            case 5:
                drawable =  AnimatedVectorDrawableCompat.create(this,R.drawable.animate_wave_5);
                AnimateNumber = 0;
                break;
            default:
                drawable =  AnimatedVectorDrawableCompat.create(this,R.drawable.animate_wave_1);
        }


        AnimateNumber ++ ;
        WaveContainer.setBackground(drawable);
        assert drawable != null;
        drawable.start();
    }
}
