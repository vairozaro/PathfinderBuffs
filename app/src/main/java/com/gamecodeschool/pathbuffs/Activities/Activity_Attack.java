package com.gamecodeschool.pathbuffs.Activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.jsjf.Enums;

public class Activity_Attack extends AppCompatActivity {

    private RadioGroup radioHandGroup;
    private int bab;
    private int level;

    CheckBox cbxPow, cbxTwoWeapon, dedicated, focus, weaponSpec, weaponTrain;
    RadioButton rdb2Hand;
    RadioGroup radioEnhanceGroup;
    private int attackBonus;
    private int strMod;
    private int damageBonus;
    TextView txtAttack, txtDamage;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ActionBar mActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menu_drawer);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        ViewStub stub = (ViewStub) findViewById(R.id.view_stub_menu_drawer);
        stub.setLayoutResource(R.layout.activity_attack);
        View inflated = stub.inflate();
        mActionBar = getSupportActionBar();

        if(mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowTitleEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            mActionBar.setTitle("Calculate Bonuses");
        }

        bab = BuffManager.getBab();
        level = BuffManager.getCasterLevel();
        strMod = 0;

        rdb2Hand = (RadioButton) findViewById(R.id.rbtn_2hand);
        cbxPow = (CheckBox) findViewById(R.id.cbx_powerAttack);
        cbxTwoWeapon = (CheckBox) findViewById(R.id.cbx_twoWeapon);
        dedicated = (CheckBox)findViewById(R.id.cbx_dedicatedAdversary);
        focus = (CheckBox)findViewById(R.id.cbx_weaponFocus);
        weaponSpec = (CheckBox)findViewById(R.id.cbx_weaponSpecilization);
        weaponTrain = (CheckBox)findViewById(R.id.cbx_weaponTraining);
        radioEnhanceGroup = (RadioGroup)findViewById(R.id.rgroup_enhance);


        NavigationView navigationView = (NavigationView) findViewById(R.id.menu_nav_view);

        if(navigationView != null) {
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            int id = menuItem.getItemId();
                            Intent intent = null;
                            if(id == R.id.nav_active)
                            {

                            }
                            if(id == R.id.nav_stats)
                            {

                            }
                            if(id == R.id.nav_search_spells)
                            {

                            }
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            if(intent != null)
                            {
                                startActivity(intent);
                            }

                            return true;
                        }
                    });
        }
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int powerAttackBonusAtt ()
    {
        int bonus = 0;
        int tempBab = bab;

        if (cbxPow.isChecked())
        {
            do {
                bonus--;
                tempBab = tempBab - 4;
            }while(tempBab >= 0);
        }

        return bonus;
    }

    private int powerAttackBonusDamage ()
    {
        int bonus = 0;
        int tempBab = bab;
        int additionalBonus = 0;

        if(rdb2Hand.isChecked())
        {
            additionalBonus = 3;
        }
        else
        {
            additionalBonus = 2;
        }

        if (cbxPow.isChecked())
        {
            do {
                bonus = bonus + additionalBonus;
                tempBab = tempBab - 4;
            }while(tempBab >= 0);
        }

        return bonus;
    }

    private int addEnhancement ()
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
            default:
                bonus = 0;
                break;
        }

        return bonus;
    }

    public void calculateBonuses(View view)
    {
        strMod = BuffManager.getModifier(Enums.Attribute.STRENGTH);

        attackBonus = ((dedicated.isChecked()) ? 2 : 0) +
                ((focus.isChecked()) ? 1 : 0) +
                strMod +
                powerAttackBonusAtt() +
                addEnhancement() +
                bab +
                ((weaponTrain.isChecked()) ? weaponTraining() : 0);
        damageBonus = ((dedicated.isChecked()) ? 2 : 0) +
                ((rdb2Hand.isChecked()) ? (strMod + (strMod/2)) : strMod) +
                ((weaponSpec.isChecked()) ? 2 : 0) +
                powerAttackBonusDamage() +
                addEnhancement() +
                ((weaponTrain.isChecked()) ? weaponTraining() : 0);

        attackBonus = attackBonus + BuffManager.addSpellBonus(Enums.BonusTo.ATTACK);
        damageBonus = damageBonus + BuffManager.addSpellBonus(Enums.BonusTo.DAMAGE);

        txtAttack = (TextView)findViewById(R.id.txt_attack_number);
        txtDamage = (TextView)findViewById(R.id.txt_damage_number);

        txtAttack.setText(attackToString(attackBonus));
        txtDamage.setText(Integer.toString(damageBonus));


    }

    private int weaponTraining ()
    {
        int bonus = 0;
        int tempLevel = level;

        do{
            if(tempLevel > 4)
            {
                bonus++;
                tempLevel = tempLevel - 4;
            }

        }while (tempLevel > 4);

        return bonus;
    }

    private String attackToString(int Bonus)
    {
        String bonusText = "";
        int TempBonus = Bonus;
        int tempBab = bab;
        int BrawlerFlurry = bab;


        if(cbxTwoWeapon.isChecked())
        {
            if (rdb2Hand.isChecked())
            {
                TempBonus = TempBonus - 4;
            }
            else
            {
                TempBonus = TempBonus - 2;
            }
        }

        do {

            bonusText = bonusText + Integer.toString(TempBonus);
            if(cbxTwoWeapon.isChecked())
            {
                if(BrawlerFlurry > 0)
                {
                    bonusText = bonusText + " / " + TempBonus;
                    BrawlerFlurry = BrawlerFlurry - 7;
                }

            }

            tempBab = tempBab - 5;
            TempBonus = TempBonus - 5;

            if(tempBab > 0)
            {
                bonusText = bonusText + " / ";
            }

        }while (tempBab > 0);

        return bonusText;
    }


}
