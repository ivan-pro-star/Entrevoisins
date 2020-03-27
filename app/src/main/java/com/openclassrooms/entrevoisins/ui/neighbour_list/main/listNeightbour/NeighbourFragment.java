package com.openclassrooms.entrevoisins.ui.neighbour_list.main.listNeightbour;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class NeighbourFragment extends Fragment {

    // TYPE FRAGMENT
    private boolean isFragmentFavourite = false;
    // API SERVICE FOR GET DATA
    private NeighbourApiService mApiService;

    // FOR DESIGN ---
    private RecyclerView mRecyclerView;
    private int id_recycler;
    //DATA
    private List<Neighbour> neighbours;

    // BOOLEAN FOR TYPE FRAGMENT ---
    public void setFragmentFavourite(){
        this.isFragmentFavourite = true;
    }
    // OVERRIDE ---
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = configRecyclerView(inflater, container);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // CONFIG ---
    private View configRecyclerView(LayoutInflater inflater, ViewGroup container) {
       View  view = inflater.inflate(R.layout.recycler_neighbour_list, container, false);
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        return mRecyclerView;
    }

    private void configListNeighbours(){
        if(!isFragmentFavourite){
            neighbours = mApiService.getNeighbours();
        }
        else{
            neighbours = mApiService.getFavourites();
        }
    }
    /**
     * Init the List of neighbours
     */
    private void initList() {
        configListNeighbours();
        mRecyclerView.setAdapter(new NeighbourRecyclerViewAdapter(neighbours));
    }
    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        Neighbour neighbour = event.neighbour;
        mApiService.deleteNeighbour(neighbour);
        initList();
    }
}


