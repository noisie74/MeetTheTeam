package michael.com.meettheteam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.meettheteam.R;
import michael.com.meettheteam.model.Contacts;

import static android.widget.AdapterView.*;

/**
 * Created by Mikhail on 2/12/17.
 */

public class TeamContactsAdapter extends RecyclerView.Adapter<TeamContactsAdapter.ViewHolder> {

    private final List<Contacts> teamContacts;
    private final OnItemClickListener onItemClicked;
    Context mContext;

    public interface OnItemClickListener {
        void onClick(Contacts Item);
    }


    public TeamContactsAdapter(List<Contacts> teamContacts, OnItemClickListener onItemClicked) {
        this.teamContacts = teamContacts;
        this.onItemClicked = onItemClicked;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.avatar) ImageView avatar;
        @BindView(R.id.name_text) TextView nameText;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void click(final Contacts teamContactsListData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(teamContactsListData);
                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_cardview, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TeamContactsAdapter.ViewHolder holder, int position) {

        holder.nameText.setText(teamContacts.get(position).getFirstName()
                + " "
                + teamContacts.get(position).getLastName());
        String image = teamContacts.get(position).getAvatar();
        Glide.with(mContext).load(image).into(holder.avatar);

    }


    @Override
    public int getItemCount() {
        return teamContacts.size();
    }
}
