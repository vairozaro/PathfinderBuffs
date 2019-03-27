package com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentActiveBuffs;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentAttack;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentAttributes;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentFeatsAbilities;

public class CharacterPagerAdapter extends FragmentPagerAdapter {
    int numOfTabs;
    private String[] tabTitles = new String[]{"Attributes", "Feats/Abilities", "Attributes", "Feats/Abilities"};

    public CharacterPagerAdapter(FragmentManager fm, int NumbOfTabs) {
        super(fm);
        this.numOfTabs = NumbOfTabs;
    }
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                TabFragmentAttributes tab1 = new TabFragmentAttributes();
                return tab1;
            case 1:
                TabFragmentFeatsAbilities tab2 = new TabFragmentFeatsAbilities();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
