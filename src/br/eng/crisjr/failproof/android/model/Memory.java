package br.eng.crisjr.failproof.android.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Class to edit memory information
 */
public class Memory
{
    /**
     * Retrieve all lists in memory
     *
     * @param context The application's context
     * @return an array of every checklist on memory
     */
    public static Checklist[] retrieveAllLists(Context context)
    {
        Checklist[] outlet = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int size = preferences.getInt("size", 0);

        if (size > 0) {
            outlet = new Checklist[size];
            for (int i = 0; i < size; ++i) {
                String raw = preferences.getString(String.format("%03d", i), "");
                if (raw.length() > 0) {
                    outlet[i] = new Checklist(raw);
                }
            }
        }

        return outlet;
    }

    /**
     * Stores a checklist on memory under its position on memory
     * @param context The application's context
     * @param checklist The checklist object to be saved
     */
    public static void store(Context context, Checklist checklist)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        int size = preferences.getInt("size", 0);
        editor.putString(String.format("%03d", size), checklist.toString());
        editor.putInt("size", size + 1);
        editor.commit();
        editor.apply();
    }

    public static void resetMemory(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
    }
}
