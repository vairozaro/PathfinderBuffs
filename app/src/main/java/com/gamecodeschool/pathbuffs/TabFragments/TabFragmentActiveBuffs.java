package com.gamecodeschool.pathbuffs.TabFragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.ClickListener;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.ListViewAdaptor;

//This fragment is one of the fragments for BuffTrackingActivity
//This fragment displays the current active buffs on a player.  It displays
//each buff name, rounds remaining and a dispel button to remove the buff
public class TabFragmentActiveBuffs extends android.support.v4.app.Fragment {

    RecyclerView mRecyclerView;

    static ListViewAdaptor mAdapter = new ListViewAdaptor(BuffManager.getBuffList(), new ClickListener() {
        public void onPositionClicked(int position) {}
        public void onLongClicked(int position) {}
    });

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view =  inflater.inflate(R.layout.fragment_active_spells, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.dispel_recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public static void updatelist()
    {
        mAdapter.notifyDataSetChanged();
    }
}