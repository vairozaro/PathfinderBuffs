package com.gamecodeschool.pathbuffs.jsjf.Profiles;

import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.FeatManager;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Feats;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//This class manages all the profiles and the current profile that is selected
public class AllProfiles {

    public static LinkedList<Profile> allprofiles = new LinkedList<Profile>();
    public static Profile currentProfile;
    public static ArrayList<String> profileNames = new ArrayList<String>();
    public static int[] defaultprofile = new int[]{10,10,10,10,10,10};


    public AllProfiles()
    {

    }

    public static void addProfile(Profile p)
    {
        if(allprofiles.size() > 0 && currentProfile.getName().equals(""))
        {
            allprofiles.remove(currentProfile);
            profileNames.remove(currentProfile);
        }
        allprofiles.add(p);
        profileNames.add(p.getName());
        //If a profile was saved as current profile load that as the current profile
        if(p.getSelected() == 'y' || allprofiles.size() == 1)
        {
            if(currentProfile != null)
            {
                currentProfile.setSelected('n');
            }
            currentProfile = p;
        }

    }
    //This changes the currently selected profile to a new profile
    public static Profile setProfile(String selected)
    {
        currentProfile.setSelected('n');
        for(Profile x: allprofiles)
        {
            if(x.getName().equalsIgnoreCase(selected))
            {
                x.setSelected('y');
                currentProfile = x;
            }
        }

        return currentProfile;
    }
    //delete all profiles
    public static void clearAllProfiles()
    {
        allprofiles.clear();
        profileNames.clear();

    }
    //Remove a profile from all profiles
    public static void deleteProfile(Profile p)
    {
        currentProfile = allprofiles.get(0);
        profileNames.remove(p.name);
        allprofiles.remove(p);
        if(allprofiles.size() == 0) {
            currentProfile = new Profile("", defaultprofile, 1, 1, 'y');
            allprofiles.add(currentProfile);
            profileNames.add(currentProfile.name);
        }

    }

    public static void addFeatToProfile(String s, String f)
    {
        for(Profile x: allprofiles)
        {
            if(x.getName().equals(s))
            {
                x.addFeatToProfile(FeatManager.getFeat(f));
            }
        }
    }
    public static void addAbilityToProfile(String s, String a)
    {
        for(Profile x: allprofiles)
        {
            if(x.getName().equals(s))
            {
                x.addAbilityToProfile(a);
            }
        }
    }
    public static boolean hasFeat(String n)
    {
        boolean hasfeat = false;
        List<Feats> feats = currentProfile.getFeats();

        for (Feats x: feats)
        {
            if(x.getName().equals(n))
            {
                hasfeat = true;
            }
        }

        return hasfeat;
    }
    public static ArrayList<String> getProfileNames() {
        return profileNames;
    }
    public static LinkedList<Profile> getAllprofiles() {
        return allprofiles;
    }
}
