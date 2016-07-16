package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.controller.*;
import br.eng.crisjr.failproof.android.view.MainView;

public class MainActivity
       extends Activity
{
    private MemoryAccess card;
    private MainView view;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view = new MainView();
        card = new MemoryAccess(getApplicationContext());
        updateLists();
    }

    private void updateLists()
    {
        Context context = getApplicationContext();
        LinearLayout layoutMain = (LinearLayout) findViewById(R.id.layoutMain);
        ScrollView scroll = (ScrollView) findViewById(R.id.scrollLists);
        card = new MemoryAccess(context);
        String[] lists = card.loadLists();

        if (lists == null) {
            scroll = view.resetScroll(context);
        }
        // TODO create else statement

        scroll.setId(R.id.scrollLists);
        view.replaceScroll(layoutMain, scroll);
    }

    public void onClick_buttonAdd(View view)
    {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
        // TODO Create Search Activity
    }
}
