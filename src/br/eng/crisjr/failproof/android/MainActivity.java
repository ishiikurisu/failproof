package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import br.eng.crisjr.failproof.web;

public class MainActivity extends Activity {
    private final String SOURCE = "http://failproofchecklist.tumblr.com";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        new DatabaseAccess().execute(SOURCE);
    }

    private class DatabaseAccess extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            return web.process(params[0]);
        }

        protected void onPostExecute(String[] result) {
            updateLists(result);
        }
    }

    private void updateLists(String[] lists) {
        TextView tv = (TextView) findViewById(R.id.textEmpty);
        tv.setText("");
        for (String it: lists) {
            tv.setText(tv.getText() + it + "\n");
        }
    }
}
