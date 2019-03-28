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

//This class is used to add a new profile to the saved proviles
public class add_profile extends AppCompatActivity {

    EditText[] addProfileEditText = new EditText[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        setEditViews(addProfileEditText, (LinearLayout)findViewById(R.id.ll_edit_text_add_profile));
    }

    //Create the edit views and put into an array
    private void setEditViews(EditText[] arraySelected, LinearLayout llSelected)
    {
        for(int i = 0; i < arraySelected.length; i++)
        {
            EditText value = new EditText(this);
            value.setId(i);
            value.setTextSize(20);
            value.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            value.setGravity(Gravity.CENTER);
            if (i == 0) //For entering the name
            {
                value.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            }
            else //for entering only numbers
            {
                value.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
            llSelected.addView(value);
            arraySelected[i] = value;

        }
    }

    //this will add a new profile to the text file that the profiles are saved to
    public void saveProfile(View view)
    {
        String[] newPro = new String[addProfileEditText.length];
        for(int i = 0; i < addProfileEditText.length; i++)
        {
            //If a EditView is empty set the defaults as below and add to array of strings
            //else add what is in the EditView to the array of strings
            if(addProfileEditText[i].getText().toString().equals(""))
            {
                if(i == 1 || i == 2)
                {
                    newPro[i] = "1";//level and BAB
                }
                else if (i > 2)
                {
                    newPro[i] = "10"; //attributes
                }
                else
                {
                    newPro[i] = "No Name"; //name
                }
            }
            else {
                newPro[i] = String.valueOf(addProfileEditText[i].getText());
            }
        }

        //initilize variables in the creation of a profile
        String proName ="";
        int[] proAttribute = new int[6];
        int level =0;
        int bab = 0;
        char selected = 'n';
        int k = 0;

        for(int i = 0; i < newPro.length; i++)
        {
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

        //Save the profile to the txt document
        saveStatsFile(this);
        openStatsFile(this);
        start_up.activateButtons();

        //Close activity and return to previous activity
        this.finish();
    }

    //Close activity and return to previous activity
    public void doNotAdd(View view) { this.finish(); }
}
