package br.eng.crisjr.failproof.android.controller;

import br.eng.crisjr.failproof.android.model.MainModel;
import br.eng.crisjr.failproof.android.view.MainView;

/**
 * Controller for MainActivity's actions
 */
public class MainController {
    protected MainModel model;
    protected MainView view;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public MainController(MainView view, MainModel model) {
        this(model, view);
    }

    // TODO Actually update view

    /**
     * Draws the current state on screen.
     */
    public void updateView() {
        String[] titles = model.getTitles();

        if (titles == null) {
            view.drawEmptyView();
        } else {
            // TODO Draw lists on screen
        }
    }

    /**
     * Calls the search checklist activity to enable the user to pick a checklist
     */
    public void addChecklist() {
        // TODO Actually start search activity
        view.startSearchActivity();
    }
}
