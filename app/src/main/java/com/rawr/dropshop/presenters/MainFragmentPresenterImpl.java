package com.rawr.dropshop.presenters;

import com.rawr.dropshop.interactors.MainFragmentInteractorImpl;
import com.rawr.dropshop.interfaces.MainFragmentInteractor;
import com.rawr.dropshop.interfaces.MainFragmentPresenter;
import com.rawr.dropshop.interfaces.MainFragmentView;

/**
 * Created by Ricardo Rodriguez on 12/1/2016.
 */

public class MainFragmentPresenterImpl implements MainFragmentPresenter {

    private MainFragmentView fragmentView;

    private MainFragmentInteractor fragmentInteractor;

    public MainFragmentPresenterImpl(MainFragmentView fragmentView) {
        this.fragmentView = fragmentView;

        fragmentInteractor = new MainFragmentInteractorImpl();
    }

    @Override
    public void actionPlusButtonPresenter(boolean isOpen) {

        if (isOpen){
            fragmentView.hideFloatingButtons();
        }else {
            fragmentView.showFloatingButtons();
        }

    }
}
