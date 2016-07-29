package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import br.eng.crisjr.failproof.android.controller.AccessResultant;
import br.eng.crisjr.failproof.android.controller.DatabaseAccess;
import br.eng.crisjr.failproof.android.view.LinkView;

/**
 * Activity to display a list which can be saved to memory or not
 */
public class LinkActivity
       extends Activity
       implements AccessResultant
{
    LinkView view = new LinkView();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link);

        // download list
        String link = getIntent().getStringExtra("link");
        DatabaseAccess access = new DatabaseAccess(this);
        access.setOperation(DatabaseAccess.GET_LIST);
        access.execute(link);
    }

    /**
     * Implementation of the required method to download lists from internet.
     * @param result the list title and items
     */
    public void receiveLists(String[] result)
    {
        int id = R.id.scrollInfo;
        ScrollView scrollInfo = (ScrollView) findViewById(id);
        LinearLayout linearInfo = (LinearLayout) findViewById(R.id.linearInfo);
        scrollInfo = view.createScroll(getApplicationContext(), result);
        scrollInfo.setId(id);
        view.replaceScroll(linearInfo, scrollInfo);
    }

    // TODO Implement the save and cancel buttons. Cancel first because it' easier.
}