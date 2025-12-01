package com.itproger.droid;
import com.itproger.weapon.Weapon;
import java.util.Random;

public abstract class Droid {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int accuracy;
    protected Weapon weapon; // Головне поле для зброї
    protected Random rnd = new Random();

    // КОНСТРУКТОР: Приймає лише Ім'я, Життя та Зброю
    public Droid(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.weapon = weapon;
    }

    // Геттери
    public String getName() { return name; }
    public int getHealth() { return health; }
    public boolean isAlive() { return health > 0; }
    public Weapon getWeapon() { return weapon; }

    // Метод отримання удару
    public void getHit(int dmg) {
        // Беремо захист зі зброї (наприклад, з Руки)
        int defense = weapon.getDefense();
        int actualDmg = dmg - defense;

        if (actualDmg < 0) actualDmg = 0;

        this.health -= actualDmg;
        if (this.health < 0) this.health = 0;

        System.out.println(name + " отримав " + actualDmg + " урону (Заблоковано: " + defense + ")");
    }

    public abstract int attack(Droid enemy);

    public abstract Droid copyForBattle();

    @Override
    public String toString() {
        // %s — для виводу рядка (toString зброї)
        return String.format("🤖 %-10s | ❤️ %3d/%-3d | %s",
                name, health, maxHealth, weapon);
    }
}