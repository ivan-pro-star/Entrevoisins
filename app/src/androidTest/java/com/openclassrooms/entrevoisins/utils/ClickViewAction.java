package com.openclassrooms.entrevoisins.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

public class ClickViewAction implements ViewAction {
    public static String sName_user;
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific Neighbours";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.item_list_name);
        TextView viewUserName = view.findViewById(R.id.item_list_name);
        sName_user = (String) viewUserName.getText();
        // Maybe check for null
        button.performClick();
    }
}