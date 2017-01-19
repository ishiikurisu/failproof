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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        // Setting up MVC
        String address = getIntent().getStringExtra("address");
        Context context = getApplicationContext();
        model = new ChecklistModel(new MemoryAccess(context), address);
        view = new ChecklistView(this);
        controller = new ChecklistController(model, view);

        // Setting up list
        controller.setup();
    }

    // TODO REFACTOR THIS CODE URGENTLY

    public void radio_onClick(View v) {
        TextView title = (TextView) findViewById(R.id.textChecklistTitle);
        String address = getIntent().getStringExtra("address");
        String checklist = title.getText() + "\n" +
                view.extractChecklist((ScrollView) findViewById(R.id.scrollChecklist));

        view.saveChecklistOnMemory(getApplicationContext(), address, checklist);
    }
}