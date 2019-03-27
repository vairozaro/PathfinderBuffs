package com.gamecodeschool.pathbuffs.TabFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
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

public class TabFragmentAttack extends android.support.v4.app.Fragment {

    static TextView txtAttackBonus;
    static TextView txtDamageBonus;
    static ToggleButton tbtnWeaponTraining, tbtnBralwersFlurry;
    RadioGroup radioEnhanceGroup;
    Feats weaponTraining = new Feats();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_bonus_calculation, container, false);

        txtAttackBonus = (TextView)view.findViewById(R.id.txt_attack_number);
        txtDamageBonus = (TextView)view.findViewById(R.id.txt_damage_number);
        tbtnWeaponTraining = (ToggleButton)view.findViewById(R.id.tbtn_weapon_training);
        tbtnBralwersFlurry = (ToggleButton)view.findViewById(R.id.tbtn_brawlers_flurry);

        if (BuffManager.getWEAPONS() == Enums.AmountOfWeapons.TWO_WEAPONS)
        {
            tbtnBralwersFlurry.setChecked(true);
        }

        Spinner spinner = (Spinner)view.findViewById(R.id.spn_style_of_attack);

        spinner.setSelection(BuffManager.getStyleOfAttack().getValue());
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItem = adapterView.getItemAtPosition(position).toString();

                int enhanceBonus = getEnhanceBonus();
                BuffManager.setWeaponEnhancement(enhanceBonus);
                Toast toast;
                switch (selectedItem)
                {
                    case "One-Handed":
                        BuffManager.setStyleOfAttack(Enums.StyleOfAttack.ONE_HANDED);
                        break;
                    case "Two-Handed":
                        BuffManager.setStyleOfAttack(Enums.StyleOfAttack.TWO_HANDED);
                        break;
                    case "Thrown":
                        BuffManager.setStyleOfAttack(Enums.StyleOfAttack.THROWN);
                        break;
                    case "Ranged":
                        BuffManager.setStyleOfAttack(Enums.StyleOfAttack.RANGED);
                        break;
                    default:
                        BuffManager.setStyleOfAttack(Enums.StyleOfAttack.ONE_HANDED);
                        break;
                }
                BuffManager.calculateBonus();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        radioEnhanceGroup = (RadioGroup)view.findViewById(R.id.rgroup_enhance);

        radioEnhanceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int enhanceBonus = getEnhanceBonus();
                BuffManager.setWeaponEnhancement(enhanceBonus);
                BuffManager.calculateBonus();
            }
        });

        tbtnBralwersFlurry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
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

        tbtnWeaponTraining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                weaponTraining.name = "Weapon Training";
                weaponTraining.bonusTo = Enums.BonusTo.OTHER;
                if(isChecked)
                {
                    FeatManager.addActiveFeat(weaponTraining);
                    BuffManager.calculateBonus();
                } else {
                    FeatManager.removeActiveFeat(weaponTraining);
                    BuffManager.calculateBonus();
                }
            }
        });

        return view;
    }

    public static void changeTextViews(String attackBonus, int damageBonus)
    {
        txtAttackBonus.setText(attackBonus);
        txtDamageBonus.setText(Integer.toString(damageBonus));
    }

    private int getEnhanceBonus ()
    {
        int bonus;

        int selectID = radioEnhanceGroup.getCheckedRadioButtonId();

        switch (selectID){
            case R.id.rbtn_enhance0:
                bonus = 0;
                break;
            case R.id.rbtn_enhance1:
                bonus = 1;
                break;
            case R.id.rbtn_enhance2:
                bonus = 2;
                break;
            case R.id.rbtn_enhance3:
                bonus = 3;
                break;
            case R.id.rbtn_enhance4:
                bonus = 4;
                break;
            case R.id.rbtn_enhance5:
                bonus = 5;
                break;
            case R.id.rbtn_enhanceMW:
                bonus = 99;
                break;
            default:
                bonus = 0;
                break;
        }

        return bonus;
    }
}
