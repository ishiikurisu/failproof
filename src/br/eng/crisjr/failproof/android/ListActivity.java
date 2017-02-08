package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import br.eng.crisjr.failproof.android.controller.ChecklistController;
import br.eng.crisjr.failproof.android.model.ChecklistModel;
import br.eng.crisjr.failproof.android.model.system.MemoryAccess;
import br.eng.crisjr.failproof.android.view.ChecklistView;

/**
 * Activity to display a usable checklist
 */
public class ListActivity extends Activity {
    protected ChecklistModel model;
    protected ChecklistView view;
    protected ChecklistController controller;
    protected String address;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        // Setting up MVC
        address = getIntent().getStringExtra("address");
        Context context = getApplicationContext();
        model = new ChecklistModel(new MemoryAccess(context), address);
        view = new ChecklistView(this);
        controller = new ChecklistController(model, view);

        // Setting up list
        controller.setup();
    }

    /**
     * Called when a radio button is pressed
     *
     * @param v the pressed radio button
     */
    public void radio_onClick(View v) {
        String checklist = view.retrieveChecklist(view.getEditMode());
        controller.saveChecklist(address, checklist);
    }

    /**
     * Called when the back button is pressed
     */
    public void onClick_buttonBack(View v) {
        onBackPressed();
    }

    /**
     * Called when the edit/save button is pressed
     */
    public void onClick_buttonEdit(View v) {
        // TODO Discover why it can't return to the regular mode
        String checklist = view.retrieveChecklist(view.getEditMode());
        controller.saveChecklist(address, checklist);
        view.toggleMode();
        controller.setup();
    }
}