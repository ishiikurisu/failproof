package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import br.eng.crisjr.failproof.android.model.Checklist;
import br.eng.crisjr.failproof.android.view.ChecklistView;

/**
 * Activity to display a usable checklist
 */
public class ListActivity
        extends Activity {
    private ChecklistView view = new ChecklistView();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        // Setting up list
        String address = getIntent().getStringExtra("address");
        TextView title = (TextView) findViewById(R.id.textChecklistTitle);
        Checklist checklist = view.loadList(getApplicationContext(), address);
        title.setText(checklist.getTitle());

        // Drawing checklist
        replaceScroll(checklist);
    }

    private void replaceScroll(Checklist checklist) {
        LinearLayout layout = view.drawChecklist(getApplicationContext(), this, checklist);
        layout.setId(R.id.layoutChecklist);
        ScrollView scroll = (ScrollView) findViewById(R.id.scrollChecklist);
        scroll = view.replaceScroll(scroll, layout);
    }

    public void radio_onClick(View v) {
        TextView title = (TextView) findViewById(R.id.textChecklistTitle);
        String address = getIntent().getStringExtra("address");
        String checklist = title.getText() + "\n" +
                view.extractChecklist((ScrollView) findViewById(R.id.scrollChecklist));

        view.saveChecklistOnMemory(getApplicationContext(), address, checklist);
    }
}