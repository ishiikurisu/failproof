package br.eng.crisjr.failproof.android.controller;

/**
 * Created by cris on 16/07/2016.
 */

import android.os.AsyncTask;
import br.eng.crisjr.failproof.web;

/**
 * Class to simplify access to failproof's database through another
 */
public class SpecificAccess extends AsyncTask<String, Void, String> {
    private SpecificResultant mother;

    public SpecificAccess(SpecificResultant mother)
    {
        this.mother = mother;
    }

    @Override
    protected String doInBackground(String... params) {
        return web.getList(params[0]);
    }

    protected void onPostExecute(String result) {
        mother.receiveList(result);
    }
}