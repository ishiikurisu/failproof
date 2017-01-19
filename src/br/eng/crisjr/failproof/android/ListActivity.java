package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.controller.ChecklistController;
import br.eng.crisjr.failproof.android.model.ChecklistModel;
import br.eng.crisjr.failproof.android.model.entity.Checklist;
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

    public void radio_onClick(View v) {
        String checklist = view.retrieveChecklist();
        controller.saveChecklist(address, checklist);
    }
}