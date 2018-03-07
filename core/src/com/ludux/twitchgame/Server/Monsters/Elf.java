package com.ludux.twitchgame.Server.Monsters;
import com.ludux.twitchgame.Server.Items.Item;
import com.ludux.twitchgame.Server.Items.Magic.MagicItem;
import com.ludux.twitchgame.Server.JobWithDATABASE.Search;
import java.io.Serializable;
import java.util.ArrayList;

public class Elf extends Monster implements Serializable {
    public int getHP(){return this.getHealth();}
    public void setHP(int HP){this.setHealth(HP);}
    public void setHPMax(int HPMax){this.setHealthMax(HPMax);}
    public void setAttack(int attack){this.setBaseForceAttack(attack);}
    public void setName(String name){this.setname(name);}
    public void setAggressive(int Agressive){this.setAgressive(Agressive);}
    public void setDefend(int value){this.setBaseForceDefend(value);}
    public int getAggressive(){return this.getAgressive();}

    public Elf(String name){
        setAttack(100);
        setName(name);
        setHP(1000);
        setHPMax(this.getHP());
        setAggressive(10);
        setDefend(10);
        this.setLiesInTheInventory(new Search().searchItems(name).Inventory);
        ArrayList<MagicItem> tmp = new ArrayList<>();
        for (Item item:this.getLiesInTheInventory()){
            if(item instanceof MagicItem){
                tmp.add((MagicItem) item);
            }
        }
        System.out.println(tmp);
        this.setUsableItems(tmp);

    }

}
