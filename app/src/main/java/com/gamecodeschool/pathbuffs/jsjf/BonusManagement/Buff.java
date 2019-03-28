package com.gamecodeschool.pathbuffs.jsjf.BonusManagement;

import com.gamecodeschool.pathbuffs.jsjf.Enums;

import java.util.Comparator;

/**
 * Created by Jason on 5/5/2018.
 */

public class Buff implements Comparator<Buff>
{
    private String name;
    private int bonus;
    private Enums.Type type;
    private Enums.BonusTo bonusTo;
    private int rounds;
    private Enums.Scaling scaling;
    public Enums.Speed speed;

    private String btnID;

    public Buff()
    {

    }

    public Buff(String n, Enums.Type t, Enums.BonusTo bt, int b, int r, Enums.Scaling scaling, Enums.Speed speed)
    {
        name = n;
        type = t;
        bonus = b;
        bonusTo = bt;
        rounds = r;
        this.scaling = scaling;
        this.speed = speed;
    }

    public Enums.BonusTo getBonusTo() {
        return bonusTo;
    }
    public int getBonus()
    {
        int tempBonus = bonus;
        int level = BuffManager.getCasterLevel();
        switch (scaling)
        {
            case NO_SCALING:
                tempBonus = bonus;
                break;
            case ONE_PER_THREE_MAX_THREE:
                if(level < 6)
                {
                    tempBonus = bonus;
                }else if(level < 9)
                {
                    tempBonus = bonus *2;
                }else
                {
                    tempBonus = bonus *3;
                }
                break;
            default:
                tempBonus = bonus;
                break;
        }

        return tempBonus;
    }
    public String getName() { return name;}
    public int getRounds(){return rounds;}
    public Enums.Type getType() {
        return type;
    }

    //display rounds in string
    public String getRoundsString()
    {
        String roundDisplay;
        int tempRounds = 0;
        if(rounds >= 600)
        {
            tempRounds = rounds / 600;
            roundDisplay = Integer.toString(tempRounds) + " hours";
        }
        else if(rounds >= 10)
        {
            tempRounds = rounds / 10;
            roundDisplay = Integer.toString(tempRounds) + " minutes";
        }
        else if(rounds > 1)
        {
            roundDisplay = Integer.toString(rounds) + " rounds";
        }
        else
        {
            roundDisplay = Integer.toString(rounds) + " round";
        }

        return roundDisplay;
    }

    public void removeRounds(int timePass)
    {
        int temp = rounds - timePass;
        rounds = temp;
    }

    //Buffs are organized by least about of rounds remaining then by alphabetical 
    public int compare(Buff buff1, Buff buff2) {
        int bigger = 0;
        if(buff1.getRounds() == buff2.getRounds())
        {
            bigger = buff1.getName().compareTo(buff2.getName());
        }
        else if(buff1.getRounds() > buff2.getRounds())
        {
            bigger = 1;
        }
        else
        {
            bigger = -1;
        }
        return bigger;
    }
}
