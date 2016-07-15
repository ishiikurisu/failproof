package br.eng.crisjr.failproof.android.controller;

import android.os.AsyncTask;
import br.eng.crisjr.failproof.web;

/**
 * Class to simplify access to failproof's database through another
 */
public class DatabaseAccess extends AsyncTask<String, Void, String[]> {
    public static final int GET_LINKS = 0;
    public static final int GET_LISTS = 1;
    private AccessResultant mother;
    private int operation = GET_LINKS;

    public AccessResultant setMother(AccessResultant mother) {
        this.mother = mother;
        return this.mother;
    }

    public int setOperation(int operation) {
        this.operation = operation;
        return this.operation;
    }

    @Override
    protected String[] doInBackground(String... params) {
        String[] outlet = null;

        switch (operation) {
            case GET_LINKS:
                outlet = web.getLinks(params[0]);
                break;

            case GET_LISTS:
                outlet = web.getLists(params[0]);
                break;
        }

        return outlet;
    }

    protected void onPostExecute(String[] result) {
        mother.receiveLists(result);
    }
}