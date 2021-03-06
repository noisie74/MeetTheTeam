package michael.com.meettheteam.ui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.meettheteam.MeetTheTeam;
import michael.com.meettheteam.R;
import michael.com.meettheteam.model.Contacts;
import michael.com.meettheteam.network.Service;
import michael.com.meettheteam.ui.adapter.TeamContactsAdapter;
import michael.com.meettheteam.ui.fragment.DetailFragment;
import michael.com.meettheteam.ui.presenter.TeamContract;
import michael.com.meettheteam.ui.presenter.TeamPresenter;
import michael.com.meettheteam.util.ConnectionManager;

public class MainActivity extends AppCompatActivity implements TeamContract.View {

    @BindView(R.id.list) RecyclerView mRecyclerView;
    @BindView(R.id.progress) DonutProgress progressBar;
    @Inject Service service;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ((MeetTheTeam) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        setRecyclerView();

        TeamPresenter mPresenter = new TeamPresenter(service, this);

        if (ConnectionManager.isConnected(getApplicationContext())) {
            mPresenter.getTeamContacts();
        } else {
            Toast.makeText(getApplicationContext(), "No Network Connection!", Toast.LENGTH_LONG).show();
        }

    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        boolean a = false;
                        if (a) {
                            ObjectAnimator anim = ObjectAnimator.ofInt(progressBar, "progress", 0, 10);
                            anim.setInterpolator(new DecelerateInterpolator());
                            anim.setDuration(500);
                            anim.start();
                        } else {
                            AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.progerss_anim);
                            set.setInterpolator(new DecelerateInterpolator());
                            set.setTarget(progressBar);
                            set.start();
                        }
                    }
                });
            }
        }, 0, 2000);
    }


    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoadingFailed(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showContacts(List<Contacts> contactsList) {

        TeamContactsAdapter mAdapter = new TeamContactsAdapter(contactsList,
                (itemView, position) -> {
                    mRecyclerView.setVisibility(View.GONE);
                    setDetailsFragment(contactsList.get(position).getBio()
                            , contactsList.get(position).getFirstName(), contactsList.get(position).getLastName()
                            , contactsList.get(position).getTitle(), contactsList.get(position).getAvatar());
                });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setDetailsFragment(String bio, String firstName, String lastName, String title, String image) {
        DetailFragment fragment = new DetailFragment();
        fragment.setBio(bio);
        fragment.setFirstName(firstName);
        fragment.setLastName(lastName);
        fragment.setTitle(title);
        fragment.setImage(image);
        initFragment(fragment);
    }

    private void initFragment(Fragment detailFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, detailFragment, "TAG");
        transaction.addToBackStack("TAG");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
        }
    }
}
