package com.rawr.dropshop.presenters;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.rawr.dropshop.R;
import com.rawr.dropshop.interactors.MenuInteractorImpl;
import com.rawr.dropshop.interfaces.MenuInteractor;
import com.rawr.dropshop.interfaces.MenuPresenter;
import com.rawr.dropshop.views.MainActivity;
import com.rawr.dropshop.views.fragments.AcercaDeFragment;
import com.rawr.dropshop.views.fragments.MainFragment;
import com.rawr.dropshop.views.fragments.TotalCategoriasFragment;
import com.rawr.dropshop.views.fragments.TotalPedidosFragment;
import com.rawr.dropshop.views.fragments.TotalVentasFragment;

/**
 * Created by Ricardo Rodriguez on 11/30/2016.
 */
public class MenuPresenterImpl implements MenuPresenter {

    private MainActivity view;
    private MenuInteractor interactor;

    private static final String MAIN_ACTIVITY_FRAGMENT_NAME = "MainFragment";

    public MenuPresenterImpl(MainActivity view) {
        this.view = view;
        interactor = new MenuInteractorImpl();
    }

    /**
     * Metodo que hace el Switch entre las opciones del menú
     */
    @Override
    public void switchOtherSectionPresenter(final NavigationView navview, final DrawerLayout drawerLayout) {
       /*Listener de menu lateral*/
        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                /*AQUI VAN LAS OPCIONES DEL MENU*/
                switch (item.getItemId()) {
                    case R.id.menu_todos:
                        fragment = new MainFragment();
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
                    case R.id.menu_categorias:
                        fragment = new TotalCategoriasFragment();
                        fragmentTransaction = true;
                        break;
                }

                if (fragmentTransaction) {
                    view.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("MainFragment").commit();


                    int size = navview.getMenu().size();
                    for (int i = 0; i < size; i++) {
                        navview.getMenu().getItem(i).setChecked(false);
                    }

                    item.setChecked(true);
                    /*Validar para que con home mande el titulo de la app*/
                    setTitleBar(fragment, view, item);


                    drawerLayout.closeDrawers();
                }
                return fragmentTransaction;

            }


        });
    }

    /**
     * Metodo para colocar el titulo correcto en la action bar
     */
    private void setTitleBar(Fragment mFragment, MainActivity mView, MenuItem mItem) {

        if (MAIN_ACTIVITY_FRAGMENT_NAME.compareTo(mFragment.getClass().getSimpleName()) == 0) {
            mView.getSupportActionBar().setTitle(R.string.app_name);
        }else{
            view.getSupportActionBar().setTitle(mItem.getTitle());
        }
    }
}
