package com.rawr.dropshop;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.rawr.dropshop.adapters.ViewPagerAdapter;
import com.rawr.dropshop.fragments.Inventarios;
import com.rawr.dropshop.fragments.Pedidos;
import com.rawr.dropshop.fragments.Ventas;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbarMain;
    TabLayout tabLayoutMain;
    ViewPager viewPagerMain;
    ViewPagerAdapter viewPagerAdapter;

    FloatingActionButton fabPlus, fabOrder, fabStock, fabSales;
    TextView tvOrder, tvStock, tvSales;

    Animation fabOpen, fabClose, fabClockWise, fabAntiClockWise;
    boolean isOpen= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniGUImain();
        llamarListenersMain();
    }

    public void iniGUImain(){
        fabPlus = (FloatingActionButton)findViewById(R.id.fab_plus);
        fabOrder = (FloatingActionButton)findViewById(R.id.fab_orden);
        fabStock = (FloatingActionButton)findViewById(R.id.fab_stock);
        fabSales = (FloatingActionButton)findViewById(R.id.fab_ventas);
        tvOrder = (TextView)findViewById(R.id.fab_orden_text);
        tvStock = (TextView)findViewById(R.id.fab_in_stock_text);
        tvSales = (TextView)findViewById(R.id.fab_ventas_text);
        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabClockWise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        fabAntiClockWise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);
        toolbarMain = (Toolbar)findViewById(R.id.toolBar_main);
        setSupportActionBar(toolbarMain);
        tabLayoutMain = (TabLayout)findViewById(R.id.tabLayout_main);
        viewPagerMain = (ViewPager)findViewById(R.id.view_pager_main);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new Pedidos(), getResources().getString(R.string.pedidos));
        viewPagerAdapter.addFragments(new Inventarios(), getResources().getString(R.string.inventarios));
        viewPagerAdapter.addFragments(new Ventas(), getResources().getString(R.string.ventas));
        viewPagerMain.setAdapter(viewPagerAdapter);
        tabLayoutMain.setupWithViewPager(viewPagerMain);

    }

    public void llamarListenersMain(){
        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    fabSales.startAnimation(fabClose);
                    fabSales.setClickable(false);
                    tvSales.startAnimation(fabClose);
                    tvSales.setVisibility(View.GONE);
                    fabStock.startAnimation(fabClose);
                    fabStock.setClickable(false);
                    tvStock.startAnimation(fabClose);
                    tvStock.setVisibility(View.GONE);
                    fabOrder.startAnimation(fabClose);
                    fabOrder.setClickable(false);
                    tvOrder.startAnimation(fabClose);
                    tvOrder.setVisibility(View.GONE);
                    fabPlus.startAnimation(fabAntiClockWise);
                    isOpen = false;
                } else {
                    fabSales.startAnimation(fabOpen);
                    fabSales.setClickable(true);
                    tvSales.startAnimation(fabOpen);
                    fabStock.startAnimation(fabOpen);
                    fabStock.setClickable(true);
                    tvStock.startAnimation(fabOpen);
                    fabOrder.startAnimation(fabOpen);
                    fabOrder.setClickable(true);
                    tvOrder.startAnimation(fabOpen);
                    fabPlus.startAnimation(fabClockWise);
                    isOpen = true;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; /** true -> el menú ya está visible */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.acercaDe) {
            lanzarAcercaDe(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void lanzarAcercaDe(View view){
        Intent acercaDe = new Intent(this, AcercaDe.class);
        startActivity(acercaDe);
    }
}
