package com.itproger.droid;
import com.itproger.weapon.Weapon;

public class TankDroid extends Droid {

    public TankDroid(String name) {
        // HP: 150, DMG: 12, Acc: 90%, Crit: 5%
        super(name, 150, 12, 90, 5);
    }

    @Override
    public int attack(Droid enemy) {
        if (rnd.nextInt(100) > this.accuracy) {
            System.out.println(this.name + " незграбно промахнувся.");
            return 0;
        }

        boolean isCrit = rnd.nextInt(100) < this.critChance;
        return isCrit ? this.damage * 2 : this.damage;
    }

    @Override
    public Droid copyForBattle() {
        return new TankDroid(this.name, this.weapon);
    }

}