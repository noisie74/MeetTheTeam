package michael.com.meettheteam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.meettheteam.R;
import michael.com.meettheteam.model.Contacts;


/**
 * Adapter
 */

public class TeamContactsAdapter extends RecyclerView.Adapter<TeamContactsAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private final List<Contacts> teamContacts;
    Context mContext;


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public TeamContactsAdapter(List<Contacts> teamContacts, OnItemClickListener listener) {
        this.teamContacts = teamContacts;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.avatar) ImageView avatar;
        @BindView(R.id.name_text) TextView nameText;
        @BindView(R.id.title) TextView titleText;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_cardview, parent, false);
        mContext = parent.getContext();

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TeamContactsAdapter.ViewHolder holder, int position) {

        holder.nameText.setText(teamContacts.get(position).getFirstName()
                + " "
                + teamContacts.get(position).getLastName());
        holder.titleText.setText(teamContacts.get(position).getTitle());
        String image = teamContacts.get(position).getAvatar();
        Glide.with(mContext).load(image).into(holder.avatar);

    }

    @Override
    public int getItemCount() {
        return teamContacts.size();
    }
}
