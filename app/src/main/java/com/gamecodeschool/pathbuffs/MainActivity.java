package com.gamecodeschool.pathbuffs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.Menu;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {

    private int level = 5;
    private char alchemical = 'a';
    private char moral = 'm';
    private char enhancement = 'e';
    private char stats = 's';
    private char attackRoll = 'a';
    private char damage = 'd';
    public String bonus = "0";

    BuffManager buffs = new BuffManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textElement = (TextView) findViewById(R.id.totalToAttack);
        textElement.setText("bonus");
    }

    public void mutagenCast(View view)
    {
        String name = "Mutagen";
        int rounds = 100 * level;
        buffs.addBuff(name, alchemical, stats, 4, rounds);
        bonus = buffs.update();
        TextView textElement = (TextView) findViewById(R.id.totalToAttack);
        textElement.setText("bonus");
    }
    public String getBonus()
    {
        return bonus;
    }

}
