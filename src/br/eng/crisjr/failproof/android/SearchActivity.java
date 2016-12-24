package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import br.eng.crisjr.failproof.android.controller.SearchController;
import br.eng.crisjr.failproof.android.model.SearchModel;
import br.eng.crisjr.failproof.android.view.SearchView;

/**
 * Activity to show available lists
 */
public class SearchActivity extends Activity {
    protected SearchView view = null;
    protected SearchModel model = null;
    protected SearchController controller = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        findViewById(R.id.scrollSearch).requestFocus();

        view = new SearchView(this);
        model = new SearchModel();
        controller = new SearchController(view, model);

        controller.downloadChecklists();
    }

    /**
     * When the edit search is clicked.
     */
    public void onClick_editSearch(View view) {
        // TODO Make text default disappear when the user selects the filter edit
        EditText editSearch = (EditText) view;
        String expected = getResources().getString(R.string.search_edit);
        String collected = editSearch.getText().toString();

        if (expected.equals(collected)) {
            editSearch.setText("");
        }
    }
}