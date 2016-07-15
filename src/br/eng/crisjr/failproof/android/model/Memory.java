package br.eng.crisjr.failproof.android.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Class to edit memory information
 */
public class Memory
{
    public static Checklist[] retrieveAllLists(Context context)
    {
        Checklist[] outlet = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int size = preferences.getInt("size", 0);

        if (size > 0) {
            // TODO Load checklists
        }

        return outlet;
    }

    public static void store(Context context, Checklist checklist)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        int size = preferences.getInt("size", 0);

        // TODO save checklist

        editor.putInt("size", size + 1);
        editor.commit();
    }
}
