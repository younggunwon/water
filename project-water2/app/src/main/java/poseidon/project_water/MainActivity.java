package poseidon.project_water;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            TextView titleTextView = (TextView) f.get(toolbar);
            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    MainFragment mft = new MainFragment();
                    ft.replace(R.id.layout_main, mft);
                    ft.commit();
                    fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            });
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            if(fm.findFragmentByTag("main") != fm.findFragmentById(R.id.layout_main)) {
                MainFragment mft = new MainFragment();
                ft.replace(R.id.layout_main, mft, "main");
                ft.commit();
                return;
            } else {
                super.onBackPressed();
            }
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
        Fragment fragment = new MainFragment();
        switch(item.getItemId()) {

            case R.id.action_info:
                fragment = new InfoFragment();
                break;
            case R.id.action_stats:
                fragment = new StatsFragment();
                break;
            case R.id.action_alarm:
                fragment = new AlarmFragment();
                break;
            default:
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = new MainFragment();
        if (id == R.id.nav_ccup) {
            CCupDialog dialog = new CCupDialog(this);
            dialog.show();
        } else if (id == R.id.nav_alarm) {
            fragment = new AlarmFragment();
        } else if (id == R.id.nav_stats) {
            fragment = new StatsFragment();
        } else if (id == R.id.nav_info) {
            fragment = new InfoFragment();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_challenge) {
            fragment = new ChallengeFragment();
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void init() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MainFragment mft = new MainFragment();
        ft.add(R.id.layout_main, mft, "main");
        if(true) {
            Fragment fragment = new InfoFragment();
            ft.replace(R.id.layout_main,fragment);
        }
        ft.commit();
    }

    class CCupDialog extends Dialog {

        public CCupDialog(@NonNull Context context) {
            super(context);
            setContentView(R.layout.dialog_ccup);
        }
    }
}
