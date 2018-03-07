package com.ludux.twitchgame.Server.Items.Kleidung;

import java.io.Serializable;

public class ClothesValues implements Serializable {
    private int HealthValue;
    private int ManaValue;
    private int ArmorValue;
    private int AttackValue;

    public int getHealthValue() {
        return HealthValue;
    }

    public void setHealthValue(int healthValue) {
        HealthValue = healthValue;
    }

    public int getManaValue() {
        return ManaValue;
    }

    public void setManaValue(int manaValue) {
        ManaValue = manaValue;
    }

    public int getArmorValue() {
        return ArmorValue;
    }

    public void setArmorValue(int armorValue) {
        ArmorValue = armorValue;
    }

    public int getAttackValue() {
        return AttackValue;
    }

    public void setAttackValue(int attackValue) {
        AttackValue = attackValue;
    }


}
