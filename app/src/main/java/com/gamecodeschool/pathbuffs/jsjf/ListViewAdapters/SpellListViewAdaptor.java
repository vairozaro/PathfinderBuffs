package com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentSpellSearch;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentActiveBuffs;
import com.gamecodeschool.pathbuffs.jsjf.ClickListener;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Spells;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SpellListViewAdaptor extends RecyclerView.Adapter<SpellListViewAdaptor.MyViewHolder>{
    private List<Spells> mSpells;
    private final ClickListener listener;

    public SpellListViewAdaptor(ArrayList<Spells> mSpellList, ClickListener listner) {
        this.mSpells = mSpellList;
        this.listener = listner;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public Button button;
        private WeakReference<ClickListener> listenerRef;


        public MyViewHolder(final View view, ClickListener listener){
            super(view);
            listenerRef = new WeakReference<>(listener);


            name = (TextView) view.findViewById(R.id.spellCastName);
            button = (Button) view.findViewById(R.id.castBtn);



            button.setOnClickListener(this);
        }

        public void onClick(View v)
        {
            if(v.getId() == button.getId())
            {
                BuffManager.castSpell((String) name.getText());
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_spell_cast, parent, false);

        return new MyViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Spells spell = mSpells.get(position);
        holder.name.setText(spell.getName());

        if(BuffManager.isBuffActive((String) holder.name.getText()))
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
        return mSpells.size();
    }
}
