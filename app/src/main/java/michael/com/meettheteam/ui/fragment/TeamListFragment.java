package michael.com.meettheteam.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import michael.com.meettheteam.R;

/**
 * Created by Mikhail on 2/11/17.
 */

public class TeamListFragment extends Fragment {



    public static TeamListFragment newInstance() {
        return new TeamListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_cardview, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }
}
