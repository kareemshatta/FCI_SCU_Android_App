package com.example.kareem.fci_scu_project.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.kareem.fci_scu_project.Helpers.Constants;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.User;
import com.example.kareem.fci_scu_project.fragments.CoursesFragment;
import com.example.kareem.fci_scu_project.fragments.HomeFragment;
import com.example.kareem.fci_scu_project.fragments.NotificationFragment;
import com.example.kareem.fci_scu_project.fragments.ProfileFragment;

import static com.example.kareem.fci_scu_project.Helpers.Constants.PREF_KEY;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment coursesFragment;
    private NavigationView navigationView;
    public static String flag;
    public static int coursesBack = 0, teamsBack = 0;
    User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.home_nav_txt);
        user = Constants.USER_DATA;
        flag = getIntent().getStringExtra("flag");

        navigationView = findViewById(R.id.nav_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (coursesBack == 1) {
            replaceFragment(new CoursesFragment());
            coursesBack = 0;
//        } else if (teamsBack == 1 && flag.equals("student")) {
//            replaceFragment(new CreateTeamFragment());
//
//            teamsBack = 0;
//        } else if (teamsBack == 1 && !flag.equals("student")) {
//            replaceFragment(new TeamsFragment());
//
//            teamsBack = 0;
        } else {

            replaceFragment(new HomeFragment());
            navigationView.getMenu().getItem(0).setChecked(true);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            replaceFragment(new HomeFragment());

        } else {
            replaceFragment(new HomeFragment());
            getSupportActionBar().setTitle(R.string.home_nav_txt);
            navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        String packageName = "com.example.kareem.chatproject";
        String className = "com.example.kareem.chatproject.MainActivity";

        if (id == R.id.action_chat_btn) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(intent.CATEGORY_LAUNCHER);
            intent.setComponent(new ComponentName(packageName, className));
            try {
                startActivity(intent);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            getSupportActionBar().setTitle(R.string.home_nav_txt);
            replaceFragment(new HomeFragment());

        } else if (id == R.id.nav_courses) {

            getSupportActionBar().setTitle(R.string.Courses_textview_txt);
            replaceFragment(new CoursesFragment());


        } else if (id == R.id.nav_notify) {

            getSupportActionBar().setTitle(R.string.notification_nav_txt);
            replaceFragment(new NotificationFragment());

        } else if (id == R.id.nav_logout) {
            clearAllSavedSharedData(HomeActivity.this);
            getSupportActionBar().setTitle(R.string.logout_nav_txt);
            startActivity(new Intent(this, LoginActivity.class));
            finish();

        } else if (id == R.id.nav_profile) {

            getSupportActionBar().setTitle(R.string.profile_nav_txt);
            replaceFragment(new ProfileFragment());
       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    public static void clearAllSavedSharedData(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
        prefsEditor.clear();
        prefsEditor.apply();
    }
}
