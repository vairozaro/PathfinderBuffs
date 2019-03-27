package com.gamecodeschool.pathbuffs.jsjf.BonusManagement;

import com.gamecodeschool.pathbuffs.jsjf.Enums;

import java.util.Comparator;

public class Abilities implements Comparator<Abilities>
{
    public String name;
    public int bonus = 0;
    public Enums.BonusTo bonusTo;
    public Enums.Type type;

    public int compare(Abilities ability, Abilities t1) {
        return ability.getName().compareTo(t1.getName());
    }
    public String getName() {
        return name;
    }
}
