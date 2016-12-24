package br.eng.crisjr.failproof.android.controller;

import br.eng.crisjr.failproof.android.model.SearchModel;
import br.eng.crisjr.failproof.android.model.system.AccessResultant;
import br.eng.crisjr.failproof.android.model.system.DatabaseAccess;
import br.eng.crisjr.failproof.android.view.SearchView;

/**
 * Controller to deal with SearchActivity's actions.
 */
public class SearchController implements AccessResultant {
    protected SearchModel model;
    protected SearchView view;

    public SearchController(SearchModel model, SearchView view) {
        this.model = model;
        this.view = view;
        view.setController(this);
    }

    public SearchController(SearchView view, SearchModel model) {
        this(model, view);
    }

    /**
     * Downloads the checklists' links to display on screen.
     */
    public void downloadChecklists() {
        DatabaseAccess access = new DatabaseAccess(this);
        access.setOperation(DatabaseAccess.GET_STUFF);
        access.execute();
    }

    /**
     * Displays the available lists for download.
     *
     * @param result The call to get stuff from the database, as described on API.
     */
    @Override
    public void receiveLists(String[] result) {
        model.addLists(result);
        view.updateStuff(model.getLists(), model.getLinks());
    }
}
