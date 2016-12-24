package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.eng.crisjr.failproof.android.view.MainView;
import br.eng.crisjr.failproof.android.model.MainModel;
import br.eng.crisjr.failproof.android.controller.MainController;

/**
 * Activity for the Main Screen
 */
public class MainActivity
       extends Activity
{
    protected MainView view;
    protected MainModel model;
    protected MainController controller;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view = new MainView(this);
        model = new MainModel();
        controller = new MainController(view, model);

        controller.updateView();
    }

    // TODO Add possibility to download lists
    /**
     * Callback to + button. Will call another activity to show lists available on the internet.
     * @param view I don't know why this argument is here
     */
    public void onClick_buttonAdd(View view) {
        controller.addChecklist();
    }

    // TODO Add possibility to delete checklists
    /**
     * Callback to x button. Toggles the delete mode.
     * @param view I don't know why this argument is here either
     */
    public void onClick_buttonRandom(View view) {
    }
}
