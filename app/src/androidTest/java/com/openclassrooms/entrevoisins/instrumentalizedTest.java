package com.openclassrooms.entrevoisins;

/**
 * Created by <ivan-pro> on 01/03/2020.
 * Project : EntrevoisinsFinal.
 */

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.main.MainActivity;
import com.openclassrooms.entrevoisins.utils.ClickViewAction;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.core.AllOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class instrumentalizedTest {

    private static int ITEMS_COUNT = DUMMY_NEIGHBOURS.size();
    private MainActivity mActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
    }

    // TEST --

    @Test
    public void getInfoNeighbourTest() {
        goTabNeighbours();
        //Click sur le premier item
        onView(allOf(isDisplayed(),withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, new ClickViewAction()));

        //verification que le nom affiche dans le textView "id_full_name_title" est bien le même que celui sauvegarde
        onView(withId(R.id.id_full_name_title)).check(matches(withText(ClickViewAction.sName_user)));
    }

    /**
     * Voici la methode qu_il fallait faire evoluer
     *
     * getFavouritesTest
     *
     * on cree un favoris, on verifie q_il s_affiche dans l_onglet favoris
     *
     * */
    @Test
    public void CreateFavouritesTest()
    {
        String name = "Emma";
        //EMMA n_est pas favoris
        goTabFavourites();
        onView(withText(name)).check(matches(not(isDisplayed())));

        //Ouvre les details du voisin numero 4 (Emma)
        goTabNeighbours();
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, new ClickViewAction()));

        //Voisin 4 devient favoris
        onView(withId(R.id.id_button_favourite)).perform(click());

        //Retour sur le MainActivity
        pressBack();

        goTabFavourites();
        onView(allOf(isDisplayed(), withText(name)));
    }

    @Test
    public void deleteNeighbourTest() {
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }


    @Test
    public void createNeighbourTest(){
        String name = "Leo Messi";
        onView(withText(name)).check((doesNotExist()));

        //Start AddNeighbourActivity
        onView(withId(R.id.add_neighbour)).perform(click());
        button_create_evolve(name);

        //back to main with the click on "create"
        onView(withId(R.id.create)).perform(click());
        onView(withText(name)).check(matches(isDisplayed()));
    }

    // UTILS --


    public void goTabFavourites(){
        onView(withText("Favourites")).perform(click());
    }
    public void goTabNeighbours(){
        onView(withText("Neighbours")).perform(click());
    }

    public void button_create_evolve(String name) {
        //AddNeighbourActivity

        //Verifie que le button create est desactive
        onView(ViewMatchers.withId(R.id.create))
                .check(matches(not(isEnabled())));
        //Ecrit dans l_editText "name"
        onView(ViewMatchers.withId(R.id.name))
                .perform(typeText(name), closeSoftKeyboard());
        //Verifie que le button create est active
        onView(ViewMatchers.withId(R.id.create))
                .check(matches(isEnabled()));
    }
}