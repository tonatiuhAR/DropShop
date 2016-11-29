package com.rawr.dropshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import layout.AcercaDeFragment;
import layout.TotalPedidosFragment;
import layout.TotalVentasFragment;
import layout.MainActivityFragment;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolBar_main)
    Toolbar toolBarMain;
    @BindView(R.id.navview)
    NavigationView navview;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iniGUImain();
        llamarListenersMain();
    }

    public void iniGUImain() {

        /*llenar fragment main*/
        Fragment fragmentMain = new MainActivityFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentMain).commit();

        /**/

        setSupportActionBar(toolBarMain);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void llamarListenersMain() {

        /*Listener de menu lateral*/
        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                /*AQUI VAN LAS OPCIONES DEL MENU*/
                switch (item.getItemId()) {
                    case R.id.menu_todos:
                        fragment = new MainActivityFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_ventas:
                        fragment = new TotalVentasFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_acerca_de:
                        fragment = new AcercaDeFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_pedidos:
                        fragment = new TotalPedidosFragment();
                        fragmentTransaction = true;
                        break;
                }

                if (fragmentTransaction) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("MainActivityFragment").commit();


                    int size = navview.getMenu().size();
                    for (int i = 0; i < size; i++) {
                        navview.getMenu().getItem(i).setChecked(false);
                    }

                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());

                    drawerLayout.closeDrawers();
                }
                return fragmentTransaction;

            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /*
        getMenuInflater().inflate(R.menu.menu_main, menu);*/
        return true; /** true -> el menú ya está visible */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            //...
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
