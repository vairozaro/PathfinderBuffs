package com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters;

import android.view.View;

import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentSpellSearch;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentActiveBuffs;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Buff;
import com.gamecodeschool.pathbuffs.jsjf.ClickListener;

import android.widget.TextView;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import java.lang.ref.WeakReference;
import java.util.List;

//This adapter manages the active buffs.  It displays the name, round remaining, and a dispel button.
public class ListViewAdaptor extends RecyclerView.Adapter<ListViewAdaptor.MyViewHolder>
{
    private List<Buff> mDataList;
    private final ClickListener listener;

    public ListViewAdaptor(List<Buff> mBuffList, ClickListener listner) {
        this.mDataList = mBuffList;
        this.listener = listner;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView buffName,rounds;
        public Button buttonView;
        private WeakReference<ClickListener> listenerRef;


        public MyViewHolder(final View view, ClickListener listener){
            super(view);
            listenerRef = new WeakReference<>(listener);


            buffName = (TextView) view.findViewById(R.id.spellName);
            rounds= (TextView) view.findViewById(R.id.rounds);
            buttonView = (Button) view.findViewById(R.id.dispelBtn);

            buttonView.setOnClickListener(this);
        }

        //On dispel button clicked remove buff from active buffs.  Update the this adapter
        //along with the TabFragmentSpellSearch adapater
        public void onClick(View v)
        {
            if(v.getId() == buttonView.getId())
            {
                BuffManager.dispelBuff((String) buffName.getText());
                TabFragmentActiveBuffs.updatelist();
                TabFragmentSpellSearch.updatelist();
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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_active_spells, parent, false);

        return new MyViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Buff buff = mDataList.get(position);
        holder.buffName.setText(buff.getName());
        holder.rounds.setText(buff.getRoundsString());

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}