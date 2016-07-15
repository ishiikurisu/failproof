package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.controller.*;
import br.eng.crisjr.failproof.android.view.MainView;

public class MainActivity
       extends Activity
//       implements AccessResultant
{
    private MemoryAccess card;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        card = new MemoryAccess(getApplicationContext());
        updateLists();
//        DatabaseAccess access = new DatabaseAccess();
//        access.setMother(this);
//        access.execute();
    }

//    public void receiveLists(String[] result) {
//        TextView tv = (TextView) findViewById(R.id.textHello);
//        tv.setText(result[0]);
//    }

    private void updateLists()
    {
        Context context = getApplicationContext();
        ScrollView scroll = (ScrollView) findViewById(R.id.scrollLists);
        card = new MemoryAccess(context);
        String[] lists = card.loadLists();

        if (lists == null) {
            scroll = MainView.resetScroll(context);
        }
        // TODO create else statement
    }
}
