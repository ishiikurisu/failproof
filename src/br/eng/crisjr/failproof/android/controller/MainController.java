package br.eng.crisjr.failproof.android.controller;

import android.widget.Toast;
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
     * Called when search activity is finished.
     */
    public void receiveChecklist() {
        updateView();
    }

    /**
     * Loads a checklist from memory using its address code.
     *
     * @param address The memory code to retrieve a checklist.
     * @return The expected checklist or null if the address is invalid.
     */
    public String retrieveChecklist(String address) {
        String rawChecklist = null;
        String obtainedChecklist = model.loadList(address);

        if (obtainedChecklist != null) {
            rawChecklist = obtainedChecklist;
        }

        return rawChecklist;
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
}
