package ru.englishcat24.ui.activities.homePage;

import ru.englishcat24.core.BasePresenterImpl;

import javax.inject.Inject;

/**
 * Created by crish on 1/16/18.
 */

public class HomePresenterImpl extends BasePresenterImpl<HomeView> implements HomePresenter {
    @Inject
    public HomePresenterImpl(HomeView view) {
        super(view);
    }
}
