package com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.FeatManager;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Feats;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.AllProfiles;

import java.util.ArrayList;
import java.util.List;

//Displays active feats in the BuffTrackingActivity on the Fragment TabFragmentFeats
//Uses toggle button an checks to see if it is active to set state of toggle button
public class ActiveFeatsListViewAdapter extends RecyclerView.Adapter<ActiveFeatsListViewAdapter.MyViewHolder>{
    private List<Feats> mFeats;

    public ActiveFeatsListViewAdapter(ArrayList<Feats> mFeatsList) {
        this.mFeats = mFeatsList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        public TextView name;
        public ToggleButton button;


        public MyViewHolder(final View view){
            super(view);

            name = (TextView) view.findViewById(R.id.txt_feat_name_toggle);
            button = (ToggleButton) view.findViewById(R.id.tbtn_feat);
            //Checks to see if feat is currently active and adding bonus
            //if so set the button to on.
            if(FeatManager.isFeatActive((String) name.getText()))
            {
                button.setChecked(true);
            }

            button.setOnCheckedChangeListener(this);
        }


        //Add or remove feat from active feat list based on toggle button change state
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked)
            {
                FeatManager.addActiveFeat((String) name.getText());
            } else {
                FeatManager.removeActiveFeat((String) name.getText());
            }
            BuffManager.calculateBonus();
        }
    }

    @Override
    public ActiveFeatsListViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_feats_toggle, parent, false);

        return new ActiveFeatsListViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ActiveFeatsListViewAdapter.MyViewHolder holder, int position) {
        Feats feat = mFeats.get(position);
        holder.name.setText(feat.getName());
    }

    @Override
    public int getItemCount() {
        return mFeats.size();
    }

}