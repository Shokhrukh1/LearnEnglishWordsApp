package ru.englishcat24.ui.activities.ratingPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ru.englishcat24.R;
import ru.englishcat24.core.BaseActivity;
import ru.englishcat24.model.Rating;
import ru.englishcat24.ui.activities.ratingPage.adapters.RatingAdapter;

public class RatingActivity extends BaseActivity implements RatingView {
    @Inject
    RatingPresenter presenter;
    @BindView(R.id.rvRatings)
    RecyclerView rvRatings;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setToolbarTitle(R.string.best_players);
        showHomeArrow();
        presenter.getRating();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_rating;
    }

    @Override
    public void showRating(List<Rating> ratings) {
        rvRatings.setLayoutManager(new LinearLayoutManager(this));
        rvRatings.setAdapter(new RatingAdapter(this, ratings));
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
