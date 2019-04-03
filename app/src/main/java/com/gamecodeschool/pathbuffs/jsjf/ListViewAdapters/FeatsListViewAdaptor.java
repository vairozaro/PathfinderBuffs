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

//This class displays all loaded feats.  On add button pressed it saves to the current profile that feat
public class FeatsListViewAdaptor extends RecyclerView.Adapter<FeatsListViewAdaptor.MyViewHolder>{
    private List<Feats> mFeats;
    private final ClickListener listener;
    private Feats feat;

    public FeatsListViewAdaptor(ArrayList<Feats> mFeatsList, ClickListener listner) {
        this.mFeats = mFeatsList;
        this.listener = listner;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public Button button;
        private WeakReference<ClickListener> listenerRef;
        Feats f;

        //Sets view and variables
        public MyViewHolder(final View view, ClickListener listener){
            super(view);
            listenerRef = new WeakReference<>(listener);

            name = (TextView) view.findViewById(R.id.txt_feat_name);
            button = (Button) view.findViewById(R.id.btn_feat_add);
            button.setOnClickListener(this);

            f = FeatManager.getFeat((String) name.getText());

        }

        //On click add the feat to the current profile, save the feats to that profile and update
        //This adapter and the profile feats
        public void onClick(View v)
        {
            if(v.getId() == button.getId())
            {
                AllProfiles.currentProfile.addFeatToProfile(feat);
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
        holder.feat = mFeats.get(position);
        holder.name.setText(feat.getName());
        //If feat is currently saved to profile remove button so can not be added again.
        //Else display the add button.
        if(AllProfiles.currentProfile.hasFeat(holder.feat)
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
