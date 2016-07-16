package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.controller.*;
import br.eng.crisjr.failproof.android.view.MainView;

public class MainActivity
       extends Activity
//       implements AccessResultant
{
    private MemoryAccess card;
    private MainView view;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view = new MainView();
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
        LinearLayout layoutMain = (LinearLayout) findViewById(R.id.layoutMain);
        ScrollView scroll = (ScrollView) findViewById(R.id.scrollLists);
        card = new MemoryAccess(context);
        String[] lists = card.loadLists();

        if (lists == null) {
            scroll = view.resetScroll(context);
            scroll.setId(R.id.scrollLists);
        }
        // TODO create else statement

        view.replaceScroll(layoutMain, scroll);
    }
}
