package com.gamecodeschool.pathbuffs.jsjf.BonusManagement;

import com.gamecodeschool.pathbuffs.jsjf.Enums;
import java.util.ArrayList;
import java.util.Iterator;

public class FeatManager implements Iterable<Feats>
{
    private static ArrayList<Feats> allFeatsList;
    private static ArrayList<Feats> activeFeats = new ArrayList<>();
    private static final int attackBonus = 0;
    private static final int damageBonus = 1;

    //Calculates bonus of all active feats
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

    //Calculate the bonus of the feats
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

    //Adds a feat to active feats.  First checks if it is already there.
    public static void addActiveFeat(Feats f)
    {
        if(!activeFeats.contains(f))
        {
            activeFeats.add(f);
        }
    }
    public static void removeActiveFeat(Feats f)
    {
        activeFeats.remove(f);
    }
    //setter
    public static void setAllFeatsList(ArrayList<Feats> allFeatsList) { FeatManager.allFeatsList = allFeatsList; }
    //getter
    public static ArrayList<Feats> getAllFeatsList() { return allFeatsList; }
    //Return the feat when given just the name.  Used to load feats from a text document.
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

    public Iterator<Feats> iterator() { return activeFeats.iterator(); }

}
