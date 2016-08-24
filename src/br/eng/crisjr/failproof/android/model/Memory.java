package br.eng.crisjr.failproof.android.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

    /**
     * Stores a checklist on memory under its position on memory
     *
     * @param context   The application's context
     * @param checklist The checklist object to be saved
     */
    public static void store(Context context, String address, Checklist checklist) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(String.format(address), checklist.toString());
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
        int size = preferences.getInt("size", 0);

        if (size > 0) {
            for (int i = 0; i < size; ++i) {
                String code = String.format("%03d", i);
                String raw = preferences.getString(code, "");
                if (raw.length() > 0) {
                    stuff.add(code);
                }
            }
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
}
