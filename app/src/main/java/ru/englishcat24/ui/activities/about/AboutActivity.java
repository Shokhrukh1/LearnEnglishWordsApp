package ru.englishcat24.ui.activities.about;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import ru.englishcat24.R;
import ru.englishcat24.core.BaseActivity;

import javax.inject.Inject;

public class AboutActivity extends BaseActivity implements AboutView {
    @Inject
    AboutPresenter presenter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setToolbarTitle(R.string.about);
        showHomeArrow();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }
}
