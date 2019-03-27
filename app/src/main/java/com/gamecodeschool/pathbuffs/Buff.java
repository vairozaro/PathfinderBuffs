package com.gamecodeschool.pathbuffs;

/**
 * Created by Jason on 5/5/2018.
 */

public class Buff
{
    private String name;
    private int bonus;
    private char type;
    private int rounds;
    private char bonusTo;

    public Buff(String n, char t, char bT, int b, int r)
    {
        name = n;
        type = t;
        bonusTo = bT;
        bonus = b;
        rounds = r;
    }

    public int getBonus()
    {
        return bonus;
    }
}
