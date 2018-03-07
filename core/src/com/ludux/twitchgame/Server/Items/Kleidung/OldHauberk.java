package com.ludux.twitchgame.Server.Items.Kleidung;

import com.ludux.twitchgame.Server.Monsters.Monster;

public class OldHauberk extends Clothes {
    public OldHauberk(){
        this.values.setArmorValue(10);
        this.values.setHealthValue(50);
        this.AdditionalEffect = null;
    }

    @Override
    public void levelUp() {
        this.setLevel(this.getLevel()+1);
        this.values.setArmorValue(this.values.getArmorValue() + 10);
        this.values.setHealthValue(this.values.getHealthValue() + 100);
    }

    @Override
    public Monster action(Monster monster) {
        monster.setHealth(monster.getHealth() + this.values.getHealthValue());
        monster.setHealthMax(monster.getHealth());
        monster.setBaseForceDefend(monster.getBaseForceDefend() + monster.getBaseForceDefend()*this.values.getArmorValue()/100);
        return monster;
    }
}
