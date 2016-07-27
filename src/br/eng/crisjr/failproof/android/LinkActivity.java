package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.controller.AccessResultant;
import br.eng.crisjr.failproof.android.controller.DatabaseAccess;

/**
 * Activity to display a list which can be saved to memory or not
 */
public class LinkActivity
       extends Activity
       implements AccessResultant
{
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

    public void receiveLists(String[] result)
    {
        TextView tv = (TextView) findViewById(R.id.textPlaceholder);
        String outlet = "";

        for (String it: result)
        {
            outlet += it + "\n";
        }

        tv.setText(outlet);
    }
}