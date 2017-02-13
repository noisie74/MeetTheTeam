package michael.com.meettheteam.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.meettheteam.R;

/**
 * Created by Mikhail on 2/11/17.
 */

public class TeamListFragment extends Fragment {

    @BindView(R.id.name) TextView nameText;
    @BindView(R.id.bio) TextView bioText;
    private String firstName;
    private String lastName;
    private String bio;

    public static TeamListFragment newInstance(String firstName, String lastName, String bio) {
        return new TeamListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_cardview, container, false);
        ButterKnife.bind(this, rootView);

        loadData();

        return rootView;
    }

    private void loadData() {
        if (firstName != null && lastName != null && bio != null) {
            nameText.setText(firstName + " " + lastName);
            bioText.setText(bio);
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
