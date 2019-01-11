package ru.englishcat24.ui.activities.ratingPage;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.englishcat24.core.BasePresenterImpl;
import ru.englishcat24.model.Rating;

import static ru.englishcat24.ui.Constants.RATING_FIELD_NAME;
import static ru.englishcat24.ui.Constants.USERS_COLLECTION_NAME;

/**
 * Created by crish on 1/31/18.
 */

public class RatingPresenterImpl extends BasePresenterImpl<RatingView> implements RatingPresenter {
    private List<Rating> ratings;
    private boolean isDestroyed = false;

    @Inject
    public RatingPresenterImpl(RatingView view) {
        super(view);
        ratings = new ArrayList<>();
    }

    @Override
    public void getRating() {
        view.showProgressBar();

        FirebaseFirestore.getInstance()
                .collection(USERS_COLLECTION_NAME)
                .orderBy(RATING_FIELD_NAME, Query.Direction.DESCENDING)
                .limit(100)
                .get()
                .addOnSuccessListener(documentSnapshots -> {
                    if (!isDestroyed) {
                        for (DocumentSnapshot dc : documentSnapshots) {
                            ratings.add(new Rating(dc.getString("name"), dc.getLong("rating")));
                        }

                        view.showRating(ratings);
                        view.hideProgressBar();
                    }
                })
                .addOnFailureListener(e -> {
                });
    }

    @Override
    public void onDestroy() {
        isDestroyed = true;
        super.onDestroy();
    }
}
