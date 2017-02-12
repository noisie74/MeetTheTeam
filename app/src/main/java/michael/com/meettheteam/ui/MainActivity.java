package michael.com.meettheteam.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.meettheteam.R;
import michael.com.meettheteam.model.Response;
import michael.com.meettheteam.ui.fragment.TeamListFragment;
import michael.com.meettheteam.ui.presenter.TeamContract;

public class MainActivity extends AppCompatActivity implements TeamContract.View {

    @BindView(R.id.list) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        initFragment(TeamListFragment.newInstance());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onLoadingFailed(String error) {

    }

    @Override
    public void showContacts(Response response) {

    }

    private void initFragment(Fragment detailFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, detailFragment, "TAG");
        transaction.addToBackStack("TAG");
        transaction.commit();
    }
}
