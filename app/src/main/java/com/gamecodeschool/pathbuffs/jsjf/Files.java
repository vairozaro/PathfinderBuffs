package com.gamecodeschool.pathbuffs.jsjf;

import android.content.Context;
import android.widget.Toast;

import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Abilities;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Feats;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.AllProfiles;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.Profile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Files {

    static final String STATSFILENAME = "SavedStats.txt";
    static final String PROFILEFEATSFILE = "ProfileFeats.txt";
    static final String PROFILEABILITIESFILE = "ProfileAbilities.txt";
    private static LinkedList<Profile> profilesToSave = new LinkedList<>();

    public static void saveStatsFile(Context ctx)
    {
        profilesToSave = AllProfiles.getAllprofiles();
        String str = "";
        int[] tempAttribute;
        try {
            FileOutputStream fOut = ctx.openFileOutput(STATSFILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fOut);
            //Name, Str, Dex, Con, Int, Wis, Cha, Level, BAB, y/n for default profile
            for(Profile x: profilesToSave)
            {
                str = x.getName() + ",";
                tempAttribute = x.getAttirbute();

                for(int i = 0; i < tempAttribute.length; i++)
                {
                    str = str + Integer.toString(tempAttribute[i]) + ",";
                }
                str = str + x.getLevel() + ","
                        + x.getBab() + ","
                        + x.getSelected() + "\n";
                outputWriter.write(str);
            }
                        outputWriter.close();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveFeatsFile(Context ctx)
    {
        profilesToSave = AllProfiles.getAllprofiles();
        String str = "";
        ArrayList<Feats> feats;

        try {
            FileOutputStream fOut = ctx.openFileOutput(PROFILEFEATSFILE, Context.MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fOut);

            for(Profile x: profilesToSave)
            {
                feats = x.getFeats();
                str = x.getName() + ",";

                for(Feats f: feats)
                {
                    str = str + f.getName() + ",";
                }
                str = str + "\n";
                outputWriter.write(str);
            }
            outputWriter.close();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openStatsFile(Context ctx)
    {
        AllProfiles.clearAllProfiles();
        try {
            FileInputStream fis = ctx.openFileInput(STATSFILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();


            while (line != null)
            {
                String[] newPro = line.split(",");
                String proName ="";
                int[] proAttribute = new int[6];
                int level =0;
                int bab = 0;
                char selected = 'z';

                for(int i = 0; i < newPro.length; i++)
                {
                    //proAttribute[i] = Integer.parseInt(newPro[i+1]);
                    switch (i){
                        case 0: //name
                            proName = newPro[i];
                            break;
                        case 1: //attributes
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            proAttribute[i-1] = Integer.parseInt(newPro[i]);
                            break;
                        case 7: // level
                            level = Integer.parseInt(newPro[7]);
                            break;
                        case 8: // bab
                            bab = Integer.parseInt(newPro[8]);
                            break;
                        case 9: //current profile
                            selected = newPro[9].charAt(0);
                            break;
                        default:
                            break;
                    }
                }

                AllProfiles.addProfile(new Profile(proName, proAttribute, level, bab, selected));
                line = reader.readLine();
            }
            if(AllProfiles.getAllprofiles().size() == 0)
            {
                AllProfiles.addProfile(new Profile("", AllProfiles.defaultprofile, 1, 1, 'y'));
            }
            reader.close();
            isr.close();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void openFeatsFile(Context ctx)
    {

        try {
            FileInputStream fis = ctx.openFileInput(PROFILEFEATSFILE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();


            while (line != null)
            {
                String[] newPro = line.split(",");
                String proName ="";

                proName = newPro[0];
                for(int i = 1; i < newPro.length; i++)
                {
                    AllProfiles.addFeatToProfile(proName, newPro[i]);
                }

                line = reader.readLine();
            }

            reader.close();
            isr.close();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
