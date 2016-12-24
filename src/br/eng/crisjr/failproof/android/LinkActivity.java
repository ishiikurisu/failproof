package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import br.eng.crisjr.failproof.android.controller.LinkController;
import br.eng.crisjr.failproof.android.model.LinkModel;
import br.eng.crisjr.failproof.android.model.system.AccessResultant;
import br.eng.crisjr.failproof.android.model.system.DatabaseAccess;
import br.eng.crisjr.failproof.android.view.LinkView;

/**
 * Activity to display a list which can be saved to memory or not
 */
public class LinkActivity extends Activity {
    LinkModel model = null;
    LinkView view = null;
    LinkController controller = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link);

        // MVC
        String link = getIntent().getStringExtra("link");
        model = new LinkModel(link);
        view = new LinkView(this);
        controller = new LinkController(model, view);

        // Download list
        controller.downloadChecklist();
    }

    /**
     * Cancels the operation of saving the list and returns to the search screen.
     * @param view I still don't know why this is here. I guess this is the button?
     */
    public void onClick_buttonCancel(View view) {
        setResult(LinkView.CANCEL_PLEASE);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(LinkView.CANCEL_PLEASE);
        super.onBackPressed();
    }


    /**
     * Saves the current list on memory then returns to the main screen.
     *
     * @param v Ignoring this
     */
    public void onClick_buttonSave(View v) {
        // TODO Save checklist
        controller.saveChecklist();
    }

}