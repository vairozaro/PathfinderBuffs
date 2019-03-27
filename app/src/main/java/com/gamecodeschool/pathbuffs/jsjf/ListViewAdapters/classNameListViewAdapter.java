package com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.Enums;
import com.gamecodeschool.pathbuffs.Activities.spellLists;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class classNameListViewAdapter extends RecyclerView.Adapter<classNameListViewAdapter.MyViewHolder>{
    private ArrayList<String> mClassNames = new ArrayList<>();

    public classNameListViewAdapter(ArrayList<String> mClassNames) {
        this.mClassNames = mClassNames;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public RelativeLayout relativeLayout;

        public MyViewHolder(final View view){
            super(view);

            name = (TextView) view.findViewById(R.id.txt_class_name);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_class_name);
            relativeLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View view)
        {
            spellLists.updateClassSpellList((String) name.getText(), view.getContext());
        }
    }

    @Override
    public classNameListViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_class_name, parent, false);

        return new classNameListViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(classNameListViewAdapter.MyViewHolder holder, int position) {
        String className = mClassNames.get(position);
        holder.name.setText(className);
    }

    @Override
    public int getItemCount() {
        return mClassNames.size();
    }

}
