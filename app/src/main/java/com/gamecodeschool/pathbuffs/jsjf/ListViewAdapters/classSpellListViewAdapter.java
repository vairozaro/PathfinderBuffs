package com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Spells;

import java.util.ArrayList;

//This adapter displays the class spell list.  On class selection from classNameListViewAdapter
//display that class
public class classSpellListViewAdapter extends RecyclerView.Adapter<classSpellListViewAdapter.MyViewHolder>{
    static ArrayList<Spells> mClassSpells;

    public classSpellListViewAdapter(ArrayList<Spells> classSpells) {
        this.mClassSpells = classSpells;
    }

    public void refresh(ArrayList<Spells> spells)
    {
        mClassSpells = spells;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public Button button;
        private Spells spell;

        public MyViewHolder(final View view){
            super(view);

            name = (TextView) view.findViewById(R.id.txt_class_spell_name);
            name.setOnClickListener(this);
            button = (Button) view.findViewById(R.id.btn_spell_add);

            //If Spell is added to favorites list deactivate button else activate it
            if(BuffManager.favoritesContainsSpells(spell))
            {
                button.setAlpha(.5f);
                button.setClickable(false);
            }else {
                button.setAlpha(1f);
                button.setClickable(true);
            }

        }
        //Add spell to favorites list
        public void onClick(View view) {
            BuffManager.addSpellToFavorites(spell);
        }
    }

    @Override
    public classSpellListViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_class_spell_list, parent, false);

        return new classSpellListViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(classSpellListViewAdapter.MyViewHolder holder, int position) {
        holder.spell = mClassSpells.get(position);
        holder.name.setText(holder.spell.getName());
    }

    @Override
    public int getItemCount() {
        return mClassSpells.size();
    }

}

