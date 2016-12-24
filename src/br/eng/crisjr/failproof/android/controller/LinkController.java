package br.eng.crisjr.failproof.android.controller;

import br.eng.crisjr.failproof.android.model.LinkModel;
import br.eng.crisjr.failproof.android.model.system.AccessResultant;
import br.eng.crisjr.failproof.android.model.system.DatabaseAccess;
import br.eng.crisjr.failproof.android.view.LinkView;

/**
 * Controller for LinkActivity
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

    public void downloadChecklist() {
        DatabaseAccess db = new DatabaseAccess(this);
        db.setOperation(DatabaseAccess.GET_LIST);
        db.execute(model.getLink());
    }

    @Override
    public void receiveLists(String[] result) {
        model.setList(result);
        view.updateStuff(model.getList());
    }
}
