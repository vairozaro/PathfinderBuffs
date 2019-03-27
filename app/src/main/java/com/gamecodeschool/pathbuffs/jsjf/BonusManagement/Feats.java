package com.gamecodeschool.pathbuffs.jsjf.BonusManagement;

import com.gamecodeschool.pathbuffs.jsjf.Enums;

import java.util.Comparator;

public class Feats implements Comparator<Feats> {

    public String name;
    public Enums.BonusTo bonusTo;
    public int bonus = 0;

    public Feats(){}

    @Override
    public int compare(Feats feats, Feats t1) {
        return feats.getName().compareTo(t1.getName());
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
