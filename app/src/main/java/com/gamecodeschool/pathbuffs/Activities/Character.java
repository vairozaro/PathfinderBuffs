package com.gamecodeschool.pathbuffs.Activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.CharacterPagerAdapter;

//This activity manages the fragments that have to do with the Character
//  TabFragmentAttributes
//  TabFragmentFeats
public class Character extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private CharacterPagerAdapter mPagerAdaper;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        //Sets the toolbar with the tabs and the text
        tabLayout = (TabLayout)findViewById(R.id.tabLayout_character);
        tabLayout.addTab(tabLayout.newTab().setText("Character"));
        tabLayout.addTab(tabLayout.newTab().setText("Feats/Ablities"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mPagerAdaper = new CharacterPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        mViewPager = (ViewPager) findViewById(R.id.view_pager_character);
        mViewPager.setAdapter(mPagerAdaper);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(mViewPager);

    }


    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    public void onTabUnselected(TabLayout.Tab tab) {

    }

    public void onTabReselected(TabLayout.Tab tab) {

    }


}
