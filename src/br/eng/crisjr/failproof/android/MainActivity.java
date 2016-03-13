package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.controller.AccessResultant;
import br.eng.crisjr.failproof.android.controller.Checklists;
import br.eng.crisjr.failproof.android.controller.DatabaseAccess;
import br.eng.crisjr.failproof.android.model.Constants;

public class MainActivity extends Activity implements AccessResultant {
    private Checklists controller = new Checklists();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DatabaseAccess access = new DatabaseAccess();
        access.setMother(this);
        access.execute(Constants.SOURCE);
    }

    public void receiveLists(String[] result) {
        LinearLayout layoutLists = (LinearLayout) findViewById(R.id.layoutLists);

        layoutLists.removeAllViews();
        for (String it: result)
            controller.addChecklist(it);
        for (String it: controller.getStuff())
            layoutLists.addView(createButtonForTarget(it));
    }

    private Button createButtonForTarget(String it) {
        Button button = new Button(getApplicationContext());
        String target = controller.query(it)[0];

        button.setText(it);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(target);
            }
        });

        return button;
    }


    public void openPage(String target) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("target", target);
        startActivityForResult(intent, 0);
    }
}
