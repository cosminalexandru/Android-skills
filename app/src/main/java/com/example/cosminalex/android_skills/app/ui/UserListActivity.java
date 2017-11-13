package com.example.cosminalex.android_skills.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cosminalex.android_skills.R;
import com.example.cosminalex.android_skills.commons.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Users. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link UserDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class UserListActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;
    private SimpleItemRecyclerViewAdapter adapter;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.user_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        RecyclerView recyclerView = findViewById(R.id.user_list);
        assert recyclerView != null;
        adapter=new SimpleItemRecyclerViewAdapter(this,mTwoPane);
        recyclerView.setAdapter(adapter);

        presenter = new MainPresenter(this,this);
        presenter.getUsers();
    }

    @Override
    public void loadUsers(List<User> items) {
        adapter.setmValues(items);
    }

    @Override
    public void showError() {
        Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final UserListActivity mParentActivity;
        private List<User> mValues=new ArrayList<>();
        private final boolean mTwoPane;

        public void setmValues(List<User> mValues) {
            this.mValues = mValues;
            notifyDataSetChanged();
        }

        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user= (User) view.getTag();

                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putParcelable(UserDetailFragment.ARG_ITEM_ID, user);
                    UserDetailFragment fragment = new UserDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.user_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, UserDetailActivity.class);
                    intent.putExtra(UserDetailFragment.ARG_ITEM_ID, user);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(UserListActivity parent,
                                      boolean twoPane) {
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mUserNameView.setText(mValues.get(position).getDisplayName());
            Glide.with(mParentActivity)
                    .asBitmap()
                    .load(mValues.get(position).getProfileImage())
                    .apply(new RequestOptions()
                    .centerCrop())
                    .into(holder.mImageView);
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView mImageView;
            final TextView mUserNameView;

            ViewHolder(View view) {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.dev_image);
                mUserNameView = (TextView) view.findViewById(R.id.dev_name);
            }
        }
    }
}
