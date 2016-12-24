package br.eng.crisjr.failproof.android.controller;

import br.eng.crisjr.failproof.android.model.LinkModel;
import br.eng.crisjr.failproof.android.model.system.AccessResultant;
import br.eng.crisjr.failproof.android.model.system.DatabaseAccess;
import br.eng.crisjr.failproof.android.view.LinkView;

/**
 * Controller for LinkActivity. Is able to download stuff from the internet through the database access class.
 *
 */
public class LinkController implements AccessResultant {
    protected LinkModel model;
    protected LinkView view;

    public LinkController(LinkModel model, LinkView view) {
        this.model = model;
        this.view = view;
        view.setController(this);
    }

    public LinkController(LinkView v, LinkModel m) {
        this(m, v);
    }

    /**
     * Downloads the checklist specified by link given to the model class.
     */
    public void downloadChecklist() {
        DatabaseAccess db = new DatabaseAccess(this);
        db.setOperation(DatabaseAccess.GET_LIST);
        db.execute(model.getLink());
    }

    /**
     * Stores a downloaded checklist on RAM memory and display it on screen.
     *
     * @param result A raw list, as described on the API description of a list.
     */
    @Override
    public void receiveLists(String[] result) {
        model.setList(result);
        view.updateStuff(model.getList());
    }

    /**
     * Saves the stored checklist on memory.
     */
    public void saveChecklist() {
        model.store();
        view.returnToMainActivity();
    }
}
