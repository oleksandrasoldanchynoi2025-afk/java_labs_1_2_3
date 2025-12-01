package com.itproger.gameservice;
import com.itproger.droid.*;
import com.itproger.weapon.*;
import java.io.*;
import java.util.*;

public class Game {
    private List<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private String lastBattleLog = ""; // Тут зберігаємо текст останнього бою

    public void runGame() {
        while (true) {
            System.out.println("\n=== 🤖 DROID BATTLE MENU ===");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Почати бій (1 на 1)");
            System.out.println("4. Записати бій у файл");
            System.out.println("5. Відтворити бій з файлу");
            System.out.println("6. Вихід");

            switch (scanner.nextLine()) {
                case "1" -> createDroid();
                case "2" -> showDroids();
                case "3" -> startBattle();
                case "4" -> saveBattleToFile();
                case "5" -> loadBattleFromFile();
                case "6" -> System.exit(0);
                default -> System.out.println("Невідома команда.");
            }
        }
    }

    private void createDroid() {
        System.out.println("Оберіть тип дроїда:");
        System.out.println("1. Berserker (Має шанс на Ультра-атаку)");
        System.out.println("2. Tank (Точний і живучий)");
        String type = scanner.nextLine();

        System.out.print("Введіть ім'я: ");
        String name = scanner.nextLine();

        // Вибір зброї
        Weapon weapon = chooseWeapon();

        if (type.equals("1")) {
            droids.add(new Berserker(name, weapon));
        } else {
            droids.add(new TankDroid(name, weapon));
        }
        System.out.println("✅ Дроїд успішно створений!");
    }

    private Weapon chooseWeapon() {
        System.out.println("Оберіть зброю:");
        System.out.println("1. Бластер (Стандарт)");
        System.out.println("2. Вогонь (Потужний, але косий)");
        System.out.println("3. Електро-шок (Точний)");
        System.out.println("4. Рука дроїда (Слабка, але дає ЩИТ)");

        return switch (scanner.nextLine()) {
            case "2" -> new Fire();
            case "3" -> new Electric();
            case "4" -> new DroidHand();
            default -> new Blaster(); // За замовчуванням
        };
    }

    private void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i));
        }
    }

    private void startBattle() {
        if (droids.size() < 2) {
            System.out.println("❌ Треба мінімум 2 дроїди для бою!");
            return;
        }

        showDroids();
        System.out.print("Оберіть номер бійця 1: ");
        int idx1 = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.print("Оберіть номер бійця 2: ");
        int idx2 = Integer.parseInt(scanner.nextLine()) - 1;

        // ВАЖЛИВО: Робимо копії, щоб оригінали не вмерли назавжди
        Droid fighter1 = droids.get(idx1).copyForBattle();
        Droid fighter2 = droids.get(idx2).copyForBattle();

        // Створюємо команди (для 1 на 1 це списки з одного елемента)
        List<Droid> teamA = new ArrayList<>();
        teamA.add(fighter1);

        List<Droid> teamB = new ArrayList<>();
        teamB.add(fighter2);

        // Запускаємо бій
        Battle battle = new Battle(teamA, teamB);
        lastBattleLog = battle.start(); // Отримуємо результат і зберігаємо в змінну
    }

    private void saveBattleToFile() {
        if (lastBattleLog.isEmpty()) {
            System.out.println("Ще не було битв для збереження.");
            return;
        }
        try (PrintWriter out = new PrintWriter("battle_log.txt")) {
            out.println(lastBattleLog);
            System.out.println("💾 Бій збережено у файл 'battle_log.txt'");
        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
        }
    }

    private void loadBattleFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("battle_log.txt"))) {
            String line;
            System.out.println("\n--- 📜 ІСТОРІЯ БОЮ ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Файл не знайдено.");
        }
    }
}