package com.gamecodeschool.pathbuffs.jsjf.BonusManagement;

import android.support.annotation.NonNull;


import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentAttack;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.AllProfiles;
import com.gamecodeschool.pathbuffs.jsjf.Enums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Jason on 5/5/2018.
 */

//This class manages all the Spells class and Buff class along with calculating the total bonus
public class BuffManager implements Iterable<Buff>
{
    private static ArrayList<Spells> allSpellsList;
    private static ArrayList<Spells> favoriteSpells = new ArrayList<>();
    private static List<Buff> buffList = new LinkedList<>();
    private static List<Buff> attackBuffList = new LinkedList<>();
    private static List<Buff> damageBuffList = new LinkedList<>();
    private static List<Buff> statsBuffList = new LinkedList<>();
    private static int ability = 0;
    private static final int attackBonus = 0;
    private static final int damageBonus = 1;

    private static int weaponEnhancement = 0;
    private static Enums.StyleOfAttack styleOfAttack = Enums.StyleOfAttack.ONE_HANDED;
    private static Enums.Speed SPEED = Enums.Speed.NO_SPEED;
    private static Enums.AmountOfWeapons WEAPONS = Enums.AmountOfWeapons.ONE_WEAPON;
    static int size = 0;

    static int casterLevel = 10;
    static int bab = 10;

    //Add new spells to master list and the type it is to attack or damage list
    public static void addBuff(Buff b)
    {
        buffList.add(b);
        size++;
        Collections.sort(buffList, new Buff() {});
        switch(b.getBonusTo())
        {
            case ATTACK:
                attackBuffList.add(b);
                break;
            case DAMAGE:
                damageBuffList.add(b);
                break;
            case STATS:
                statsBuffList.add(b);
                break;
            case ATTACK_AND_DAMAGE:
                attackBuffList.add(b);
                damageBuffList.add(b);
                break;
            default:
                break;
        }
    }
    //Finds the spell and adds it as a buff
    public static void castSpell(String n)
    {
        Spells s = new Spells();
        for(Spells x: allSpellsList)
        {
            if(x.getName().equals(n))
            {
                s = x;
            }
        }
        addBuff(new Buff(s.name, s.type, s.bonus_to, s.bonus, s.getDuration(), s.scaling, s.speed));
    }
    //Add a spell to the favorites list
    public static void addSpellToFavorites(String n)
    {
        if(!favoritesContainsSpells(n)) {
            for (Spells s : allSpellsList) {
                if (s.getName().equals(n)) {
                    favoriteSpells.add(s);
                }
            }
        }
    }
    public static void removeSpellFromFavorites(String n)
    {
        Spells s = new Spells();
        for(Spells x: favoriteSpells)
        {
            if(x.getName().equals(n))
            {
                s = x;
            }
        }
        favoriteSpells.remove(s);
    }
    //Check to see if a spell is on the favorites list
    public static boolean favoritesContainsSpells(String n)
    {
        boolean contains = false;
        for (Spells s: favoriteSpells)
        {
            if(s.getName().equals(n))
            {
                contains = true;
            }
        }
        return contains;
    }
    //Check to see if a buff is on the active list
    public static boolean isBuffActive(String n)
    {
        boolean activeBuff = false;

        for (Buff x: buffList)
        {
            if(x.getName().equals(n))
            {
                activeBuff = true;
            }
        }

        return activeBuff;
    }
    //Removes a buff from the master bufflist and from the attack or damage list it is on.
    public static void dispelBuff(Buff b)
    {
        if(b != null) {
            buffList.remove(b);
            switch(b.getBonusTo())
            {
                case ATTACK:
                    attackBuffList.remove(b);
                    break;
                case DAMAGE:
                    damageBuffList.remove(b);
                    break;
                case STATS:
                    statsBuffList.remove(b);
                    break;
                case ATTACK_AND_DAMAGE:
                    attackBuffList.remove(b);
                    damageBuffList.remove(b);
                    break;
                default:
                    attackBuffList.remove(b);
                    break;
            }
        }
        size--;
    }
    //On round button clicked it will remove the rounds.  If the rounds are <=0 remove the spell
    public static void removeRound(int timePass)
    {
        List<Buff> tempList = new LinkedList<Buff>();
        for(Buff x: buffList)
        {
            x.removeRounds(timePass);
        }
        for(Buff x: buffList)
        {
            if(x.getRounds() <= 0)
            {
                tempList.add(x);
            }
        }
        if(tempList.size() > 0)
        {
            for(Buff x: tempList)
            {
                dispelBuff(x.getName());
            }
        }
    }

