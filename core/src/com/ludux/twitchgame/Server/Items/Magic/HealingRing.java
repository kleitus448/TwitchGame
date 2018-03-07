package com.ludux.twitchgame.Server.Items.Magic;

import com.ludux.twitchgame.Server.Connection.PreparingBattle;
import com.ludux.twitchgame.Server.Monsters.Monster;

import java.util.ArrayList;

public class HealingRing extends MagicItem {
    public HealingRing(){
        name = "HealingRing";
        act = "Heal";
        val = 30;
        manacost = 30;
        MaxCooldown = 4;
        Cooldown = 0;
    }

    @Override
    public ArrayList<Monster> action(Monster current, Monster attacked){
        if (this.Cooldown == 0){
            current.TakenDamage(-this.getVal());
            current.ManaCostUsing(this.getManacost());
            this.setCooldown(this.MaxCooldown);
        } else{
            current.setError("Не хватает маны");
        }
        ArrayList<Monster> out = new ArrayList<>();
        out.add(current);
        out.add(attacked);
        return out;
    };

}
