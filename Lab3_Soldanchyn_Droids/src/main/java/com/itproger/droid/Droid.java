package com.itproger.droid;
import java.util.Random;

public abstract class Droid {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int accuracy;
    protected int damage;
    protected Random rnd = new Random();

    // КОНСТРУКТОР: Приймає лише Ім'я, Життя та Зброю
    public Droid(String name, int health, int accuracy, int damage) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.accuracy = accuracy;
        this.damage = damage;
    }

    // Геттери
    public String getName() { return name; }
    public int getHealth() { return health; }
    public boolean isAlive() { return health > 0; }
    // Метод отримання удару
    public void getHit(int dmg) {
        int actualDmg = dmg;

        if (actualDmg < 0) actualDmg = 0;

        this.health -= actualDmg;
        if (this.health < 0) this.health = 0;

        System.out.println(name + " отримав " + actualDmg + " урону");
    }

    public abstract int attack(Droid enemy);

    public abstract Droid copyForBattle();

    @Override
    public String toString() {
        // %s — для виводу рядка (toString зброї)
        return String.format("🤖 %-10s | ❤️ %3d/%-3d",
                name, health, maxHealth);
    }
}