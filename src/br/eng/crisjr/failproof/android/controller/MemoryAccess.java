package br.eng.crisjr.failproof.android.controller;

import android.content.Context;
import br.eng.crisjr.failproof.android.model.*;

/**
 * Access to phone's memory, looking for data
 */
public class MemoryAccess
{
    private Context context;

    public MemoryAccess(Context context)
    {
        this.context = context;
    }

    public String[] loadLists()
    {
        Checklist[] lists = Memory.retrieveAllLists(context);
        String[] outlet = null;

        // TODO Turn lists into Strings
        // TODO Add lists to outlet

        return outlet;
    }
}
