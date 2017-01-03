package com.rawr.dropshop.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.rawr.dropshop.R;
import com.rawr.dropshop.interfaces.MainPresenter;
import com.rawr.dropshop.interfaces.MainView;
import com.rawr.dropshop.interfaces.MenuPresenter;
import com.rawr.dropshop.presenters.MainPresenterImpl;
import com.rawr.dropshop.presenters.MenuPresenterImpl;
import com.rawr.dropshop.views.fragments.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.toolBar_main)
    Toolbar toolBarMain;
    @BindView(R.id.content_frame)
    FrameLayout contentFrame;
    @BindView(R.id.navview)
    NavigationView navview;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    MenuPresenter menuPresenter;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iniGUImain();

        /*Esta implementacion es para generar funciones con el menu*/
       menuPresenter = new MenuPresenterImpl(this);
        /*Damos funcion a opciones de menu*/
        menuPresenter.switchOtherSectionPresenter(navview, drawerLayout);

        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.startFirstFragmentPresenter();


    }

    /**
     * METODO PARA INICIALIZAR LOS ELEMENTOS DE LA GUI
     */
    private void iniGUImain() {

        setSupportActionBar(toolBarMain);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Metodo para dar funcion al boton 'menu-options'
     * Metodo para dar funcion al boton 'menu-options'
     *
     * @param item
     * @return
     */
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

    /**
     * Metodo para conservar 'ultimo fragment',
     * al presionar el boton 'back' regresa al fragment anterior
     */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            Log.v("aquii", "" + getFragmentManager().getClass().getSimpleName());

            getSupportActionBar().setTitle(getFragmentManager().getClass().getSimpleName());
        } else {
            super.onBackPressed();

        }
    }

    /**
     * Metodo para cargar por primera vez el fragment principal
     */
    @Override
    public void startFirstFragmentView() {
        /*llenar fragment main*/
        Fragment fragmentMain = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentMain).commit();
    }

}

