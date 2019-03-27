package com.gamecodeschool.pathbuffs.TabFragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.jsjf.ClickListener;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.ActiveFeatsListViewAdapter;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.ListViewAdaptor;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.AllProfiles;

public class TabFragmentFeats extends android.support.v4.app.Fragment  {

    RecyclerView mRecyclerView;

    static ActiveFeatsListViewAdapter mAdapter = new ActiveFeatsListViewAdapter(AllProfiles.currentProfile.getFeats());

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_active_feats, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_active_feats);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

}
