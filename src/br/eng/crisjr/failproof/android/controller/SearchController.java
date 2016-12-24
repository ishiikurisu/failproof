package br.eng.crisjr.failproof.android.controller;

import br.eng.crisjr.failproof.android.model.SearchModel;
import br.eng.crisjr.failproof.android.view.SearchView;

/**
 * Controller to deal with SearchActivity's actions.
 */
public class SearchController {
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
}
