package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.controller.AccessResultant;
import br.eng.crisjr.failproof.android.controller.Checklists;
import br.eng.crisjr.failproof.android.controller.DatabaseAccess;
import br.eng.crisjr.failproof.android.model.Checklist;

/**
 * Page describing a checklist. Must receive the target checklist as an extra.
 */
public class ListActivity extends Activity implements AccessResultant {
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
        Checklist checklist = new Checklist(result[0]);
        TextView textTitle = (TextView) findViewById(R.id.textTitle);
        TextView textList = (TextView) findViewById(R.id.textChecklist);

        textTitle.setText(checklist.getName());
        textList.setText("");
        for (String item: checklist.getStuff()) {
            textList.setText(textList.getText() + item + "\n");
        }
    }

    public void onClickButtonSave(View view) {
    }
}
