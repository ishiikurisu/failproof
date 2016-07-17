package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import br.eng.crisjr.failproof.android.controller.AccessResultant;
import br.eng.crisjr.failproof.android.controller.DatabaseAccess;
import br.eng.crisjr.failproof.android.view.SearchView;

/**
 * Activity to show available lists
 */
public class SearchActivity
       extends Activity
       implements AccessResultant
{
    private SearchView view = new SearchView();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        DatabaseAccess access = new DatabaseAccess(this);
        access.setOperation(DatabaseAccess.GET_LISTS);
        access.execute();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    public void receiveLists(String[] result)
    {
        ScrollView scroll = (ScrollView) findViewById(R.id.scrollSearch);
        LinearLayout linearSearch = (LinearLayout) findViewById(R.id.linearSearch);

        if (result != null) {
            scroll = view.createScroll(getApplicationContext(),result);
        }

        scroll.setId(R.id.scrollSearch);
        view.replaceScroll(linearSearch, scroll);
    }
}