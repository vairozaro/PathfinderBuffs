package com.gamecodeschool.pathbuffs.TabFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.ClickListener;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.SpellListViewAdaptor;
import com.gamecodeschool.pathbuffs.Activities.spellLists;

public class TabFragmentSpellSearch extends android.support.v4.app.Fragment {

    static SpellListViewAdaptor mAdapter = new SpellListViewAdaptor(BuffManager.getAllSpellsList(), new ClickListener() {
        public void onPositionClicked(int position) {}
        public void onLongClicked(int position) {}
    });

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_favorite_spell_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.case_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(mAdapter);

        Button button = (Button) view.findViewById(R.id.btn_add_spell_to_favorites);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), spellLists.class);
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
