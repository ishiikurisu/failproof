package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import br.eng.crisjr.failproof.android.controller.MemoryAccess;
import br.eng.crisjr.failproof.android.view.MainView;

/**
 * Activity for the Main Screen
 */
public class MainActivity
       extends Activity
{
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
        updateLists();
    }

    /**
     * Checks if it must update the lists on the screen.
     * It resets the scroll if there are no lists in memory by showing an "I'm empty" message,
     * or shows the lists to be accessed. Currently, not showing stored lists.
     */
    private void updateLists()
    {
        Context context = getApplicationContext();
        LinearLayout layoutMain = (LinearLayout) findViewById(R.id.layoutMain);
        ScrollView scroll = (ScrollView) findViewById(R.id.scrollLists);
        String[] lists = view.getLists(context);

        if (lists == null) {
            scroll = view.resetScroll(context);
        }
        // TODO create else statement

        scroll.setId(R.id.scrollLists);
        view.replaceScroll(layoutMain, scroll);
    }

    /**
     * Callback to + button. Will call another activity to show lists available on the internet.
     * @param view I don't know why this argument is here
     */
    public void onClick_buttonAdd(View view)
    {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
        // TODO Improve Search Activity
    }

    /**
     * Callback to ? button
     * @param view I don't know why this argument is here either
     */
    public void onClick_buttonRandom(View view) {
        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
        String output = "Yes! We have bluetooth!";

        if (bluetooth == null) {
            output = "no bluetooth :(";
        }

        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
    }
}
