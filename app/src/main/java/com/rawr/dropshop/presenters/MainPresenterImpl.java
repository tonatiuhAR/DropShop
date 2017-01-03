package com.rawr.dropshop.presenters;

import com.rawr.dropshop.interactors.MainInteractorImpl;
import com.rawr.dropshop.interfaces.MainInteractor;
import com.rawr.dropshop.interfaces.MainPresenter;
import com.rawr.dropshop.interfaces.MainView;

/**
 * Created by Ricardo Rodriguez on 11/30/2016.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
    }

    @Override
    public void startFirstFragmentPresenter() {
        if (mainView != null) {
            mainView.startFirstFragmentView();
        }

    }
}
