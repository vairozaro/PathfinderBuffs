package com.gamecodeschool.pathbuffs.jsjf.BonusManagement;

import com.gamecodeschool.pathbuffs.jsjf.Enums;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

//Class to manage buffs
public class Spells implements Comparator<Spells>
{

    public String name;
    public Enums.Duration duration;
    public Enums.BonusTo bonus_to;
    public int bonus = 0;
    public Enums.Scaling scaling = Enums.Scaling.NO_SCALING;
    public Enums.Type type;
    public Enums.Attribute attribute;
    public Enums.Speed speed = Enums.Speed.NO_SPEED;
    public EnumSet<Enums.ClassList> classLists = EnumSet.noneOf(Enums.ClassList.class);

    public String getName() {
        return name;
    }
    @Override
    public int compare(Spells spell, Spells spell2) {

        return spell.getName().compareTo(spell2.getName());
    }


    public Enums.BonusTo getBonus_to() {
        return bonus_to;
    }
    public Enums.Type getType() {
        return type;
    }
    //Determines the duration of each spell
    public int getDuration()
    {
        int rounds;
        switch (duration)
        {
            case ONE_ROUND_A_LEVEL:
                rounds = BuffManager.getCasterLevel();
                break;
            case ONE_MINUTE:
                rounds = 10;
                break;
            case MIN_A_LEVEL:
                rounds = BuffManager.getCasterLevel() * 10;
                break;
            case TEN_MIN_A_LEVEL:
                rounds = BuffManager.getCasterLevel() * 100;
                break;
            case HOUR_A_LEVEL:
                rounds = BuffManager.getCasterLevel() * 600;
            default:
                rounds = 1;
                break;
        }

        return rounds;
    }
    //Returns a bonus based on if it scales
    public int calculateScalingBonus()
    {
        int tempBonus;
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

    //Checks to see if a spell is on a class list
    public boolean isOnClassList(Enums.ClassList c)
    {
        boolean onList = false;

        for (Enums.ClassList x: classLists)
        {
            if (x == c)
            {
                onList = true;
            }
        }

        return onList;
    }
    public EnumSet<Enums.ClassList> getClassLists() {
        return classLists;
    }

}