    //Get the bonus of the Spells that are currently cast
    public static int addSpellBonus(Enums.BonusTo bonusTo)
    {
        int bonus = 0;
        if(buffList.isEmpty())
        {
            return 0;
        }else
        {
            switch (bonusTo)
            {
                case ATTACK:
                    bonus = addSpellBonusX(attackBuffList);
                    break;
                case DAMAGE:
                    bonus = addSpellBonusX(damageBuffList);
                    break;
                case STATS:
                    bonus = addSpellBonusX(statsBuffList);
                    break;
                default:
                    bonus = 0;
                    break;
            }
        }

        return bonus;
    }
    //Helper class for addSpellBonus
    private static int addSpellBonusX(List<Buff> attackOrDamageList)
    {
        Buff tempBuff;
        int tempBonus;
        int bonus = 0;
        List<Buff> tempBonusList = new LinkedList<Buff>();
        List<Buff> tempList = new LinkedList<Buff>();
        tempList.addAll(attackOrDamageList);

        while(!tempList.isEmpty())
        {
            tempBuff = tempList.get(0);
            tempBonus = tempBuff.getBonus();

            for (Buff x : tempList) {
                if(tempBuff.getType() == x.getType())
                {
                    tempBonusList.add(x);
                }
            }
            if(tempBonusList.size() >= 2)
            {
                for(Buff x: tempBonusList)
                {
                    tempBonus = ((x.getBonus() > tempBonus) ? x.getBonus() : tempBonus);
                }
            }
            tempList.removeAll(tempBonusList);
            tempBonusList.clear();

            bonus = bonus + tempBonus;
        }

        return bonus;
    }

    //calculates that is displayed on the TabFragmentAttack class
    public static void calculateBonus()
    {
        int[] bonus = new int[]{0,0};

        int strMod = getModifier(Enums.Attribute.STRENGTH);
        int dexMod = getModifier(Enums.Attribute.DEXTERITY);
        int[] featBonus = FeatManager.calculateFeatBonus();
        int[] abilitiesBonus = AbilitiesManager.calculateBonus();

        //Add bonuses from spells, feats, and abilities
        bonus[attackBonus] = addSpellBonus(Enums.BonusTo.ATTACK) + featBonus[attackBonus] + abilitiesBonus[attackBonus] + bab;
        bonus[damageBonus] = addSpellBonus(Enums.BonusTo.DAMAGE) + featBonus[damageBonus] + abilitiesBonus[attackBonus];

        //Depending on the style of attack add the right attribute to attack and damage
        switch (styleOfAttack)
        {
            case ONE_HANDED:
                bonus[attackBonus] = bonus[attackBonus] + strMod;
                bonus[damageBonus] = bonus[damageBonus] + strMod;
                break;
            case TWO_HANDED:
                bonus[attackBonus] = bonus[attackBonus] + strMod;
                bonus[damageBonus] = bonus[damageBonus] + strMod + (strMod/2);
                break;
            case RANGED:
                bonus[attackBonus] = bonus[attackBonus] + dexMod;
                break;
            case THROWN:
                bonus[attackBonus] = bonus[attackBonus] + dexMod;
                bonus[damageBonus] = bonus[damageBonus] + strMod;
                break;
            default:
                bonus[attackBonus] = bonus[attackBonus] + strMod;
                bonus[damageBonus] = bonus[damageBonus] + strMod;
                break;
        }
        //Add enhancement bonus
        if (weaponEnhancement == 99) {
            bonus[attackBonus] = bonus[attackBonus] + 1;
        }else {
            bonus[attackBonus] = bonus[attackBonus] + weaponEnhancement;
            bonus[damageBonus] = bonus[damageBonus] + weaponEnhancement;
        }
        //If a spell has the enum Speed change Speed in this class
        for(Buff b: buffList)
        {
            if(b.speed == Enums.Speed.SPEED_ACTIVE)
            {
                SPEED = Enums.Speed.SPEED_ACTIVE;
            }
        }

        //set the textviews with that attack and damage
        TabFragmentAttack.changeTextViews(attackToString(bonus[attackBonus]), bonus[damageBonus]);

    }
    //Displays the attack as a string
    private static String attackToString(int Bonus)
    {
        String bonusText = "";
        int speedBonus = 0;
        int TempBonus = Bonus;
        int tempBab = bab;
        int BrawlerFlurry = bab;

        //If using two weapons then modify the style of attack that is displayed
        if(WEAPONS == Enums.AmountOfWeapons.TWO_WEAPONS)
        {
            if (styleOfAttack == Enums.StyleOfAttack.TWO_HANDED)
            {
                TempBonus = TempBonus - 4;
            }
            else
            {
                TempBonus = TempBonus - 2;
            }
        }

        //Create the string of attack values
        speedBonus = TempBonus;
        do {

            bonusText = bonusText + Integer.toString(TempBonus);
            //IF two weapon fighting display the addition attack number
            if(WEAPONS == Enums.AmountOfWeapons.TWO_WEAPONS)
            {
                if(BrawlerFlurry > 0)
                {
                    bonusText = bonusText + " / " + TempBonus;
                    BrawlerFlurry = BrawlerFlurry - 7;
                }

            }

            tempBab = tempBab - 5;
            TempBonus = TempBonus - 5;

            if(tempBab > 0)
            {
                bonusText = bonusText + " / ";
            }

        }while (tempBab > 0);
        //IF speed is active add an addition attack at highest attack value
        if(SPEED == Enums.Speed.SPEED_ACTIVE)
        {
            bonusText = bonusText + " / " + speedBonus;
        }
        //Reset speed to default
        SPEED = Enums.Speed.NO_SPEED;

        return bonusText;
    }
    @NonNull
    @Override
    public Iterator<Buff> iterator() {
        return buffList.iterator();
    }

