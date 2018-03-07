package com.ludux.twitchgame.Server.Items.Magic.Abilities;

import com.ludux.twitchgame.Server.Items.Magic.MagicItem;
import com.ludux.twitchgame.Server.Monsters.Monster;

import java.util.ArrayList;

public class BaseAttack extends MagicItem {
    public BaseAttack(){
        setMaxCooldown(1);
        setCooldown(0);
        enable = false;
    }

    @Override
    public ArrayList<Monster> action(Monster current, Monster attacked) {
        if (this.Cooldown==0){
            this.setCooldown(this.getMaxCooldown());
            this.enable = true;

        }else if (this.enable == true) {
            this.enable = false;
            System.out.println("ЗАЩИТА БИЛЯТЬ " + attacked.getBaseForceDefend());
            int dmg = (int)Math.round(current.getBaseForceAttack() * (1 - 0.05*attacked.getBaseForceDefend() / (1 + (0.05 * Math.abs(attacked.getBaseForceDefend())))));
            System.out.println(dmg);
            attacked.TakenDamage(dmg);
        }
        else{
            current.setError("Вы уже атаковали.");
        }
        ArrayList<Monster> out = new ArrayList<>();
        out.add(current);
        out.add(attacked);
        return out;
    }
}
