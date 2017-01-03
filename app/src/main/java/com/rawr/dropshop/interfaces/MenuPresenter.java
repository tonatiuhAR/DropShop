package com.rawr.dropshop.interfaces;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

/**
 * Created by Ricardo Rodriguez on 11/30/2016.
 */

public interface MenuPresenter {
    void switchOtherSectionPresenter(NavigationView navigationView, DrawerLayout drawerLayout);
}