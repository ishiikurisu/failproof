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

    public void setup() {
        String checklist = model.loadList();
        view.setChecklist(checklist);
    }
}
