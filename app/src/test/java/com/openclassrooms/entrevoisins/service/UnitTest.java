package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class UnitTest {
    private NeighbourApiService mService ;
    private Neighbour mNeighbourTest = new Neighbour(200,"Jean-Paul Belmondo",
            "https://i.pravatar.cc/150?u=a042581f4e29026703b","45 Boulevar Mazagran",
            "04544654","J'aime la pistache.","www.facebook/Jean-Paul");
    @Before
    public void setup() {
       mService = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = mService.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = mService.getNeighbours().get(0);
        assertTrue(mService.getNeighbours().contains(neighbourToDelete));
        mService.deleteNeighbour(neighbourToDelete);
        assertFalse(mService.getNeighbours().contains(neighbourToDelete));
    }
    @Test
    public void createNeighbourWithSuccess() {
        assertFalse(mService.getNeighbours().contains(mNeighbourTest));
        mService.createNeighbour(mNeighbourTest);
        assertTrue(mService.getNeighbours().contains(mNeighbourTest));
    }
     @Test
    public void getFavouritesWithSuccess() {
        List<Neighbour> favourites = mService.getFavourites();
        List<Neighbour> expectedFavourites= DummyNeighbourGenerator.DUMMY_FAVOURITES_NEIGHBOURS;
        assertThat(favourites, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedFavourites.toArray()));
    }
    @Test
    public void deleteFavouriteWithSuccess() {
        Neighbour favouriteToDelete = mService.getFavourites().get(0);
        mService.deleteFavourite(favouriteToDelete);
        assertFalse(mService.getFavourites().contains(favouriteToDelete));
    }
    @Test
    public void createFavouriteWithSuccess() {
        assertFalse(mService.getFavourites().contains(mNeighbourTest));
        mService.createFavourite(mNeighbourTest);
        assertTrue(mService.getFavourites().contains(mNeighbourTest));
    }
}
