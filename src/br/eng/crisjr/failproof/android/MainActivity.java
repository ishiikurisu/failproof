package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
     * @return Gets the view object that helps this activity.
     */
    public MainView getView() {
        return this.view;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view = new MainView();
        MainView.resetMemory(getApplicationContext());
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
        String[] lists = MainView.getStuff(context);
        scroll = (lists == null) ? MainView.resetScroll(context) : MainView.createScroll(context, this, lists);
        scroll.setId(R.id.scrollLists);
        MainView.replaceScroll(layoutMain, scroll);
    }

    /**
     * Checks if it must update the lists on the screen with destructive buttons.
     * It resets the scroll if there are no lists in memory by showing an "I'm empty" message,
     * or shows the lists to be destroyed.
     */
    private void enableListsDestruction() {
        Context context = getApplicationContext();
        LinearLayout layoutMain = (LinearLayout) findViewById(R.id.layoutMain);
        ScrollView scroll = (ScrollView) findViewById(R.id.scrollLists);
        String[] lists = MainView.getStuff(context);
        scroll = (lists == null) ? MainView.resetScroll(context) : MainView.createKillerScroll(context, this, lists);
        scroll.setId(R.id.scrollLists);
        MainView.replaceScroll(layoutMain, scroll);
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

    // TODO Add possibility to delete checklists
    /**
     * Callback to ? button
     * @param view I don't know why this argument is here either
     */
    public void onClick_buttonRandom(View view) {
        // TODO Make this button activate or deactivate the delete mode
        // TODO Enable lists to be deleted
        boolean isDeleteMode = !this.view.getMode();
        Button buttonRandom = (Button) findViewById(R.id.buttonRandom);

        if (isDeleteMode) {
            buttonRandom.setBackgroundColor(0x951951);
            enableListsDestruction();
        } else {
            buttonRandom.setBackgroundColor(0xFFF123);
            updateLists();
        }

        this.view.setMode(isDeleteMode);
    }

    /**
     * Callback to a list title on the main screen. Will call another activity for the chosen list
     *
     * @param address The list's memory address
     */
    public void textTitle_onClick(String address) // this is cheating, ya know?
    {
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
                if (!view.getMode()) updateLists();
                else enableListsDestruction();
                break;
        }
    }
}
