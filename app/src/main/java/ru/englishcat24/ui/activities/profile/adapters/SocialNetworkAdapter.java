package ru.englishcat24.ui.activities.profile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import ru.englishcat24.R;
import ru.englishcat24.core.BaseAdapter;
import ru.englishcat24.core.BaseViewHolder;

/**
 * Created by crish on 1/26/18.
 */

public class SocialNetworkAdapter extends BaseAdapter<String, SocialNetworkAdapter.SocialNetworkViewHolder> {
    public SocialNetworkAdapter(List<String> items) {
        super(items);
    }

    @Override
    public SocialNetworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SocialNetworkViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_social_network, parent, false));
    }

    @Override
    public void onBindViewHolder(SocialNetworkViewHolder holder, int position) {
        holder.tvSocialNetwork.setText(items.get(position));
    }

    class SocialNetworkViewHolder extends BaseViewHolder {
        @BindView(R.id.tvSocialNetwork)
        TextView tvSocialNetwork;

        public SocialNetworkViewHolder(View itemView) {
            super(itemView);
        }
    }
}
