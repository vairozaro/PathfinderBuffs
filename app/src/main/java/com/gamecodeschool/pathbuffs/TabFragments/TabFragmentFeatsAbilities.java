package com.gamecodeschool.pathbuffs.TabFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gamecodeschool.pathbuffs.Activities.add_profile;
import com.gamecodeschool.pathbuffs.Activities.allFeats;
import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.jsjf.ClickListener;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.ListViewAdaptor;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.ProfileFeatsViewAdaptor;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.AllProfiles;

//This fragment is one of the fragments for BuffTrackingActivity
//This fragment displays all the current feats that are active on a profile as toggle buttons
public class TabFragmentFeatsAbilities extends android.support.v4.app.Fragment {
    Button btnAddFeat;

    RecyclerView mRecyclerView;

    static ProfileFeatsViewAdaptor mAdapter = new ProfileFeatsViewAdaptor(AllProfiles.currentProfile.getFeats(), new ClickListener() {
        public void onPositionClicked(int position) {}
        public void onLongClicked(int position) {}
    });

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view =  inflater.inflate(R.layout.fragment_profiles_feats, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_profile_feats);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(mAdapter);

        btnAddFeat = (Button)view.findViewById(R.id.btn_add_feat_to_profile);
        btnAddFeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), allFeats.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public static void updatelist()
    {
        mAdapter.notifyDataSetChanged();
    }
}
