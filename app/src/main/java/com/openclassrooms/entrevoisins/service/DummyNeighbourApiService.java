package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import java.util.List;

public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> mNeighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> mFavouritesNeighbours = DummyNeighbourGenerator.generateFavouritesNeighbours();


    @Override
    public List<Neighbour> getNeighbours() {
        return mNeighbours;
    }

    @Override
    public List<Neighbour> getFavourites() {
        return mFavouritesNeighbours;
    }

    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        mNeighbours.remove(neighbour);
        if(mFavouritesNeighbours.contains(neighbour)) {
            deleteFavourite(neighbour);
        }
    }
    /**
     * {@inheritDoc}
     * @param favourite
     */
    @Override
    public void deleteFavourite(Neighbour favourite) {
        mFavouritesNeighbours.remove(favourite);
    }
    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        mNeighbours.add(neighbour);
    }

 /**
     * {@inheritDoc}
     * @param favourite
     */
    @Override
    public void createFavourite(Neighbour favourite) {
        mFavouritesNeighbours.add(favourite);
    }
}