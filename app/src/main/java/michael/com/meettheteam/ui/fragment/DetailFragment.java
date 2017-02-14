package michael.com.meettheteam.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.meettheteam.R;

/**
 * Created by Mikhail on 2/11/17.
 */

public class DetailFragment extends Fragment {

    @BindView(R.id.image) ImageView imageView;
    @BindView(R.id.name_text) TextView nameText;
    @BindView(R.id.bio) TextView bioText;
    @BindView(R.id.title) TextView titleText;
    private String firstName;
    private String lastName;
    private String bio;
    private String image;
    private String title;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_cardview, container, false);
        ButterKnife.bind(this, rootView);

        loadData();

        return rootView;
    }

    private void loadData() {
        if (firstName != null && lastName != null && bio != null && title != null && image != null) {
            nameText.setText(firstName + " " + lastName);
            bioText.setText(bio);
            titleText.setText(title);
            Glide.with(getContext()).load(image).into(imageView);
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

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}