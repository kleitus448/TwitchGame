package com.ludux.twitchgame.Server.Connection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Server.Items.Item;
import com.ludux.twitchgame.Server.Items.Kleidung.BrokenBlade;
import com.ludux.twitchgame.Server.Items.Kleidung.OldHauberk;
import com.ludux.twitchgame.Server.Items.Magic.HealingRing;


import java.lang.reflect.Type;
import java.util.ArrayList;

public class Rebuilder {

    public Rebuilder(){}

    public String ArrayToString(ArrayList<String> inputArray){
        Gson gson = new Gson();
        String inputString = gson.toJson(inputArray);
        return inputString;
    }

    public ArrayList<String> StringToArrayList(String outputarray){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(outputarray, type);
    }

    public ArrayList<Item> Convert(ArrayList<String> inArray){
        ArrayList<Item> outList = new ArrayList<>();
        for (String element :inArray) {
            String item = element.split("_")[0];
            String lvl = element.split("_")[1];
            System.out.println(item);

            switch (item){
                case Const.HEAL_RING:
                    HealingRing ring = new HealingRing();
                    ring.setLevel(Integer.parseInt(lvl));
                    outList.add(ring);
                    break;

                case Const.BROKEN_BLADE:
                    BrokenBlade brokenBlade = new BrokenBlade();
                    brokenBlade.setLevelup(Integer.parseInt(lvl));
                    outList.add(brokenBlade);

                case Const.OLD_HAUBERK:
                    OldHauberk oldHauberk = new OldHauberk();
                    oldHauberk.setLevelup(Integer.parseInt(lvl));
                    outList.add(oldHauberk);
                    System.out.println(oldHauberk.getValues().getHealthValue());

            }
        }
        return outList;
    }

    public ArrayList<String> DeConvert(ArrayList<Item> inArray){
        ArrayList<String> out = new ArrayList<>();

        for (Item item :inArray) {
            if (item instanceof HealingRing){
                out.add(Const.HEAL_RING + "_" + Integer.toString(item.getLevel()));
            }else if(item instanceof BrokenBlade){
                out.add(Const.BROKEN_BLADE + "_" + Integer.toString(item.getLevel()));
            }else if(item instanceof OldHauberk){
                out.add(Const.OLD_HAUBERK + "_" + Integer.toString(item.getLevel()));
            }
        }
        return out;
    }
}
