package com.gamecodeschool.pathbuffs.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Abilities;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.AbilitiesManager;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.AllProfiles;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.BuffManager;
import com.gamecodeschool.pathbuffs.jsjf.Enums;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.FeatManager;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Feats;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Spells;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static com.gamecodeschool.pathbuffs.jsjf.Files.openFeatsFile;
import static com.gamecodeschool.pathbuffs.jsjf.Files.openStatsFile;

public class start_up extends AppCompatActivity {
    private static Button btn_setStats, btn_spell, btn_calculateBonunes, btn_activeSpells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        //Open profile saved text file
        openStatsFile(this);
        //Load and parse Spells and Feats XML
        parseSpellsXML();
        parseFeatsXML();
        openFeatsFile(this);

        //Set buttons
        btn_setStats = (Button)findViewById(R.id.btn_set_stats);
        btn_activeSpells = (Button)findViewById(R.id.btn_active_spells);
        btn_calculateBonunes = (Button)findViewById(R.id.btn_calculate_bonuses);
        btn_spell = (Button)findViewById(R.id.btn_cast_spells);

        //If no Profiles deactive buttons besides set stats button
        if(AllProfiles.currentProfile.getName().equals(""))
        {
            deactivateButtons();
        }


    }

    //Start parser
    private void parseSpellsXML()
    {
        XmlPullParserFactory parserFactory;
        try{
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("spells.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            processSpellsParsing(parser);

        }catch (XmlPullParserException e){

        }catch (IOException e)
        {

        }
    }

    //Parse through spells and add them to all spells list in the BuffManager class
    private void processSpellsParsing(XmlPullParser parser) throws IOException, XmlPullParserException{
        ArrayList<Spells> spells = new ArrayList<>();
        int eventType = parser.getEventType();
        Spells currentSpell = null;

        while(eventType != XmlPullParser.END_DOCUMENT)
        {
            String eltName = null;

            switch(eventType)
            {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();


                    if("spell".equals(eltName))
                    {
                        currentSpell = new Spells();
                        spells.add(currentSpell);
                    }else if(currentSpell != null)
                    {
                        if("name".equals(eltName)) {
                            currentSpell.name = parser.nextText();
                        }else if("duration".equals(eltName)) {
                            currentSpell.duration = Enums.getDuration(Integer.parseInt(parser.nextText()));
                        }else if ("bonus_to".equals(eltName)){
                            currentSpell.bonus_to = Enums.getBonusTo(Integer.parseInt(parser.nextText()));
                        }else if("bonus".equals(eltName)){
                            currentSpell.bonus = Integer.parseInt(parser.nextText());
                        }else if ("scaling".equals(eltName)){
                            currentSpell.scaling = Enums.getScaling(Integer.parseInt(parser.nextText()));
                        }else if("type".equals(eltName)){
                            currentSpell.type = Enums.getType(Integer.parseInt(parser.nextText()));
                        }else if("attribute".equals(eltName)){
                            currentSpell.attribute = Enums.getAttribute(Integer.parseInt(parser.nextText()));
                        }else if("class_list".equals(eltName)) {
                            //Adds all classes as enums
                            String classMaster = parser.nextText();
                            String[] classArray = classMaster.split(",");
                            for(String s: classArray)
                            {
                                currentSpell.classLists.add(Enums.getClassList(s));
                            }
                        }else if("speed".equals(eltName)){
                            currentSpell.speed = Enums.getSpeed(Integer.parseInt(parser.nextText()));
                        }
                    }
                    break;
            }

            eventType = parser.next();
        }

        BuffManager.setAllSpellsList(spells);
    }

    //Start Parser for feats
    private void parseFeatsXML()
    {
        XmlPullParserFactory parserFactory;
        try{
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("feats.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            processFeatsParsing(parser);

        }catch (XmlPullParserException e){

        }catch (IOException e)
        {

        }
    }

    private void processFeatsParsing(XmlPullParser parser) throws IOException, XmlPullParserException{
        ArrayList<Feats> feats = new ArrayList<>();
        int eventType = parser.getEventType();
        Feats currentFeat = null;

        while(eventType != XmlPullParser.END_DOCUMENT)
        {
            String eltName = null;

            switch(eventType)
            {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();


                    if("feat".equals(eltName))
                    {
                        currentFeat = new Feats();
                        feats.add(currentFeat);
                    }else if(currentFeat != null)
                    {
                        if("name".equals(eltName)) {
                            currentFeat.name = parser.nextText();
                        }else if ("bonus_to".equals(eltName)){
                            currentFeat.bonusTo = Enums.getBonusTo(Integer.parseInt(parser.nextText()));
                        }else if("bonus".equals(eltName)){
                            currentFeat.bonus = Integer.parseInt(parser.nextText());
                        }
                    }
                    break;
            }

            eventType = parser.next();
        }
        FeatManager.setAllFeatsList(feats);
    }
/*    private void parseAbilitiesXML()
    {
        XmlPullParserFactory parserFactory;
        try{
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("abilities.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            processAbilitiesParsing(parser);

        }catch (XmlPullParserException e){

        }catch (IOException e)
        {

        }
    }
    private void processAbilitiesParsing(XmlPullParser parser) throws IOException, XmlPullParserException{
        ArrayList<Abilities> abilities = new ArrayList<>();
        int eventType = parser.getEventType();
        Abilities currentAbility = null;

        while(eventType != XmlPullParser.END_DOCUMENT)
        {
            String eltName = null;

            switch(eventType)
            {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();


                    if("ability".equals(eltName))
                    {
                        currentAbility = new Abilities();
                        abilities.add(currentAbility);
                    }else if(currentAbility != null)
                    {
                        if("name".equals(eltName)) {
                            currentAbility.name = parser.nextText();
                        }else if ("bonus_to".equals(eltName)){
                            currentAbility.bonusTo = Enums.getBonusTo(Integer.parseInt(parser.nextText()));
                        }else if("bonus".equals(eltName)){
                            currentAbility.bonus = Integer.parseInt(parser.nextText());
                        }else if("type".equals(eltName)){
                            currentAbility.type = Enums.getType(Integer.parseInt(parser.nextText()));
                        }
                    }
                    break;
            }

            eventType = parser.next();
        }
        AbilitiesManager.setAllAbilitiesList(abilities);
    }*/

//If there is a profile saved activate Buttons
    public static void activateButtons()
    {
        btn_spell.setAlpha(1f);
        btn_spell.setClickable(true);
        btn_activeSpells.setAlpha(1f);
        btn_activeSpells.setClickable(true);
        btn_calculateBonunes.setAlpha(1f);
        btn_calculateBonunes.setClickable(true);
    }
    //If there are no profiles deactivate all buttons but set stats
    public void deactivateButtons()
    {
        btn_spell.setAlpha(.5f);
        btn_spell.setClickable(false);
        btn_activeSpells.setAlpha(.5f);
        btn_activeSpells.setClickable(false);
        btn_calculateBonunes.setAlpha(.5f);
        btn_calculateBonunes.setClickable(false);

    }

    //On button click load new activity
    public void gotToActivity(View view) {
        Intent intent = null;

        if(btn_activeSpells.getId() == view.getId())
        {
            intent = new Intent(start_up.this, BuffTrackingActivity.class);
        }else if(btn_calculateBonunes.getId() == view.getId())
        {
            intent = new Intent(start_up.this, allFeats.class);
        }else if(btn_setStats.getId() == view.getId())
        {
            intent = new Intent(start_up.this, Character.class);
        }else if(btn_spell.getId() == view.getId())
        {
            intent = new Intent(start_up.this, BuffTrackingActivity.class);
        }
        
        if(intent != null)
        {
            startActivity(intent);
        }
    }
}
