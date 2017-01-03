package com.rawr.dropshop.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.rawr.dropshop.R;
import com.rawr.dropshop.adapters.ViewPagerAdapter;
import com.rawr.dropshop.fragments.Inventarios;
import com.rawr.dropshop.fragments.Pedidos;
import com.rawr.dropshop.fragments.Ventas;
import com.rawr.dropshop.interfaces.MainFragmentPresenter;
import com.rawr.dropshop.interfaces.MainFragmentView;
import com.rawr.dropshop.presenters.MainFragmentPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements MainFragmentView, View.OnClickListener {
    @BindView(R.id.tabLayout_main)
    TabLayout tabLayoutMain;
    @BindView(R.id.view_pager_main)
    ViewPager viewPagerMain;
    @BindView(R.id.fab_orden)
    FloatingActionButton fabOrden;
    @BindView(R.id.fab_stock)
    FloatingActionButton fabStock;
    @BindView(R.id.fab_ventas)
    FloatingActionButton fabVentas;
    @BindView(R.id.fab_plus)
    FloatingActionButton fabPlus;
    @BindView(R.id.fab_ventas_text)
    TextView fabVentasText;
    @BindView(R.id.fab_in_stock_text)
    TextView fabInStockText;
    @BindView(R.id.fab_orden_text)
    TextView fabOrdenText;

    Animation fabOpen, fabClose, fabClockWise, fabAntiClockWise;

    private MainFragmentPresenter mainFragmentPresenter;
    private boolean isOpen = false;
    ViewPagerAdapter viewPagerAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);
        ButterKnife.bind(this, view);
        iniGUIMainFragment();

        return view;
    }

    private void iniGUIMainFragment() {
        mainFragmentPresenter = new MainFragmentPresenterImpl(this);
        fabPlus.setOnClickListener(this);

        fabOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        fabClockWise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_clockwise);
        fabAntiClockWise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anticlockwise);

        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.addFragments(new Pedidos(), getResources().getString(R.string.pedidos));
        viewPagerAdapter.addFragments(new Inventarios(), getResources().getString(R.string.inventarios));
        viewPagerAdapter.addFragments(new Ventas(), getResources().getString(R.string.ventas));
        viewPagerMain.setAdapter(viewPagerAdapter);
        tabLayoutMain.setupWithViewPager(viewPagerMain);
    }

    @Override
    public void showFloatingButtons() {
        fabVentas.startAnimation(fabOpen);
        fabVentas.setClickable(true);
        fabVentasText.startAnimation(fabOpen);
        fabStock.startAnimation(fabOpen);
        fabStock.setClickable(true);
        fabInStockText.startAnimation(fabOpen);
        fabOrden.startAnimation(fabOpen);
        fabOrden.setClickable(true);
        fabOrdenText.startAnimation(fabOpen);
        fabPlus.startAnimation(fabClockWise);
        isOpen = true;
    }

    @Override
    public void hideFloatingButtons() {
        fabVentas.startAnimation(fabClose);
        fabVentas.setClickable(false);
        fabVentasText.startAnimation(fabClose);
        fabVentasText.setVisibility(View.GONE);
        fabStock.startAnimation(fabClose);
        fabStock.setClickable(false);
        fabInStockText.startAnimation(fabClose);
        fabInStockText.setVisibility(View.GONE);
        fabOrden.startAnimation(fabClose);
        fabOrden.setClickable(false);
        fabOrdenText.startAnimation(fabClose);
        fabOrdenText.setVisibility(View.GONE);
        fabPlus.startAnimation(fabAntiClockWise);
        isOpen = false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_plus:
                mainFragmentPresenter.actionPlusButtonPresenter(isOpen);
                break;

        }
    }



}
