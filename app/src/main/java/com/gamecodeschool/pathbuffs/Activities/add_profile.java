package com.gamecodeschool.pathbuffs.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.AllProfiles;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.Profile;

import static com.gamecodeschool.pathbuffs.jsjf.Files.*;

public class add_profile extends AppCompatActivity {

    EditText[] addProfileEditText = new EditText[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        setEditViews(addProfileEditText, (LinearLayout)findViewById(R.id.ll_edit_text_add_profile));
    }

    private void setEditViews(EditText[] arraySelected, LinearLayout llSelected)
    {
        for(int i = 0; i < arraySelected.length; i++)
        {
            EditText value = new EditText(this);
            value.setId(i);
            value.setTextSize(20);
            value.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            value.setGravity(Gravity.CENTER);
            if (i == 0)
            {
                value.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            }
            else
            {
                value.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
            llSelected.addView(value);
            arraySelected[i] = value;

        }
    }

    public void saveProfile(View view)
    {
        String[] newPro = new String[addProfileEditText.length];
        for(int i = 0; i < addProfileEditText.length; i++)
        {
            if(addProfileEditText[i].getText().toString().equals(""))
            {
                if(i == 1 || i == 2)
                {
                    newPro[i] = "1";
                }
                else if (i > 2)
                {
                    newPro[i] = "10";
                }
                else
                {
                    newPro[i] = "No Name";
                }
            }
            else {
                newPro[i] = String.valueOf(addProfileEditText[i].getText());
            }
        }

        String proName ="";
        int[] proAttribute = new int[6];
        int level =0;
        int bab = 0;
        char selected = 'n';
        int k = 0;

        for(int i = 0; i < newPro.length; i++)
        {
            //proAttribute[i] = Integer.parseInt(newPro[i+1]);
            switch (i){
                case 0: //name
                    proName = newPro[i];
                    break;
                case 1:// level
                    level = Integer.parseInt(newPro[i]);
                    break;//attributes
                case 2:// bab
                    bab = Integer.parseInt(newPro[i]);
                    break;
                case 3://str
                case 4://dex
                case 5://con
                case 6://int
                case 7: //wis
                case 8: //cha
                    proAttribute[k] = Integer.parseInt(newPro[i]);
                    k++;
                    break;
                default:
                    break;
            }
        }

        AllProfiles.addProfile(new Profile(proName, proAttribute, level, bab, selected));

        saveStatsFile(this);
        openStatsFile(this);
        start_up.activateButtons();

        this.finish();
    }

    public void doNotAdd(View view) { this.finish(); }
}
