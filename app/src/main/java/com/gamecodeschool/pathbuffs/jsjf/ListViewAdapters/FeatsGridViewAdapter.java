package com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.FeatManager;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Feats;

import java.util.ArrayList;


//Adapter is used to display feats and abilites in a grid view.  This will be with toggle buttons.
public class FeatsGridViewAdapter extends BaseAdapter {
    private ArrayList<Feats> mFeats;
    private LayoutInflater layoutInflater;
    private Context context;

    public FeatsGridViewAdapter(Context aContext, ArrayList<Feats> feats)
    {
        this.mFeats = feats;
        this.context = aContext;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return mFeats.size();
    }

    @Override
    public Object getItem(int position) {
        return mFeats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        GridViewHolder holder;
        if(view == null)
        {
            view = layoutInflater.inflate(R.layout.rec_feats_toggle, null);
            holder = new GridViewHolder();
            holder.name = (TextView) view.findViewById(R.id.txt_feat_name_toggle);
            holder.tButton = (ToggleButton) view.findViewById(R.id.tbtn_feat);
            view.setTag(holder);
        }else{
            holder = (GridViewHolder) view.getTag();
        }

        Feats feat = this.mFeats.get(position);
        holder.name.setText(feat.getName());
        holder.tButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    FeatManager.addActiveFeat((String) holder.name.getText());
                } else {
                    FeatManager.removeActiveFeat((String) holder.name.getText());
                }
                BuffManager.calculateBonus();
            }
        });

        return null;
    }

    private static class GridViewHolder{
        TextView name;
        ToggleButton tButton;
    }
}
