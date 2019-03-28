package com.gamecodeschool.pathbuffs.jsjf.BonusManagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.gamecodeschool.pathbuffs.jsjf.Enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class FeatManager implements Iterable<Feats>
{
    private static ArrayList<Feats> allFeatsList;
    private static ArrayList<Feats> activeFeats = new ArrayList<>();
    private static final int attackBonus = 0;
    private static final int damageBonus = 1;

    public static int[] calculateFeatBonus()
    {
        int[] bonuses = new int[]{0,0};

        if (activeFeats.size() > 0)
        {
            for(Feats x: activeFeats)
            {
                switch (x.bonusTo)
                {
                    case ATTACK:
                        bonuses[attackBonus] = bonuses[attackBonus] + x.bonus;
                        break;
                    case DAMAGE:
                        bonuses[damageBonus] = bonuses[damageBonus] + x.bonus;
                        break;
                    case ATTACK_AND_DAMAGE:
                        bonuses[attackBonus] = bonuses[attackBonus] + x.bonus;
                        bonuses[damageBonus] = bonuses[damageBonus] + x.bonus;
                        break;
                        //IF a feat does not have the same attack and damage bonus
                        //Use other and get the bonus of that.
                    case OTHER:
                        int[] tempBonus = new int[]{0,0};
                        //Also used for Deadly Aim and Piranhe Strike
                        if (x.name.equals("Power Attack"))
                        {
                            tempBonus = powerAttack();
                            bonuses[attackBonus] = bonuses[attackBonus] + tempBonus[attackBonus];
                            bonuses[damageBonus] = bonuses[damageBonus] + tempBonus[damageBonus];
                        }
                        if(x.getName().equals("Weapon Training"))
                        {
                            tempBonus = weaponTraining();
                            bonuses[attackBonus] = bonuses[attackBonus] + tempBonus[attackBonus];
                            bonuses[damageBonus] = bonuses[damageBonus] + tempBonus[damageBonus];
                        }
                        break;
                    default:
                        break;

                }
            }
        }

        return bonuses;
    }

    public static int[] powerAttack()
    {
        int bab = BuffManager.getBab();
        int[] bonus = new int[]{0,0};
        int additionalBonus;

        //Decide bonus based on attack style
        if(BuffManager.getStyleOfAttack() == Enums.StyleOfAttack.TWO_HANDED)
        {
            additionalBonus = 3;
        }
        else
        {
            additionalBonus = 2;
        }
        do {
            bonus[attackBonus] = bonus[attackBonus] - 1;
            bonus[damageBonus] = bonus[damageBonus] + additionalBonus;
            bab = bab - 4;
        }while(bab >= 0);


        return bonus;
    }
    private static int[] weaponTraining ()
    {
        int[] bonus = new int[]{0,0};
        int tempLevel = BuffManager.getCasterLevel();

        do{
            if(tempLevel > 4)
            {
                bonus[attackBonus]++;
                bonus[damageBonus]++;
                tempLevel = tempLevel - 4;
            }

        }while (tempLevel > 4);

        return bonus;
    }

    //Checks if feat is currently an active feat adding a bonus.  Used to display
    //The toggle button on if it is true
    public static boolean isFeatActive(String s)
    {
        boolean isActive = false;
        int i = 0;
        while(!isActive && activeFeats.size() > i)
        {
            if(s.equals(activeFeats.get(i).name))
            {
                isActive = true;
            }
        }

        return isActive;
    }

    //Adds a feat to active feats.  First checks if it is already there.
    public static void addActiveFeat(String s)
    {
        Feats f = new Feats();
        boolean newFeat = true;

        for(Feats y: activeFeats)
        {
            if(y.getName().equals(s))
            {
                newFeat = false;
            }
        }

        if(newFeat) {
            for (Feats x : allFeatsList) {
                if (x.getName().equals(s)) {
                    f = x;
                }
            }
            activeFeats.add(f);
        }
    }
    public static void addActiveFeat(Feats f)
    {
        activeFeats.add(f);
    }
    public static void removeActiveFeat(Feats f)
    {
        activeFeats.remove(f);
    }
    //setter
    public static void setAllFeatsList(ArrayList<Feats> allFeatsList) {
        FeatManager.allFeatsList = allFeatsList;
    }

    //getter
    public static ArrayList<Feats> getAllFeatsList() {
        return allFeatsList;
    }

    public static Feats getFeat(String name)
    {
        Feats f = new Feats();

        for(Feats x: allFeatsList)
        {
            if(x.getName().equals(name))
            {
                f = x;
            }
        }
        return f;
    }
    @NonNull
    @Override
    public Iterator<Feats> iterator() {
        return activeFeats.iterator();
    }

    public static void removeActiveFeat(String s) {
        Feats f = new Feats();
        for(Feats x: activeFeats)
        {
            if(s.equals(x.getName()))
            {
                f = x;
            }
        }
        if(f != null) {
            activeFeats.remove(f);
        }
    }
}
