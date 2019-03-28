package com.gamecodeschool.pathbuffs.jsjf.BonusManagement;

import android.support.annotation.NonNull;

import com.gamecodeschool.pathbuffs.jsjf.Enums;

import java.util.ArrayList;
import java.util.Iterator;

//Class to manage class abilities.
public class AbilitiesManager implements Iterable<Abilities>{

    private static ArrayList<Abilities> allAbilitiesList;
    private static ArrayList<Abilities> activeAbilites = new ArrayList<>();
    private static final int attackBonus = 0;
    private static final int damageBonus = 1;


    public static ArrayList<Abilities> getAllAbilitiesList() {
        return allAbilitiesList;
    }
    public static ArrayList<Abilities> getActiveAbilites() {
        return activeAbilites;
    }

    public static void setAllAbilitiesList(ArrayList<Abilities> allAbilitiesList) {
        AbilitiesManager.allAbilitiesList = allAbilitiesList;
    }

    //Calculates bonus of abilites and return as an array
    public static int[] calculateBonus()
    {
        int[] bonuses = new int[]{0,0};

        if(activeAbilites.size() > 0)
        {
            for(Abilities x: activeAbilites)
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
                    case STATS:

                        break;
                    case OTHER:
                        int[] tempBonus = new int[2];
                        switch (x.name)
                        {
                            case "Weapon Training":
                                tempBonus = weaponTraining();
                                bonuses[attackBonus] = bonuses[attackBonus] + tempBonus[attackBonus];
                                bonuses[damageBonus] = bonuses[damageBonus] + tempBonus[damageBonus];
                                break;
                            case "Brawler's Flurry":
                                BuffManager.setSPEED(Enums.Speed.SPEED_ACTIVE);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;

                }
            }
        }

        return bonuses;
    }

    //Calculates Weapon Training
    public static int[] weaponTraining()
    {
        int tempLevel = BuffManager.getCasterLevel();
        int[] bonus = new int[]{0,0};

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

    //checks to see if the Ability is currently in the active abilities list
    public static boolean isAbiltiyActive(String s)
    {
        boolean isActive = false;
        int i = 0;
        while(!isActive && activeAbilites.size() > i)
        {
            if(s.equals(activeAbilites.get(i).name))
            {
                isActive = true;
            }
        }

        return isActive;
    }


    public static void addActiveAbility(String s)
    {
        Abilities a = new Abilities();
        boolean newAbility = true;

        for(Abilities y: activeAbilites)
        {
            if(y.getName().equals(s))
            {
                newAbility = false;
            }
        }

        if(newAbility) {
            for (Abilities x : allAbilitiesList) {
                if (x.getName().equals(s)) {
                    a = x;
                }
            }
            activeAbilites.add(a);
        }
    }
    public static void removeActiveAbiltiy(String s) {
        Abilities a = new Abilities();
        for(Abilities x: activeAbilites)
        {
            if(s.equals(x.getName()))
            {
                a = x;
            }
        }
        if(a != null) {
            activeAbilites.remove(a);
        }
    }
    @Override
    public Iterator<Abilities> iterator() {
        return allAbilitiesList.iterator();
    }
}
