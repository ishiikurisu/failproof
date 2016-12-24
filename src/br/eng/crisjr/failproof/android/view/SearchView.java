package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.LinkActivity;
import br.eng.crisjr.failproof.android.SearchActivity;
import br.eng.crisjr.failproof.android.controller.SearchController;
import br.eng.crisjr.failproof.web;

/**
 * Updates the search screen
 */
public class SearchView
{
    protected SearchActivity activity;
    protected SearchController controller;

    public SearchView(SearchActivity activity) {
        this.activity = activity;
    }

    public SearchController setController(SearchController controller) {
        // TODO Download checklists
        this.controller = controller;
        controller.downloadChecklists();
        return this.controller;
    }
}