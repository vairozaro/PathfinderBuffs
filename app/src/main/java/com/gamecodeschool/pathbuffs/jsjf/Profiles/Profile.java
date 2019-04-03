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

//This class manages the saved information for a profile.  It consists mainly of getters and setters
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
    //Add a feat to profiles feat list if it not already there
    public void addFeatToProfile(Feats f)
    {
        if(!feats.contain(f)) {
            feats.add(f);
            Collections.sort(feats, new Feats(){});
        }
    }
    //Add a ability to profiles ability list if it not already there
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

    //delete a feat from list
    public void removeFeatFromProfile(Feats f)
    {
        feats.remove(f);
    }
    //Checks to see if a feat is attached to a profile
    public boolean hasFeat(Feats f)
    {
        return feats.contain(f);
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
