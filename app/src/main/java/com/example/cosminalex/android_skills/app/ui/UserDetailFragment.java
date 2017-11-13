package com.example.cosminalex.android_skills.app.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cosminalex.android_skills.R;
import com.example.cosminalex.android_skills.commons.models.User;

/**
 * A fragment representing a single User detail screen.
 * This fragment is either contained in a {@link UserListActivity}
 * in two-pane mode (on tablets) or a {@link UserDetailActivity}
 * on handsets.
 */
public class UserDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private User user;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            user = getArguments().getParcelable(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(user.getDisplayName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (user != null) {
            ((TextView) rootView.findViewById(R.id.dev_name)).setText(user.getDisplayName());
            ImageView imageView=rootView.findViewById(R.id.dev_image);
            Glide.with(container.getContext())
                    .asBitmap()
                    .load(user.getProfileImage())
                    .apply(new RequestOptions()
                    .centerCrop())
                    .into(imageView);

            ((TextView)rootView.findViewById(R.id.gold_badge_number)).setText(String.valueOf(user.getBadgeCounts().getGold()));
            ((TextView)rootView.findViewById(R.id.silver_badge_number)).setText(String.valueOf(user.getBadgeCounts().getSilver()));
            ((TextView)rootView.findViewById(R.id.bronze_badge_number)).setText(String.valueOf(user.getBadgeCounts().getBronze()));
        }

        return rootView;
    }
}