    //returns a list of all spells on any given class list
    public static List<Spells> getClassSpellList(Enums.ClassList c)
    {
        ArrayList<Spells> spellList = new ArrayList<>();
        for(Spells x: allSpellsList)
        {
            if(x.classLists.contains(c))
            {
                spellList.add(x);
            }
        }
        return spellList;
    }

//Getters
    public static List<Buff> getBuffList()
    {
        return buffList;
    }
    public static int getBab() { return bab;}
    public static int getCasterLevel()
    {
        return casterLevel;
    }
    public static ArrayList<Spells> getAllSpellsList() {
        return allSpellsList;
    }
    public static Enums.StyleOfAttack getStyleOfAttack() {
        return styleOfAttack;
    }
    //Gets the modifier for any particular ability score
    public static int getModifier(Enums.Attribute stat)
    {
        int abilityScore = AllProfiles.currentProfile.attirbute[stat.getValue()];

        abilityScore = abilityScore + addSpellBonus(Enums.BonusTo.STATS);


        int modifier = abilityScore - 10;
        if(modifier < 0)
        {
            modifier = modifier - 1;
        }
        return modifier /2;

    }
    //Get any given spell
    public static Spells getSpell(String name)
    {
        Spells s = new Spells();

        for(Spells x: allSpellsList)
        {
            if(x.getName().equals(name))
            {
                s = x;
            }
        }
        return s;
    }
//Setters
    public static void setCasterLevel(int casterLevel) {
        BuffManager.casterLevel = casterLevel;
    }
    public static void setBab(int bab) {
        BuffManager.bab = bab;
    }
    public static void setAllSpellsList(ArrayList<Spells> list)
    {
        allSpellsList = list;
    }
    public static void setSPEED(Enums.Speed SPEED) {
        BuffManager.SPEED = SPEED;
    }
    public static void setStyleOfAttack(Enums.StyleOfAttack styleOfAttack) {
        BuffManager.styleOfAttack = styleOfAttack;
    }
    public static void setWeaponEnhancement(int weaponEnhancement) {
        BuffManager.weaponEnhancement = weaponEnhancement;
    }
    public static void setWEAPONS(Enums.AmountOfWeapons WEAPONS) {
        BuffManager.WEAPONS = WEAPONS;
    }
    public static Enums.AmountOfWeapons getWEAPONS() {
        return WEAPONS;
    }
}
