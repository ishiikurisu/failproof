package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;

/**
 * <p>
 * Shows a checklist to be edited. Saves the edits on memory as they are made.
 * Needs to receive the checklist name as an extra called "checklist". "Duh", I think.
 * </p>
 */
public class ChecklistActivity extends Activity {
    private String checklistName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);
        checklistName = getIntent().getStringExtra("checklist");
    }


}
