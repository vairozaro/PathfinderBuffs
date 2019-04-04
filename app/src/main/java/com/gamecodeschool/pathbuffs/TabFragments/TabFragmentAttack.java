package com.gamecodeschool.pathbuffs.TabFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.FeatManager;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Feats;
import com.gamecodeschool.pathbuffs.jsjf.Enums;

import static android.widget.AdapterView.*;

//This fragment is one of the fragments for BuffTrackingActivity
//It displays a spinner to select the type of attack that will be used
//The enhancement bonus of the weapon and if they are flanking or using fighting defensivly
public class TabFragmentAttack extends android.support.v4.app.Fragment {

    static TextView txtAttackBonus;
    static TextView txtDamageBonus;
    static ToggleButton tbtnFlanking, tbtnBralwersFlurry;
    RadioGroup radioEnhanceGroup;
    RadioButton radioButton;
    Spinner spinner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_bonus_calculation, container, false);

        //assign variables to the layouts they are tracking.
        txtAttackBonus = (TextView)view.findViewById(R.id.txt_attack_number);
        txtDamageBonus = (TextView)view.findViewById(R.id.txt_damage_number);
        tbtnFlanking = (ToggleButton)view.findViewById(R.id.tbtn_flanking);
        tbtnBralwersFlurry = (ToggleButton)view.findViewById(R.id.tbtn_brawlers_flurry);
        radioEnhanceGroup = (RadioGroup)view.findViewById(R.id.rgroup_enhance);
        spinner = (Spinner)view.findViewById(R.id.spn_style_of_attack);



        //On spinner item selected change the stlye of attack and update txt fields of attack and damage
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                BuffManager.setStyleOfAttack(Enums.StyleOfAttack.valueOf(position));
                BuffManager.calculateBonus();
            }

            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        //On change of radio button set the new enhancement bonus and update the attack and damage
        //Textviews
        radioEnhanceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int position) {
                //Gets the id of the selected radio button.  If the button is masterwork,
                //set the bonus to 99 else set it to Integer of the text of the radio button.
                int selectedID = radioEnhanceGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)view.findViewById(selectedID);
                if(selectedID == R.id.rbtn_enhanceMW)
                {
                    BuffManager.setWeaponEnhancement(99);
                }else {
                    BuffManager.setWeaponEnhancement(Integer.parseInt((String) radioButton.getText()));
                }

                BuffManager.calculateBonus();
            }
        });

        //If using Brawlers Flurry update BuffManager and the attack and damage textviews
        tbtnBralwersFlurry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    BuffManager.setWEAPONS(Enums.AmountOfWeapons.TWO_WEAPONS);
                    BuffManager.calculateBonus();
                } else {
                    BuffManager.setWEAPONS(Enums.AmountOfWeapons.ONE_WEAPON);
                    BuffManager.calculateBonus();
                }
            }
        });

        //Set a listener to the toggle button to change the state of flanking
        tbtnFlanking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    BuffManager.setFlanking(Enums.FLANKING.FLANKING);
                    BuffManager.calculateBonus();
                } else {
                    BuffManager.setFlanking(Enums.FLANKING.NO_FLANKING);
                    BuffManager.calculateBonus();
                }
            }
        });

    //Checks to see the last selected of the spinners and buttons and updates then to that.
        //Get the last selected style of attack and set the spinner to that value
        spinner.setSelection(BuffManager.getStyleOfAttack().getValue());

        //Checks to see if Bralwers flurry is active.  If so put toggle to on position
        if (BuffManager.getWEAPONS() == Enums.AmountOfWeapons.TWO_WEAPONS)
        {
            tbtnBralwersFlurry.setChecked(true);
        }
        //Checks to see if flanking is active.  If so put toggle to on position
        if (BuffManager.getFlanking() == Enums.FLANKING.NO_FLANKING)
        {
            tbtnFlanking.setChecked(false);
        }else{
            tbtnFlanking.setChecked(true);
        }

        return view;
    }

    public static void changeTextViews(String attackBonus, String damageBonus)
    {
        txtAttackBonus.setText(attackBonus);
        txtDamageBonus.setText(damageBonus);
    }

}
