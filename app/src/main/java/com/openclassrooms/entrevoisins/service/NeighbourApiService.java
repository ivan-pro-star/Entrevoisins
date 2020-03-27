package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {
    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();
 /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavourites();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);
 /**
     * Deletes a neighbour
     * @param favourite
     */
    void deleteFavourite(Neighbour favourite);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

 /**
     * Create a neighbour
     * @param favourite
     */
    void createFavourite(Neighbour favourite);
}
