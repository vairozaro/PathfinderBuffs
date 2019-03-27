package com.gamecodeschool.pathbuffs.jsjf;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.gamecodeschool.pathbuffs.jsjf.Enums.ClassList.ALCHEMIST;

public class Enums {
    public enum Duration
    {
        ONE_ROUND_A_LEVEL, ONE_MINUTE, MIN_A_LEVEL, TEN_MIN_A_LEVEL, HOUR_A_LEVEL;
    }
    public static Enums.Duration getDuration(int d)
    {
        Enums.Duration tempDuration;
        switch (d)
        {
            case 0:
                tempDuration = Duration.ONE_ROUND_A_LEVEL;
                break;
            case 1:
                tempDuration = Duration.ONE_MINUTE;
                break;
            case 2:
                tempDuration = Duration.MIN_A_LEVEL;
                break;
            case 3:
                tempDuration = Duration.TEN_MIN_A_LEVEL;
                break;
            case 4:
                tempDuration = Duration.HOUR_A_LEVEL;
                break;
            default:
                tempDuration = Duration.ONE_ROUND_A_LEVEL;
                break;
        }
        return  tempDuration;
    }

    public enum StyleOfAttack
    {
        ONE_HANDED(0), TWO_HANDED(1), THROWN(2), RANGED(3);
        private final int value;
        StyleOfAttack(int i) {
            value = i;
        }
        public int getValue() {return value;}
    }
    public static Enums.StyleOfAttack StyleOfAttack(int s)
    {
        Enums.StyleOfAttack tempStyle;
        switch (s)
        {
            case 0:
                tempStyle = StyleOfAttack.ONE_HANDED;
                break;
            case 1:
                tempStyle = StyleOfAttack.TWO_HANDED;
                break;
            case 2:
                tempStyle = StyleOfAttack.THROWN;
                break;
            case 3:
                tempStyle = StyleOfAttack.RANGED;
                break;
            default:
                tempStyle = StyleOfAttack.ONE_HANDED;
                break;
        }
        return tempStyle;
    }

    public enum BonusTo
    {
        ATTACK, DAMAGE, STATS, ATTACK_AND_DAMAGE, OTHER;
    }
    public static Enums.BonusTo getBonusTo(int b)
    {
        Enums.BonusTo tempEnum;
        switch (b) {
            case 0:
                tempEnum = BonusTo.ATTACK;
                break;
            case 1:
                tempEnum = BonusTo.DAMAGE;
                break;
            case 2:
                tempEnum = BonusTo.STATS;
                break;
            case 3:
                tempEnum = BonusTo.ATTACK_AND_DAMAGE;
                break;
            case 99:
                tempEnum = BonusTo.OTHER;
                break;
            default:
                tempEnum = BonusTo.OTHER;
                break;
        }
        return tempEnum;
    }


    public enum Type
    {
        ENHANCEMENT, LUCK, SACRED, PROFANE, UNTYPED, ALCHEMICAL, MORAL;
    }
    public static Enums.Type getType(int t)
    {
        Enums.Type tempType;
        switch (t) {
            case 0:
                tempType = Type.ENHANCEMENT;
                break;
            case 1:
                tempType = Type.LUCK;
                break;
            case 2:
                tempType = Type.SACRED;
                break;
            case 3:
                tempType = Type.PROFANE;
                break;
            case 4:
                tempType = Type.UNTYPED;
                break;
            case 5:
                tempType = Type.ALCHEMICAL;
                break;
            case 6:
                tempType = Type.MORAL;
                break;
            default:
                tempType = Type.ENHANCEMENT;
                break;
        }
        return tempType;
    }

    public enum Scaling
    {
        NO_SCALING, ONE_PER_THREE_MAX_THREE, ONE_PEF_FOUR_STARTING_AT_THREE;
    }

    public static Enums.Scaling getScaling(int s)
    {
        Enums.Scaling tempEnum;
        switch (s) {
            case 0:
                tempEnum = Scaling.NO_SCALING;
                break;
            case 1:
                tempEnum = Scaling.ONE_PER_THREE_MAX_THREE;
                break;
            case 2:
                tempEnum = Scaling.ONE_PEF_FOUR_STARTING_AT_THREE;
                break;
            default:
                tempEnum = Scaling.NO_SCALING;
                break;
        }
        return tempEnum;
    }

