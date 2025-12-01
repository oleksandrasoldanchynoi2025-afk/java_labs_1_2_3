package com.itproger.gameservice;
import com.itproger.droid.Droid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle {
    private List<Droid> teamA;
    private List<Droid> teamB;
    private Random rnd = new Random();
    private StringBuilder battleLog = new StringBuilder();
    private int currentRound = 1;

    public Battle(List<Droid> teamA, List<Droid> teamB) {
        // Створюємо копії списків, щоб не ламати оригінали
        this.teamA = new ArrayList<>(teamA);
        this.teamB = new ArrayList<>(teamB);
    }

    public String start() {
        log("--- ПОЧАТОК БИТВИ ---");
        log("Команда A: " + getTeamNames(teamA));
        log("Команда B: " + getTeamNames(teamB));

        // Головний цикл бою (поки в обох командах є живі)
        while (hasAlive(teamA) && hasAlive(teamB)) {
            log("\n--- Раунд " + currentRound + " ---");

            // Хід команди А
            doTurn(teamA, teamB);
            if (!hasAlive(teamB)) break; // Якщо В померли, виходимо

            // Хід команди В
            doTurn(teamB, teamA);

            currentRound++;
            if (currentRound > 100) {
                log("Бій зупинено: занадто довго (100 раундів).");
                break;
            }
        }

        declareWinner();
        return battleLog.toString();
    }

    // Логіка одного ходу для команди
    private void doTurn(List<Droid> attackers, List<Droid> defenders) {
        for (Droid attacker : attackers) {
            if (!attacker.isAlive()) continue; // Мертві не б'ють

            Droid target = getRandomTarget(defenders);
            if (target == null) return; // Якщо нікого бити

            // 1. Розрахунок атаки (тут спрацює Ульта або Крит, якщо це Берсерк)
            int dmg = attacker.attack(target);

            // 2. Нанесення урону (тут спрацює захист броні або зброї)
            target.getHit(dmg);

            // 3. Запис у лог
            if (dmg > 0) {
                log(attacker.getName() + " завдав " + dmg + " урону по " + target.getName());
            } else {
                log(attacker.getName() + " промахнувся по " + target.getName());
            }

            if (!target.isAlive()) {
                log("💀 " + target.getName() + " знищено!");
            }
        }
    }

    // Вибір випадкової живої цілі
    private Droid getRandomTarget(List<Droid> team) {
        List<Droid> aliveTargets = new ArrayList<>();
        for (Droid d : team) {
            if (d.isAlive()) aliveTargets.add(d);
        }
        if (aliveTargets.isEmpty()) return null;
        return aliveTargets.get(rnd.nextInt(aliveTargets.size()));
    }

    // Перевірка, чи є живі в команді
    private boolean hasAlive(List<Droid> team) {
        for (Droid d : team) {
            if (d.isAlive()) return true;
        }
        return false;
    }

    // Оголошення переможця
    private void declareWinner() {
        log("\n--- РЕЗУЛЬТАТ ---");
        if (hasAlive(teamA)) {
            log("🏆 Перемогла Команда A!");
        } else if (hasAlive(teamB)) {
            log("🏆 Перемогла Команда B!");
        } else {
            log("🤝 Нічия!");
        }

        // Вивід статистики тих, хто вижив
        System.out.println(battleLog.toString());
    }


    private void log(String message) {
        battleLog.append(message).append("\n");
    }

    private String getTeamNames(List<Droid> team) {
        StringBuilder sb = new StringBuilder();
        for (Droid d : team) sb.append(d.getName()).append(" ");
        return sb.toString();
    }
}