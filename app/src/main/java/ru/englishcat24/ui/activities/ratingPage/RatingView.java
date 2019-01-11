package ru.englishcat24.ui.activities.ratingPage;

import java.util.List;

import ru.englishcat24.core.BaseView;
import ru.englishcat24.model.Rating;

/**
 * Created by crish on 1/31/18.
 */

public interface RatingView extends BaseView {
    void showRating(List<Rating> ratings);
    void showProgressBar();
    void hideProgressBar();
}
