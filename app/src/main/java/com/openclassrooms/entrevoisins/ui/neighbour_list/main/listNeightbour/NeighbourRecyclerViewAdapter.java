package com.openclassrooms.entrevoisins.ui.neighbour_list.main.listNeightbour;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.InformationNeighbourActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourRecyclerViewAdapter extends RecyclerView.Adapter<NeighbourRecyclerViewAdapter.ViewHolder> {

    //  DATA ---
    private final List<Neighbour> mNeighbours;

    /**
     * Constructor
     * */
    public NeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    // OVERRIDE ---
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = config_view(parent);
        return new NeighbourRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NeighbourRecyclerViewAdapter.ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.configItemsOnViewHolder(neighbour);
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    // CONFIG ---


    private View config_view(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_neighbour, parent, false);
        return view;
    }
    /**
     * Class ViewHolder
     * */
    public class ViewHolder extends RecyclerView.ViewHolder {
        // DESIGN --
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_delete_button)
        public ImageButton delete_button;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;

        // CONSTRUCTOR --
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        // CONFIG VIEW ON VIEW HOLDER ---
        public void configItemsOnViewHolder(Neighbour neighbour){
            this.mNeighbourName.setText(neighbour.getName());
            Glide.with(this.mNeighbourAvatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(this.mNeighbourAvatar);

            configClickOnName( neighbour);
            configClickDelete(neighbour);
        }

        // CONFIG CLICK NAME ---
        public void configClickOnName( Neighbour neighbour){
            this.mNeighbourName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startInformationActivity(neighbour, v);
                }
            });
        }

        // Go to Information Activity for neighbour in param
        public void startInformationActivity(Neighbour neighbour, View view){
            Intent intent = createIntentInformationNeighbour(view.getContext(), neighbour);
            view.getContext().startActivity(intent);
        }

        // CREATE INTENT InformationNeighbourActivity
        public Intent createIntentInformationNeighbour(Context context, Neighbour neighbour){
            Intent intent = new Intent(context, InformationNeighbourActivity.class);
            intent.putExtra("USER_NAME",neighbour.getName());
            intent.putExtra("ID",neighbour.getId());
            intent.putExtra("PHONE",neighbour.getPhoneNumber());
            intent.putExtra("ADDRESS",neighbour.getAddress());
            intent.putExtra("ABOUT_ME",neighbour.getAboutMe());
            intent.putExtra("AVATAR_URL",neighbour.getAvatarUrl());
            intent.putExtra("SITE",neighbour.getSite());
            return intent;
        }
        // CONFIG DELETE BUTTON ---
        // JUST FOR FRAGMENT NEIGHBOUR
        public void configClickDelete(Neighbour neighbour) {
                delete_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                    }
                });
            }
    }//end Class ViewHolder
}