    public enum Attribute
    {
        STRENGTH(0), DEXTERITY(1), CONSTITUTION(2), INTELLIGENCE(3), WISDOM(4), CHARISMA(5);

        private final int value;
        Attribute(int i) {
            value = i;
        }
        public int getValue() {return value;}
    }
    public static Enums.Attribute getAttribute(int a)
    {
        Enums.Attribute tempEnum;
        switch (a) {
            case 0:
                tempEnum = Attribute.STRENGTH;
                break;
            case 1:
                tempEnum = Attribute.DEXTERITY;
                break;
            case 2:
                tempEnum = Attribute.CONSTITUTION;
                break;
            case 3:
                tempEnum = Attribute.INTELLIGENCE;
                break;
            case 4:
                tempEnum = Attribute.WISDOM;
                break;
            case 5:
                tempEnum = Attribute.CHARISMA;
                break;
            default:
                tempEnum = Attribute.STRENGTH;
                break;
        }
        return tempEnum;

    }

    public enum ClassList
    {
        ALCHEMIST("Alchemist"), BARD("Bard"), BLOODRAGER("Bloodrager"), CLERIC("Cleric"), DRUID("Druid"), 
        INQUISITOR("Inquisitor"), MAGUS("Magus"), MEDIUM("Medium"), MESMERIST("Mesmerist"), OCCULTIST("Occultist"),
        PALADIN("Paladin"), PSYCHIC("Psychic"), RANGER("Ranger"), SHAMAN("Shaman"), SPIRITUALIST("Spiritualist"),
        SUMMONER("Summoner"), WITCH("Witch"), WIZARD("Wizard");
        
        private String className;
        private ClassList(String s)
        {
            this.className = s;
        }
        public String toString()
        {
            return className;
        }
    }
    public static Enums.ClassList getClassList(String s)
    {
        Enums.ClassList tempEnum = null;
        switch (s) {
            case "Alchemist":
                tempEnum = ClassList.ALCHEMIST;
                break;
            case "Bard":
                tempEnum = ClassList.BARD;
                break;
            case "Bloodrager":
                tempEnum = ClassList.BLOODRAGER;
                break;
            case "Cleric":
                tempEnum = ClassList.CLERIC;
                break;
            case "Druid":
                tempEnum = ClassList.DRUID;
                break;
            case "Inquisitor":
                tempEnum = ClassList.INQUISITOR;
                break;
            case "Magus":
                tempEnum = ClassList.MAGUS;
                break;
            case "Medium":
                tempEnum = ClassList.MEDIUM;
                break;
            case "Mesmerist":
                tempEnum = ClassList.MESMERIST;
                break;
            case "Occultist":
                tempEnum = ClassList.OCCULTIST;
                break;
            case "Paladin":
                tempEnum = ClassList.PALADIN;
                break;
            case "Psychic":
                tempEnum = ClassList.PSYCHIC;
                break;
            case "Ranger":
                tempEnum = ClassList.RANGER;
                break;
            case "Shaman":
                tempEnum = ClassList.SHAMAN;
                break;
            case "Spiritualist":
                tempEnum = ClassList.SPIRITUALIST;
                break;
            case "Summoner":
                tempEnum = ClassList.SUMMONER;
                break;
            case "Witch":
                tempEnum = ClassList.WITCH;
                break;
            case "Wizard":
                tempEnum = ClassList.WIZARD;
                break;
            default:
                tempEnum = ClassList.ALCHEMIST;
                break;
        }
        return tempEnum;
    }
    public enum AmountOfWeapons
    {
        ONE_WEAPON, TWO_WEAPONS;
    }
    public enum Speed{
        SPEED_ACTIVE, NO_SPEED;
    }
    public static Enums.Speed getSpeed(int i)
    {
        Enums.Speed tempSpeed = Speed.NO_SPEED;

        if (i == 1)
        {
            tempSpeed = Speed.SPEED_ACTIVE;
        }

        return tempSpeed;
    }
}
