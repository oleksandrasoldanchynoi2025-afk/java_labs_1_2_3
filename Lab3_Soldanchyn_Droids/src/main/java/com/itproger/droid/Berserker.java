package com.itproger.droid;
import com.itproger.weapon.Weapon;

public class Berserker extends Droid {

    public Berserker(String name, Weapon weapon) {
        super(name, 100, weapon); // Передаємо це батьківському класу Droid
    }
    @Override
    public int attack(Droid enemy) {
        // 1. Перевірка на промах
        if (rnd.nextInt(100) > this.accuracy) {
            System.out.println(this.name + " промахнувся!");
            return 0;
        }


        if (rnd.nextDouble() < 0.10) {
            System.out.println(">>> " + this.name + " ВИКОРИСТОВУЄ УЛЬТРА-АТАКУ! <<<");
            int ultraDamage = (int) (enemy.getHealth() * 0.8);
            return Math.max(ultraDamage, 50);
        }


        boolean isCrit = rnd.nextInt(100) < this.critChance;
        int actualDamage = isCrit ? this.damage * 2 : this.damage;

        if (isCrit) System.out.print(" (CRITICAL!) ");

        return actualDamage;
    }

    @Override
    public Droid copyForBattle() {
        return new Berserker(this.name);
    }

}
