package com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentActiveBuffs;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentAttack;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentFeats;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentSpellSearch;

//This adapter manages the tabs for BuffTrackingActivity
public class PagerAdapter extends FragmentPagerAdapter {
    int numOfTabs;
    private String[] tabTitles = new String[]{"Cast Spells", "Active Buffs", "Feats", "Calculate Bonus"};

    public PagerAdapter(FragmentManager fm, int NumbOfTabs) {
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
                TabFragmentSpellSearch tab1 = new TabFragmentSpellSearch();
                return tab1;
            case 1:
                TabFragmentActiveBuffs tab2 = new TabFragmentActiveBuffs();
                return tab2;
            case 2:
                TabFragmentFeats tab3 = new TabFragmentFeats();
                return tab3;
            case 3:
                TabFragmentAttack tab4 = new TabFragmentAttack();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return numOfTabs;
    }
}
