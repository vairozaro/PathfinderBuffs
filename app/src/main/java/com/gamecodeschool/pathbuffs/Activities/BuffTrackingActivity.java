package com.gamecodeschool.pathbuffs.Activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentActiveBuffs;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentSpellSearch;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.CharacterPagerAdapter;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.PagerAdapter;

//This activity manages the Fragments that have to do with active buffs and feats
//  TabFragmentSearchSpells
//  TabFragmentActiveBuffs
//  TabFragmentFeatsAbilities
//  TabFragmentAttack
public class BuffTrackingActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private PagerAdapter mPagerAdaper;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private Button btnOneRound, btnOneMin, btnTenMin, btnOneHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buff_tracking);

        //Sets the toolbar with the tabs and the text
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Cast Spells"));
        tabLayout.addTab(tabLayout.newTab().setText("Active Buffs"));
        tabLayout.addTab(tabLayout.newTab().setText("Feats"));
        tabLayout.addTab(tabLayout.newTab().setText("Calculate Bonus"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mPagerAdaper = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mPagerAdaper);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(mViewPager);

        btnOneRound = (Button)findViewById(R.id.oneRound);
        btnOneMin = (Button)findViewById(R.id.oneMinute);
        btnTenMin = (Button)findViewById(R.id.tenMinutes);
        btnOneHour = (Button)findViewById(R.id.oneHour);
    }

    //Changes the rounds of the active spells based on the button clicked
    public void timePasses(View view)
    {
        Button button = (Button)view;
        if(button.getId() == btnOneRound.getId())
        {
            BuffManager.removeRound(1);
        }else if(button.getId() == btnOneMin.getId())
        {
            BuffManager.removeRound(10);
        }else if(button.getId() == btnTenMin.getId())
        {
            BuffManager.removeRound(100);
        }else if(button.getId() == btnOneHour.getId())
        {
            BuffManager.removeRound(600);
        }
        //Updates list if any items are removed
        TabFragmentActiveBuffs.updatelist();
        TabFragmentSpellSearch.updatelist();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
