package michael.com.meettheteam.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.meettheteam.MeetTheTeam;
import michael.com.meettheteam.R;
import michael.com.meettheteam.model.Contacts;
import michael.com.meettheteam.model.Response;
import michael.com.meettheteam.network.Service;
import michael.com.meettheteam.ui.adapter.TeamContactsAdapter;
import michael.com.meettheteam.ui.fragment.TeamListFragment;
import michael.com.meettheteam.ui.presenter.TeamContract;
import michael.com.meettheteam.ui.presenter.TeamPresenter;
import michael.com.meettheteam.util.ConnectionManager;

public class MainActivity extends AppCompatActivity implements TeamContract.View {

    MeetTheTeam mTeamApp;
    @BindView(R.id.list) RecyclerView mRecyclerView;
    @BindView(R.id.donut_progress) DonutProgress progressBar;
    @Inject Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MeetTheTeam) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        setRecyclerView();

        TeamPresenter mPresenter = new TeamPresenter(service, this);
        mPresenter.getTeamContacts(ConnectionManager.isConnected(getApplicationContext()));

//        initFragment(TeamListFragment.newInstance());
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoadingFailed(String error) {
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showContacts(Response response) {

        TeamContactsAdapter mAdapter = new TeamContactsAdapter(response.getTeamContacts(),
                new TeamContactsAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(Contacts Item) {

                    }
                });
        mRecyclerView.setAdapter(mAdapter);

    }


    private void initFragment(Fragment detailFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, detailFragment, "TAG");
        transaction.addToBackStack("TAG");
        transaction.commit();
    }
}
