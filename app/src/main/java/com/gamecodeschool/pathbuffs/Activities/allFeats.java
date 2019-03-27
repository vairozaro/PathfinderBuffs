package com.gamecodeschool.pathbuffs.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.ClickListener;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.FeatManager;
import com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters.FeatsListViewAdaptor;

public class allFeats extends AppCompatActivity {

    static FeatsListViewAdaptor mAdapter = new FeatsListViewAdaptor(FeatManager.getAllFeatsList(), new ClickListener() {
        public void onPositionClicked(int position) {}
        public void onLongClicked(int position) {}
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_feats);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_feats);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

    }

    public static void updateList()
    {
        mAdapter.notifyDataSetChanged();
    }

}
