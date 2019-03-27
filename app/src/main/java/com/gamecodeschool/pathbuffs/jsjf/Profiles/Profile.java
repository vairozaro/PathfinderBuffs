package com.gamecodeschool.pathbuffs.jsjf.Profiles;

import android.content.Context;
import android.widget.Toast;

import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Abilities;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.AbilitiesManager;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.FeatManager;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Feats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Profile implements Comparator<Profile>{

    String name;
    int level;
    char selected;
    int bab;
    public int attirbute[] = new int[6];

    private ArrayList<Feats> feats = new ArrayList<>();
    private ArrayList<Abilities> abilities = new ArrayList<>();

    public Profile(String n, int[] pro, int l, int b, char s)
    {
        name = n;
        level = l;
        bab = b;
        selected = s;
        for(int i = 0; i < attirbute.length; i++)
        {
            attirbute[i] = pro[i];
        }
    }
    public int getLevel() {
        return level;
    }

    public int getBab() {
        return bab;
    }
    public String getName() {
        return name;
    }

    public int[] getAttirbute() {
        return attirbute;
    }

    public char getSelected() {
        return selected;
    }

    public void setSelected(char selected) {
        this.selected = selected;
    }
    public void addFeatToProfile(String s)
    {
        Feats f = new Feats();
        boolean newFeat = true;

        for(Feats y: feats)
        {
            if(y.getName().equals(s))
            {
                newFeat = false;
            }
        }

        if(newFeat) {
            for (Feats x : FeatManager.getAllFeatsList()) {
                if (x.getName().equals(s)) {
                    f = x;
                }
            }
            feats.add(f);
            Collections.sort(feats, new Feats(){});
        }
    }
    public void addAbilityToProfile(String s)
    {
        Abilities a = new Abilities();
        boolean newAbility = true;

        for(Feats y: feats)
        {
            if(y.getName().equals(s))
            {
                newAbility = false;
            }
        }

        if(newAbility) {
            for (Abilities x: AbilitiesManager.getAllAbilitiesList()) {
                if (x.getName().equals(s)) {
                    a = x;
                }
            }
            abilities.add(a);
            Collections.sort(abilities, new Abilities(){});
        }
    }

    public void removeFeatFromProfile(String s)
    {
        Feats f = new Feats();
        for (Feats x: feats) {
            if (s == x.getName())
            {
                f = x;
            }
        }
        feats.remove(f);
    }
    public boolean hasFeat(String n)
    {
        boolean hasfeat = false;

        for (Feats x: feats)
        {
            if(x.getName().equalsIgnoreCase(n))
            {
                hasfeat = true;
            }
        }

        return hasfeat;
    }

    public ArrayList<Feats> getFeats() {
        return feats;
    }
    public ArrayList<Abilities> getAbilities() {
        return abilities;
    }

    @Override
    public int compare(Profile profile1, Profile profile2) {
        return profile1.getName().compareTo(profile2.getName());
    }
}
