package com.ics.admin;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ics.admin.Fragment.BatchFragment;
import com.ics.admin.Fragment.CommunityFragment;
import com.ics.admin.Fragment.EnquiryFragment;
import com.ics.admin.Fragment.FacultyFragment;
import com.ics.admin.Fragment.VideoLibraryFragment;

public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottom_nav_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        bottom_nav_view = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin");
        bottom_nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.navigation_fee:
                        fragment = new FacultyFragment();
                        break;
                    case R.id.navigation_comm:
                        fragment = new CommunityFragment();
                        break;
                    case R.id.navigation_video_libary:
                        fragment = new VideoLibraryFragment();
                        break;
                    case R.id.navigation_Batch:
                        fragment = new BatchFragment();
                        break;
                    case R.id.navigation_student_material:
                        fragment = new EnquiryFragment();
//                        loadFragment(fragment);
                        break;
                }
                return loadFragment(fragment);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle("Admin");
        loadFragment(new FacultyFragment());
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment!= null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container,fragment)
                    .commit();
       return true;
        }
        return false;
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
        getMenuInflater().inflate(R.menu.admin, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_batch) {
            // Handle the camera action
        } else if (id == R.id.nav_faculty) {

        } else if (id == R.id.nav_payment) {

        } else if (id == R.id.nav_enquiry) {

        } else if (id == R.id.nav_test) {

        } else if (id == R.id.nav_video) {

        } else if (id == R.id.nav_fee_structure) {

        } else if (id == R.id.nav_listing) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_community) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
