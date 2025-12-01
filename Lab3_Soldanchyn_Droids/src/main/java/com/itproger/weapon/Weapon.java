package com.itproger.weapon;

public abstract class Weapon {
    private String name;
    private int damage;
    private int accuracy; // Шанс влучити (0-100)
    private int defense;  // Зменшення урону по власнику (щит)

    public Weapon(String name, int damage, int accuracy, int defense) {
        this.name = name;
        this.damage = damage;
        this.accuracy = accuracy;
        this.defense = defense;
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public int getAccuracy() { return accuracy; }
    public int getDefense() { return defense; }

    @Override
    public String toString() {
        return name + " (⚔️" + damage + ", 🛡️" + defense + ")";
    }
}