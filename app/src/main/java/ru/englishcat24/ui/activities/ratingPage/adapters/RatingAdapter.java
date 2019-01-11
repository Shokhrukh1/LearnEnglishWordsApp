package ru.englishcat24.ui.activities.ratingPage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import ru.englishcat24.R;
import ru.englishcat24.core.BaseAdapter;
import ru.englishcat24.core.BaseViewHolder;
import ru.englishcat24.model.Rating;
import ru.englishcat24.model.User;

/**
 * Created by crish on 1/31/18.
 */

public class RatingAdapter extends BaseAdapter<Rating, RatingAdapter.RatingViewHolder> {
    private Context context;

    public RatingAdapter(Context context, List<Rating> items) {
        super(items);
        this.context = context;
    }

    @Override
    public RatingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating, parent, false);

        return new RatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RatingViewHolder holder, int position) {
        int color = 0;
        Rating rating = items.get(position);

        holder.tvNumber.setText(position + 1 + "");
        holder.tvName.setText(rating.getUserName());
        holder.tvRating.setText((rating.getPoint() > 0 ? "+" : "") + rating.getPoint());

        switch (position) {
            case 0:
                color = context.getResources().getColor(R.color.colorDarkGreen);
                break;
            case 1:
            case 2:
                color = context.getResources().getColor(R.color.colorBlue);
                break;
            default:
                color = context.getResources().getColor(R.color.colorDarkGray);
        }

        holder.tvNumber.setTextColor(color);
        holder.tvName.setTextColor(color);
        holder.tvRating.setTextColor(color);
    }

    class RatingViewHolder extends BaseViewHolder {
        @BindView(R.id.tvNumber)
        TextView tvNumber;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvRating)
        TextView tvRating;

        public RatingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
