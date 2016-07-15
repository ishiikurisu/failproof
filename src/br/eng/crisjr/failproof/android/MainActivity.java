package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.controller.*;

public class MainActivity
extends Activity
implements AccessResultant {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DatabaseAccess access = new DatabaseAccess();
        access.setMother(this);
        access.execute();
    }

    public void receiveLists(String[] result) {
        TextView tv = (TextView) findViewById(R.id.textHello);
        tv.setText(result[0]);
    }
}
