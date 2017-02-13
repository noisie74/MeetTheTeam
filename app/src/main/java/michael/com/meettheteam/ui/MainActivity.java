package michael.com.meettheteam.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.meettheteam.MeetTheTeam;
import michael.com.meettheteam.R;
import michael.com.meettheteam.model.Contacts;
import michael.com.meettheteam.network.Service;
import michael.com.meettheteam.ui.adapter.TeamContactsAdapter;
import michael.com.meettheteam.ui.presenter.TeamContract;
import michael.com.meettheteam.ui.presenter.TeamPresenter;
import michael.com.meettheteam.util.ConnectionManager;

public class MainActivity extends AppCompatActivity implements TeamContract.View {

    @BindView(R.id.list) RecyclerView mRecyclerView;
    @BindView(R.id.progress) ProgressBar progressBar;
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

    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showContacts(List<Contacts> contactsList) {

        TeamContactsAdapter mAdapter = new TeamContactsAdapter(contactsList);
        mRecyclerView.setAdapter(mAdapter);
    }

}
