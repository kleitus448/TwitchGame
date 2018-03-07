package com.ludux.twitchgame.Server.Items.Kleidung;


import com.ludux.twitchgame.Server.Items.Item;
import com.ludux.twitchgame.Server.Monsters.Monster;


public abstract class Clothes extends Item {
    protected String AdditionalEffect;
    protected ClothesValues values = new ClothesValues();
    public Monster action(Monster monster){return monster;}
    public void setLevelup(int Level){
        for (int i = 0; i < Level-1; i++){this.levelUp();};}
    public void levelUp(){}

    public ClothesValues getValues() {
        return values;
    }
}
