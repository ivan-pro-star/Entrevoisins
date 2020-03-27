package com.openclassrooms.entrevoisins.ui.neighbour_list.main;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.AddNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.main.listNeightbour.NeighbourFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // FOR DESIGN ---
    @BindView(R.id.container)
    public ViewPager mViewPager;
    @BindView(R.id.add_neighbour)
    public FloatingActionButton mAddNeighbourButton;
    @BindView(R.id.tabs)
    public TabLayout mTabLayout;

    //---  FRAGMENTS ---
    NeighbourFragment neighbourFragment = new NeighbourFragment();
    NeighbourFragment favouriteFragment = new NeighbourFragment();

    // ADAPTER ---
    SectionPageAdapter mSectionPageAdapter;

    // OVERRIDE ---

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        configurationFragment();
        configureFragmentsOnSectionPageAdapter();
        configureTab();
        configureAddNeighbourButton();
    }

    // FOR TYPE FRAGMENT ---
    private void configurationFragment() {
        favouriteFragment.setFragmentFavourite();
    }

    // CONFIGURATION ---

    public void configureFragmentsOnSectionPageAdapter() {
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mSectionPageAdapter.addFragment(neighbourFragment, "Neighbours");
        mSectionPageAdapter.addFragment(favouriteFragment, "Favourites");
        mViewPager.setAdapter((mSectionPageAdapter));
    }

    public void configureTab(){
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void configureAddNeighbourButton(){
        mAddNeighbourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNeighbourActivity.navigate(MainActivity.this);
            }
        });
    }
}