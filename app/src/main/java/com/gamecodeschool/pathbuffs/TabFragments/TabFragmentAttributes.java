package com.gamecodeschool.pathbuffs.TabFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.gamecodeschool.pathbuffs.Activities.add_profile;
import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.AllProfiles;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.Profile;

import static com.gamecodeschool.pathbuffs.jsjf.Files.saveStatsFile;

public class TabFragmentAttributes extends android.support.v4.app.Fragment{

    TextView[] attibuteTextViews = new TextView[6];
    TextView[] modifierTextViews = new TextView[6];
    Spinner spin;
    ArrayAdapter<String> spinnerAdapter;

    TextView txtBAB;
    TextView txtLevel;
    TextView txtName;
    Button btnAdd, btnEdit, btnDelete;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view =  inflater.inflate(R.layout.fragment_profile_attributes, container, false);

        txtName = (TextView)view.findViewById(R.id.txt_profile_name);
        txtBAB = (TextView)view.findViewById(R.id.txt_profile_bab);
        txtLevel = (TextView)view.findViewById(R.id.txt_profile_level);
        btnAdd = (Button)view.findViewById(R.id.add_profile);
        btnEdit = (Button)view.findViewById(R.id.edit_profile);
        btnDelete = (Button)view.findViewById(R.id.delete_profile);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), add_profile.class);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllProfiles.deleteProfile(AllProfiles.currentProfile);
                selectProfile(AllProfiles.currentProfile.getName());
                spin.setSelection(spinnerAdapter.getPosition(AllProfiles.currentProfile.getName()));
                saveStatsFile(getContext());
            }
        });
        setTextViews(attibuteTextViews, (LinearLayout)view.findViewById(R.id.ll_ability));
        setTextViews(modifierTextViews, (LinearLayout)view.findViewById(R.id.ll_modifier));

        spin = (Spinner)view.findViewById(R.id.spn_select_profile);
        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, AllProfiles.getProfileNames());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(spinnerAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                selectProfile(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public void selectProfile(String selected) {
        Profile selectedPro = AllProfiles.setProfile(selected);
        //Toast.makeText(this, Integer.toString(modifierTextViews.length), Toast.LENGTH_SHORT).show();
        int[] setAttibutes = selectedPro.getAttirbute();
        txtLevel.setText(Integer.toString(selectedPro.getLevel()));
        txtName.setText(selectedPro.getName());
        txtBAB.setText(Integer.toString(selectedPro.getBab()));
        for(int i = 0; i < attibuteTextViews.length; i++)
        {
            attibuteTextViews[i].setText(Integer.toString(setAttibutes[i]));
            int tempMod = setAttibutes[i] - 10;

            if(tempMod < 0)
            {
                tempMod = tempMod - 1;
            }
            modifierTextViews[i].setText(Integer.toString(tempMod/2));
        }
        BuffManager.setBab(selectedPro.getBab());
        BuffManager.setCasterLevel(selectedPro.getLevel());
    }

    private void setTextViews(TextView[] arraySelected, LinearLayout llSelected)
    {
        for(int i = 0; i < arraySelected.length; i++)
        {
            TextView value = new TextView(getContext());
            value.setId(i);
            value.setTextSize(18);
            value.setGravity(Gravity.CENTER);
            value.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            llSelected.addView(value);
            arraySelected[i] = value;

        }
    }
}
