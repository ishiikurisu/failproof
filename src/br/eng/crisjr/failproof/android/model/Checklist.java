package br.eng.crisjr.failproof.android.model;

/**
 * Class to model checklists
 */
public class Checklist
{
    private String title;
    private String[] items;

    public Checklist()
    {

    }

    public String toString()
    {
        String outlet = title;

        for (String item: items)
            outlet += "\n" + item;

        return outlet;
    }
}
