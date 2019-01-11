package ru.englishcat24.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import ru.englishcat24.R;

/**
 * Created by Portable-Acer on 05.12.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Nullable
    @BindView(R.id.llContent)
    protected LinearLayout llContent;
    @Nullable
    @BindView(R.id.llProgressBar)
    protected LinearLayout llProgressBar;
    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @Nullable
    @BindView(R.id.tvTitle)
    protected TextView tvTitle;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        unbinder = ButterKnife.bind(this);

        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    protected abstract void init(@Nullable Bundle savedInstanceState);

    protected abstract int getLayout();

    public void clearFocus() {
        llContent.clearFocus();
    }

    protected void setToolbarTitle(@StringRes int resId) {
        toolbar.setTitle("");
        tvTitle.setText(resId);
        setSupportActionBar(toolbar);
    }

    protected void showHomeArrow() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void showProgressBar() {
        llProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        llProgressBar.setVisibility(View.GONE);
    }
}
