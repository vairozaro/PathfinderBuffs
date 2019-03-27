package com.gamecodeschool.pathbuffs.Activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentActiveBuffs;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.CharacterPagerAdapter;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.PagerAdapter;

public class BuffTrackingActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private PagerAdapter mPagerAdaper;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buff_tracking);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
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


    }


    public void OneRound(View view)
    {
        BuffManager.removeRound(1);
        TabFragmentActiveBuffs.updatelist();
    }

    public void OneMinute(View view)
    {
        BuffManager.removeRound(10);
        TabFragmentActiveBuffs.updatelist();
    }

    public void TenMinutes(View view)
    {
        BuffManager.removeRound(100);
        TabFragmentActiveBuffs.updatelist();
    }

    public void OneHour(View view)
    {
        BuffManager.removeRound(600);
        TabFragmentActiveBuffs.updatelist();
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
