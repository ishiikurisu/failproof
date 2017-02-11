package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.eng.crisjr.failproof.android.model.system.MemoryAccess;
import br.eng.crisjr.failproof.android.view.MainView;
import br.eng.crisjr.failproof.android.model.MainModel;
import br.eng.crisjr.failproof.android.controller.MainController;

/**
 * Activity for the Main Screen
 */
public class MainActivity
       extends Activity
{
    protected MainView view;
    protected MainModel model;
    protected MainController controller;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        MemoryAccess memoryAccess = new MemoryAccess(getApplicationContext());
        memoryAccess.resetMemory();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view = new MainView(this);
        model = new MainModel(memoryAccess);
        controller = new MainController(view, model);

        controller.updateView();
    }

    /**
     * Callback to + button. Will call another activity to show lists available on the internet.
     * @param view I don't know why this argument is here
     */
    public void onClick_buttonAdd(View view) {
        this.view.AskWhatToAdd();
    }

    // TODO Fix bug that happens when deleting the last checklist
    /**
     * Callback to x button. Toggles the delete mode.
     * @param view I don't know why this argument is here either
     */
    public void onClick_buttonRandom(View view) {
        controller.toggleMode();
    }

    /**
     * Receives data from previous activities.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.updateView();
    }
}
