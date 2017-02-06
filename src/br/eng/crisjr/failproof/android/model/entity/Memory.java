package br.eng.crisjr.failproof.android.model.entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import br.eng.crisjr.failproof.android.model.entity.Checklist;

import java.util.ArrayList;
import java.util.List;

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
        String[] codes = retrieveAllCodes(context);

        if (codes != null) {
            outlet = new Checklist[codes.length];
            int i = 0;
            for (String code : codes) {
                outlet[i++] = new Checklist(preferences.getString(code, ""));
            }
        }

        return outlet;
    }

    /**
     * Gets the next free address
     * @param context The application's context
     * @return The next free address
     */
    public static String next(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String outlet = "";
        int index = 0;

        while ((outlet.length() == 0) && (index < 1000)) {
            String candidate = String.format("%03d", index);
            String data = preferences.getString(candidate, "");

            if (data.length() == 0) {
                outlet = candidate;
            }

            index++;
        }

        if (index == 1000) {
            throw new IllegalStateException("No more memory!");
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
        store(context, Memory.next(context), checklist);
    }

    /**
     * Stores a checklist on memory under its position on memory
     *
     * @param context   The application's context
     * @param checklist The checklist object to be saved
     */
    public static void store(Context context, String address, Checklist checklist) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(address, checklist.toString());
        editor.commit();
        editor.apply();
    }

    /**
     * Deletes everything in memory
     *
     * @param context The application's context
     */
    public static void resetMemory(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * Retrieve all valid addresses to lists
     *
     * @param context The application's context
     * @return An array with all valid addresses
     */
    public static String[] retrieveAllCodes(Context context) {
        List<String> stuff = new ArrayList<>();
        String[] outlet = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        for (int i = 0; i < 1000; ++i) {
            String code = String.format("%03d", i);
            String raw = preferences.getString(code, "");
            if (raw.length() > 0) {
                stuff.add(code);
            }
        }

        if (stuff.size() > 0) {
            outlet = stuff.toArray(new String[stuff.size()]);
        }

        return outlet;
    }

    /**
     * Retrieve a checklist from memory
     *
     * @param context The application's context
     * @param code    The list address
     * @return The chosen checklist
     */
    public static Checklist retrieveList(Context context, String code) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String raw = preferences.getString(code, "");
        Checklist outlet = null;

        if (raw.length() > 0) {
            outlet = new Checklist(raw);
        }

        return outlet;
    }

    /**
     * Removes data from memory
     *
     * @param context The application's context
     * @param code    The list address
     */
    public static void remove(Context context, String code) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(code);
        editor.commit();
        editor.apply();
    }
}
