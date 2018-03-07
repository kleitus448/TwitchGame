package com.ludux.twitchgame.notinuse;

import com.ludux.twitchgame.Server.Connection.PreparingBattle;

public class Battle {



    public synchronized PreparingBattle logic(PreparingBattle battle, String Action){
        switch (Action) {
            case "Attack":
                battle.Second.setHealth(battle.Second.getHealth() - battle.First.getBaseForceAttack());

                break;


            //case "Heal":
            //    first.Health += new HealingRing().val;
        }
        return battle;
    }
}
