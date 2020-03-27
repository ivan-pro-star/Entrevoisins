package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationNeighbourActivity extends AppCompatActivity {

    // DESIGN ---
    @BindView(R.id.id_full_name_title)
    public TextView full_name_title;
    @BindView(R.id.id_address)
    public TextView address;
    @BindView(R.id.id_phone)
    public TextView phone;
    @BindView(R.id.id_about_me)
    public TextView about_me;
    @BindView(R.id.id_image_user)
    public ImageView imageUser;
    @BindView(R.id.id_site)
    public TextView site;
    @BindView(R.id.id_header)
    public LinearLayout header;
    @BindView(R.id.id_button_favourite)
    public FloatingActionButton button_favourite;

    // SERVICE FOR DATA
    private NeighbourApiService  mApiService = DI.getNeighbourApiService();

    // NEIGHBOUR
    private  Neighbour mNeighbour;

    // OVERRIDE --
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
           case android.R.id.home : {
               finish();
               return true;
           }
       }
       return super.onOptionsItemSelected(item);
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_neighbour);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setNeighbour();
        configView();
        configButtonClickOnFavourite();
    }

    public void setNeighbour() {
        Intent intent = getIntent();
        mNeighbour =  new Neighbour( intent.getLongExtra("ID",0), intent.getStringExtra("USER_NAME"),
                intent.getStringExtra("AVATAR_URL"), intent.getStringExtra("ADDRESS"), intent.getStringExtra("PHONE"),
                intent.getStringExtra("ABOUT_ME"), intent.getStringExtra("SITE"));
    }
    // CONFIG ---
    public void configView(){
        full_name_title.setText(mNeighbour.getName());
        address.setText(mNeighbour.getAddress());
        phone.setText(mNeighbour.getPhoneNumber());
        about_me.setText(mNeighbour.getAboutMe());
        site.setText(mNeighbour.getSite());
        Glide.with(header).load(mNeighbour.getAvatarUrl()).centerCrop().into(imageUser);
    }

    private void configButtonClickOnFavourite() {
        button_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean favouriteExist = mApiService.getFavourites().contains(mNeighbour);
                String message = "Add "+ mNeighbour.getName()+" to favourites";
                if(!favouriteExist){
                    mApiService.createFavourite(mNeighbour);
                }
                else {
                    mApiService.deleteFavourite(mNeighbour);
                    message = mNeighbour.getName()+" deleted from favourites";
                }
                Toast.makeText(InformationNeighbourActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
