package com.gamecodeschool.pathbuffs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/**
 * Created by Jason on 5/5/2018.
 */

public class BuffManager
{
    private Buff[] buffList = new Buff[10];

    public void addBuff(String n, char t, char bT, int b, int r)
    {
        buffList[0] = new Buff(n, t, bT, b, r);
    }

    public String update()
    {
        int bonus = buffList[0].getBonus();

        return Integer.toString(bonus);
    }
}
