package br.eng.crisjr.failproof.android.controller;

import android.widget.Toast;
import br.eng.crisjr.failproof.android.model.MainModel;
import br.eng.crisjr.failproof.android.model.constants.Template;
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

    /**
     * Draws the current state on screen.
     */
    public void updateView() {
        model.retrieveStuff();
        String[] titles = model.getTitles();

        if (titles == null) {
            view.drawEmptyView();
        } else {
            view.drawFilledView(model.loadStuff());
        }
    }

    /**
     * Calls the search checklist activity to enable the user to pick a checklist.
     */
    public void searchChecklists() {
        view.searchNewLists();
    }

    /**
     * Deletes a checklist from memory and updates the view
     *
     * @param address The checklist address on memory
     */
    public void deleteChecklist(String address) {
        model.removeList(address);
        updateView();
    }

    /**
     * Switches the delete mode on or off.
     */
    public void toggleMode() {
        view.switchMode();
        updateView();
    }

    /**
     * Creates an empty checklist.
     */
    public void createChecklist() {
        model.createEmptyChecklist(Template.TEMPLATE_CHECKLIST);
        // TODO Start checklist activity for editing this template checklist
        updateView();
    }
}
