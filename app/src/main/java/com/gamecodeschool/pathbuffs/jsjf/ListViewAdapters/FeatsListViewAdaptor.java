package com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gamecodeschool.pathbuffs.Activities.allFeats;
import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentFeatsAbilities;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.FeatManager;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.AllProfiles;
import com.gamecodeschool.pathbuffs.jsjf.ClickListener;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Feats;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.gamecodeschool.pathbuffs.jsjf.Files.saveFeatsFile;

public class FeatsListViewAdaptor extends RecyclerView.Adapter<FeatsListViewAdaptor.MyViewHolder>{
    private List<Feats> mFeats;
    private final ClickListener listener;

    public FeatsListViewAdaptor(ArrayList<Feats> mFeatsList, ClickListener listner) {
        this.mFeats = mFeatsList;
        this.listener = listner;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public Button button;
        private WeakReference<ClickListener> listenerRef;
        boolean hasfeat;
        Feats f;


        public MyViewHolder(final View view, ClickListener listener){
            super(view);
            listenerRef = new WeakReference<>(listener);

            name = (TextView) view.findViewById(R.id.txt_feat_name);
            button = (Button) view.findViewById(R.id.btn_feat_add);
            button.setOnClickListener(this);

            f = FeatManager.getFeat((String) name.getText());

        }

        public void onClick(View v)
        {
            if(v.getId() == button.getId())
            {
                AllProfiles.currentProfile.addFeatToProfile((String) name.getText());
                saveFeatsFile(v.getContext());
                TabFragmentFeatsAbilities.updatelist();
                allFeats.updateList();
            }
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
        public boolean onLongClick(View v)
        {
            listenerRef.get().onLongClicked(getAdapterPosition());
            return true;
        }



    }

    @Override
    public FeatsListViewAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_feat_add, parent, false);

        return new FeatsListViewAdaptor.MyViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(FeatsListViewAdaptor.MyViewHolder holder, int position) {
        Feats feat = mFeats.get(position);
        holder.name.setText(feat.getName());
        if(AllProfiles.currentProfile.hasFeat((String) holder.name.getText()))
        {
            holder.button.setVisibility(View.INVISIBLE);
            holder.button.setClickable(false);
        }else {
            holder.button.setAlpha(1f);
            holder.button.setClickable(true);
            holder.button.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mFeats.size();
    }

}
