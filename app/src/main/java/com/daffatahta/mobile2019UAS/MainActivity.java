package com.daffatahta.mobile2019UAS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.daffatahta.mobile2019UAS.fragments.about.About;
import com.daffatahta.mobile2019UAS.fragments.alarm.Alarm;
import com.daffatahta.mobile2019UAS.fragments.currency.Currency;
import com.daffatahta.mobile2019UAS.fragments.temperature.Temperature;
import com.daffatahta.mobile2019UAS.fragments.timer.Timer;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
//
        //drawer = findViewById(R.id.drawer_layout);
//
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
        //        R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState();

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new About()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_alarm :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Alarm()).commit();
                break;
            case R.id.nav_timer :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Timer()).commit();
                break;
            case R.id.nav_currency :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Currency()).commit();
                break;
            case R.id.nav_temperature :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Temperature()).commit();
                break;
            case R.id.nav_about :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new About()).commit();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}
