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
        view.resetMemory(getApplicationContext());
        updateLists();
    }

    /**
     * Checks if it must update the lists on the screen.
     * It resets the scroll if there are no lists in memory by showing an "I'm empty" message,
     * or shows the lists to be accessed.
     */
    private void updateLists()
    {
        Context context = getApplicationContext();
        LinearLayout layoutMain = (LinearLayout) findViewById(R.id.layoutMain);
        ScrollView scroll = (ScrollView) findViewById(R.id.scrollLists);
        String[] lists = view.getStuff(context);
        scroll = (lists == null) ? view.resetScroll(context) : view.createScroll(context, this, lists);
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
        startActivityForResult(intent, MainView.SAVE_REQUEST);
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

    /**
     * Callback to a list title on the main screen. Will call another activity for the chosen list
     *
     * @param address The list's memory address
     */
    public void textTitle_onClick(String address) { // this is cheating, ya know?
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra("address", address);
        startActivity(intent);
    }

    /**
     * Method to receive the results of the child activities
     *
     * @param requestCode The code to the requested activity
     * @param resultCode  The result of the operation
     * @param intent      The intent sent to the activity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (resultCode) {
            case MainView.SAVE_REQUEST:
                updateLists();
                break;
        }
    }

    // TODO Add possibility to delete checklists
}
