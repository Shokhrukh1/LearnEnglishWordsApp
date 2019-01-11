package ru.englishcat24.ui.activities.about;

import ru.englishcat24.core.BasePresenterImpl;

import javax.inject.Inject;

/**
 * Created by crish on 1/18/18.
 */

public class AboutPresenterImpl extends BasePresenterImpl<AboutView> implements AboutPresenter {
    @Inject
    protected AboutPresenterImpl(AboutView view) {
        super(view);
    }
}
