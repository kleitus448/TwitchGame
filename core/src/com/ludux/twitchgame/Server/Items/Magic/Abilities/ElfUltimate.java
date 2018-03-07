package com.ludux.twitchgame.Server.Items.Magic.Abilities;

import com.ludux.twitchgame.Server.Items.Magic.MagicItem;
import com.ludux.twitchgame.Server.Monsters.Monster;

import java.util.ArrayList;

public class ElfUltimate extends MagicItem {
    public String type;

    public ElfUltimate(){

        name = "Ultimate";
        act = "Ultimate";
        val = 30;
        manacost = 30;
        MaxCooldown = 6;
        Cooldown = 0;
    }

    @Override
    public ArrayList<Monster> action(Monster current, Monster attacked){
        if (this.Cooldown == 0){
            if (current.getMana() >= this.manacost){
                attacked.TakenDamage(current.getUltimate().getVal());
                current.ManaCostUsing(current.getUltimate().getManacost());
            }else{current.setError("Не хватает маны");}
        }else{current.setError("Cooldown");}
        ArrayList<Monster> out = new ArrayList<>();
        out.add(current);
        out.add(attacked);
        return out;
    }
}
