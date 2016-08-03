package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
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

    /* TODO Make this activity useful */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        // Setting up list
        String address = getIntent().getStringExtra("address");
        Toast.makeText(ListActivity.this, address, Toast.LENGTH_SHORT).show();
//        TextView title = (TextView) findViewById(R.id.textChecklistTitle);
//        Checklist checklist = view.loadList(address);
//        title.setText(checklist.getTitle());
    }
}