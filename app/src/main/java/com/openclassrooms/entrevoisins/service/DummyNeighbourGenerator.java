package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {
    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(0, "Caroline", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint-Pierre-du-Mont.",
                    "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..","www.facebook.fr/Caroline"),
//            new Neighbour(1, "Jack", "https://i.pravatar.cc/150?u=a042581f4e29026704e", "51, rue Cazade.",
//                    "01.38.00.16.84",  "Bonjour !Je souhaiterais faire de la marche nordique.","www.facebook.fr/Jack"),
            new Neighbour(2, "Chloé", "https://i.pravatar.cc/150?u=a042581f4e29026704f", "67, rue Gustave Eiffel.",
                    "04.47.71.28.26",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..","www.facebook.fr/Chloé"),
//            new Neighbour(3, "Vincent", "https://i.pravatar.cc/150?u=a042581f4e29026704a", "58, Chemin Du Lavarin Sud.",
//                    "03.61.58.82.88",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..","www.facebook.fr/Vincent"),
            new Neighbour(4, "Elodie", "https://i.pravatar.cc/150?u=a042581f4e29026704b", "71, Rue de Verdun.",
                    "05.05.10.96.20",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..","www.facebook.fr/Elodie"),
//            new Neighbour(5, "Sylvain", "https://i.pravatar.cc/150?u=a042581f4e29026704c", "57, rue de l'Epeule.",
//                    "01.86.08.95.84",  "Bonjour !Je souhaiterais faire de la marche nordique. ","www.facebook.fr/Sylvain"),
            new Neighbour(6, "Laetitia", "https://i.pravatar.cc/150?u=a042581f4e29026703d", "15, rue Grande Fusterie",
                    "05.58.87.68.45",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..","www.facebook.fr/Laetitia"),
//            new Neighbour(7, "Dan", "https://i.pravatar.cc/150?u=a042581f4e29026703b", "56, Place Charles de Gaulle.",
//                    "03.56.69.12.05",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..","www.facebook.fr/Dan"),
//            new Neighbour(8, "Joseph", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint-Pierre-du-Mont ",
//                    "04.32.90.99.31",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..","www.facebook.fr/Joseph"),
            new Neighbour(9, "Emma", "https://i.pravatar.cc/150?u=a042581f4e29026706d", "86, avenue Ferdinand de Lesseps",
                    "01.53.49.67.19",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..","www.facebook.fr/Emma"),
//            new Neighbour(10, "Patrick", "https://i.pravatar.cc/150?u=a042581f4e29026702d", "78, place de Miremont.",
//                    "01.81.48.16.09",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..","www.facebook.fr/Patrick"),
            new Neighbour(11, "Ludovic", "https://i.pravatar.cc/150?u=a042581f3e39026702d", "56, rue Descartes.",
                    "01.15.59.42.36",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..","www.facebook.fr/Ludovic")
    );
    public static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }

    public static List<Neighbour> DUMMY_FAVOURITES_NEIGHBOURS = Arrays.asList(new Neighbour( DUMMY_NEIGHBOURS.get(0)), new Neighbour(DUMMY_NEIGHBOURS.get(3)));


    public static List<Neighbour> generateFavouritesNeighbours() {
        return new ArrayList<>(DUMMY_FAVOURITES_NEIGHBOURS);
    }
}
