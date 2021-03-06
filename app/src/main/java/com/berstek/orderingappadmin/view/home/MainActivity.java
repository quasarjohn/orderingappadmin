package com.berstek.orderingappadmin.view.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.berstek.orderingappadmin.R;
import com.berstek.orderingappadmin.firebase_da.MenuDA;
import com.berstek.orderingappadmin.model.Menu;
import com.berstek.orderingappadmin.model.MenuFactory;
import com.berstek.orderingappadmin.view.menus.AddMenuDialogFragment;
import com.berstek.orderingappadmin.view.menus.MenuFragmentViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener,
    MenuFragmentViewPager.OnAddMenuClickListener {

  private AddMenuDialogFragment addMenuDialogFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);


    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    getSupportFragmentManager().beginTransaction().
        replace(R.id.content_main, new HomeFragment()).commit();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }


  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      getSupportFragmentManager().beginTransaction().
          replace(R.id.content_main, new HomeFragment()).commit();
    } else if (id == R.id.nav_gallery) {
      getSupportFragmentManager().beginTransaction().
          replace(R.id.content_main, new MenuFragmentViewPager()).commit();
    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override
  public void onAddMenu(int position) {
    Bundle bundle = new Bundle();
    bundle.putInt("position", position);

    addMenuDialogFragment = new AddMenuDialogFragment();
    addMenuDialogFragment.setArguments(bundle);
    addMenuDialogFragment.show(getFragmentManager(), null);
  }
}
