package com.gamecodeschool.pathbuffs.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Spells;
import com.gamecodeschool.pathbuffs.jsjf.Enums;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.classNameListViewAdapter;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.classSpellListViewAdapter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static android.app.PendingIntent.getActivity;

//This activity displays all classes that cast spells.  On a class selection display
//the spells on the selected class list.
public class spellLists extends AppCompatActivity {

    RecyclerView mRecyclerViewClassNames;
    RecyclerView mRecyclerViewClassSpellList;

    static ArrayList<Spells> currentSpellList = new ArrayList<Spells>();
    static ArrayList<String> classListnames = new ArrayList<String>();
    private static EnumSet<Enums.ClassList> classListEnumset = EnumSet.allOf(Enums.ClassList.class);

    static classSpellListViewAdapter mAdapterClassSpellList = new classSpellListViewAdapter(currentSpellList);

    static classNameListViewAdapter mAdapterClassNames = new classNameListViewAdapter(classListnames);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_lists);

        currentSpellList.addAll(BuffManager.getAllSpellsList());

        //add All to the class lists to display
        classListnames.add("All");
        for(Enums.ClassList x: classListEnumset)
        {
            classListnames.add(x.toString());
        }
        mRecyclerViewClassNames = (RecyclerView) findViewById(R.id.recycler_class_names);
        mRecyclerViewClassNames.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewClassNames.setAdapter(mAdapterClassNames);
        mAdapterClassNames.notifyDataSetChanged();

        mRecyclerViewClassSpellList = (RecyclerView) findViewById(R.id.recycler_class_spell_list);
        mRecyclerViewClassSpellList.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewClassSpellList.setAdapter(mAdapterClassSpellList);
        mAdapterClassSpellList.notifyDataSetChanged();

    }

    //updates the list of spells for a class based on class selected
    public static void updateClassSpellList(String className, Context context)
    {
        currentSpellList.clear();
        if (className.equals("All")) {
            currentSpellList.addAll(BuffManager.getAllSpellsList());
        }else {
            currentSpellList = (ArrayList<Spells>) BuffManager.getClassSpellList(Enums.getClassList(className));
        }

        mAdapterClassSpellList.refresh(currentSpellList);
    }
}
