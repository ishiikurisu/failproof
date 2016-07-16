package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
import br.eng.crisjr.failproof.android.controller.AccessResultant;
import br.eng.crisjr.failproof.android.controller.DatabaseAccess;

/**
 * Acitivity to show available lists
 */
public class SearchActivity
       extends Activity
       implements AccessResultant
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        DatabaseAccess access = new DatabaseAccess();
        access.setMother(this);
        access.execute();
    }

    public void receiveLists(String[] result)
    {
        // TODO implement this method
    }
}