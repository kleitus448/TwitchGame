package com.ludux.twitchgame.Server.Items.Kleidung;

import com.ludux.twitchgame.Server.Monsters.Monster;

public class BrokenBlade extends Clothes {

    public BrokenBlade(){
        this.AdditionalEffect = null;
        this.values.setAttackValue(10);
    }

    @Override
    public void levelUp() {
        this.setLevel(this.getLevel()+1);
        this.values.setAttackValue(this.values.getAttackValue() + 5);
    }

    @Override
    public Monster action(Monster monster) {
        monster.setBaseForceAttack(monster.getBaseForceAttack()+this.values.getAttackValue());
        return monster;
    }
}
