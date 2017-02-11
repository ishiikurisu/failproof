package br.eng.crisjr.failproof.android.controller;

import br.eng.crisjr.failproof.android.model.ChecklistModel;
import br.eng.crisjr.failproof.android.view.ChecklistView;

/**
 * Controller to coordinate and route the model and view actions from ListActivity.
 */
public class ChecklistController {
    protected ChecklistModel model;
    protected ChecklistView view;

    public ChecklistController(ChecklistModel m, ChecklistView v) {
        model = m;
        view = v;
        view.setController(this);
    }

    public ChecklistController(ChecklistView v, ChecklistModel m) {
        this(m, v);
    }

    /**
     * Display checklist on screen.
     */
    public void setup() {
        String checklist = model.loadList();
        view.setChecklist(checklist);
    }


    /**
     * Stores a checklist to a address on memory.
     *
     * @param address   Where to store the checklist.
     * @param checklist Data to be stored.
     */
    public void saveChecklist(String address, String checklist) {
        model.store(address, checklist);
    }

    /**
     * @return The current checklist.
     */
    public String getChecklist() {
        return model.loadList();
    }

    /**
     * Removes an item from the checklist
     *
     * @param rawChecklist the raw checklist
     * @param index        the index of the item to be removed, 0-indexed
     * @return the new checklist
     */
    public String removeItem(String rawChecklist, int index) {
        return model.removeItem(rawChecklist, index);
    }


}
