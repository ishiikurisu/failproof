package br.eng.crisjr.failproof.android.model.system;

import android.os.AsyncTask;
import br.eng.crisjr.failproof.web;

/**
 * Class to simplify access to failproof's database through another
 */
public class DatabaseAccess extends AsyncTask<String, Void, String[]> {
    public static final int GET_LINKS = 0;
    public static final int GET_LISTS = 1;
    public static final int GET_STUFF = 2;
    public static final int GET_LIST = 3;
    private AccessResultant mother;
    private int operation = GET_LINKS;

    /**
     * Created a new database access object.
     *
     * @param mother The object that will call the download task.
     */
    public DatabaseAccess(AccessResultant mother) {
        this.mother = mother;
    }

    /**
     * Defines which download operation is desired.
     * @param operation The code to one of the available operations (GET_LINKS, GET_LISTS, GET_STUFF and GET_LIST).
     * @return The chosen operation.
     */
    public int setOperation(int operation) {
        this.operation = operation;
        return this.operation;
    }

    @Override
    protected String[] doInBackground(String... params) {
        String[] outlet = null;

        switch (operation) {
            case GET_LINKS:
                outlet = web.getLinks();
                break;

            case GET_LISTS:
                outlet = web.getLists();
                break;
            case GET_STUFF:
                outlet = web.getStuff();
                break;

            case GET_LIST:
                outlet = web.getList(params[0]).split("\n");
                break;
        }

        return outlet;
    }

    protected void onPostExecute(String[] result) {
        mother.receiveLists(result);
    }
}