package br.eng.crisjr.failproof.android;

import android.os.Bundle;
import android.view.View;
import br.eng.crisjr.failproof.android.controller.Checklists;
import br.eng.crisjr.failproof.android.controller.DatabaseAccess;
import br.eng.crisjr.failproof.android.view.FailproofActivity;

/**
 * Page describing a checklist
 */
public class ListActivity extends FailproofActivity {
    private Checklists controller = new Checklists();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        /// access database
        DatabaseAccess access = new DatabaseAccess();
        access.setMother(this);
        access.setOperation(DatabaseAccess.GET_LISTS);
        access.execute(getIntent().getStringExtra("target"));
    }

    @Override
    public void receiveLists(String[] result) {
        for (String it: result) {
            controller.addChecklist(it);
        }
    }

    public void onClickButtonSave(View view) {
    }
}
