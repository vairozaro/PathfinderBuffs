package com.gamecodeschool.pathbuffs.jsjf;

import java.util.HashMap;
import java.util.Map;


//Public class of all enums
public class Enums {
    public enum Duration
    {
        ONE_ROUND_A_LEVEL, ONE_MINUTE, MIN_A_LEVEL, TEN_MIN_A_LEVEL, HOUR_A_LEVEL;
        private static Map map = new HashMap<>();
        static
        {
            for(Duration duration : Duration.values())
            {
                map.put(duration.ordinal(), duration);
            }
        }
        public static Duration valueOf(int duration)
        {
            return (Duration) map.get(duration);
        }
        public int getValue() {return this.ordinal();}
    }

    public enum StyleOfAttack
    {
        ONE_HANDED(0), TWO_HANDED(1), THROWN(2), RANGED(3);
        private final int value;
        private static Map map = new HashMap<>();
        StyleOfAttack(int i) {
            value = i;
        }

        static
        {
            for(StyleOfAttack styleOfAttack : StyleOfAttack.values())
            {
                map.put(styleOfAttack.ordinal(), styleOfAttack);
            }
        }
        public static StyleOfAttack valueOf(int styleOfAttack)
        {
            return (StyleOfAttack) map.get(styleOfAttack);
        }
        public int getValue() {return value;}
    }

    public enum BonusTo
    {
        ATTACK, DAMAGE, STATS, ATTACK_AND_DAMAGE, OTHER;
        private static Map map = new HashMap<>();
        static
        {
            for(BonusTo bonusTo : BonusTo.values())
            {
                map.put(bonusTo.ordinal(), bonusTo);
            }
        }
        public static BonusTo valueOf(int bonusTo)
        {
            return (BonusTo) map.get(bonusTo);
        }
        public int getValue() {return this.ordinal();}
    }

    public enum Type
    {
        ENHANCEMENT, LUCK, SACRED, PROFANE, UNTYPED, ALCHEMICAL, MORAL;

        private static Map map = new HashMap<>();
        static
        {
            for(Type type : Type.values())
            {
                map.put(type.ordinal(), type);
            }
        }
        public static Type valueOf(int type)
        {
            return (Type) map.get(type);
        }
        public int getValue() {return this.ordinal();}

    }

    public enum Scaling
    {
        NO_SCALING, ONE_PER_THREE_MAX_THREE, ONE_PEF_FOUR_STARTING_AT_THREE;

        private static Map map = new HashMap<>();
        static
        {
            for(Scaling scaling : Scaling.values())
            {
                map.put(scaling.ordinal(), scaling);
            }
        }
        public static Scaling valueOf(int scaling)
        {
            return (Scaling) map.get(scaling);
        }
        public int getValue() {return this.ordinal();}
    }

    public enum Attribute
    {
        STRENGTH(0), DEXTERITY(1), CONSTITUTION(2), INTELLIGENCE(3), WISDOM(4), CHARISMA(5);

        private final int value;
        private static Map map = new HashMap<>();
        Attribute(int i) {
            value = i;
        }
        static
        {
            for(Attribute attribute : Attribute.values())
            {
                map.put(attribute.value, attribute);
            }
        }
        public static Attribute valueOf(int attribute)
        {
            return (Attribute) map.get(attribute);
        }
        public int getValue() {return value;}
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
    public enum AmountOfWeapons
    {
        ONE_WEAPON, TWO_WEAPONS;
    }
    public enum Speed{
        SPEED_ACTIVE, NO_SPEED;
    }
    public enum FLANKING{
        NO_FLANKING(0), FLANKING(2), OUTFLANK(4);
        private int value;
        FLANKING(int value){
            this.value = value;
        }
        public int getValue(){return value;}
    }
}
