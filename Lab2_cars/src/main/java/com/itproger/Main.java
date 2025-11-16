package com.itproger;
import car_pack.Car;
import java.util.Scanner;
import java.time.LocalDate; //щоб отримати поточний рік

public class Main {
    public static void main(String[] args) {
        System.out.println("Lab 2: Cars.24!");

        // Масив об'єктів
        Car[] carsAr = {
                new Car(1, 2011, "bmw", 15000, "aa6364ls"),
                new Car(2, 2015, "dssil", 18300, "mo9247vb"),
                new Car(3, 2018, "ar", 19100, "go4693bv"),
                new Car(4, 2020, "fn,ui", 21000, "an894sa"),
                new Car(5, 2014, "bmw", 14000, "pi9205cn"),
                new Car(6, 2019, "bmw", 16000, "pa1090nv")
        };

        Scanner scan = new Scanner(System.in);
        System.out.println("Доступні моделі: ");
        ModelList(carsAr);

        System.out.println();
        System.out.print("Введіть модель машини для пошуку: ");
        String model = scan.nextLine();

        findModel(carsAr, model);

        System.out.println("Список автомобілів заданої моделі, які експлуатуються більше п років");
        System.out.print("Введіть модель: ");
        String modelForAge = scan.nextLine();
        System.out.print("Скільки років машина експлуатується (більше n): ");
        int n_years = scan.nextInt();

        findModelAndYear(carsAr, modelForAge, n_years);


        System.out.print("Введіть рік випуску для пошуку: ");
        int targetYear = scan.nextInt();
        System.out.print("Введіть ціну: ");
        int minPrice = scan.nextInt();

        findYearAndPrice(carsAr, targetYear, minPrice);

        scan.close(); // Завжди закривай сканер в кінці
    }







    public static void findModel(Car[] cars, String model) {
        System.out.println("\n(a) Пошук моделі: " + model);
        boolean found = false; // Змінна-прапорець, щоб знати, чи знайшли щось

        for (Car car : cars) {
            // .equalsIgnoreCase() порівнює рядки без урахування "bmw" == "BMW"
            if (car.getModel().equalsIgnoreCase(model)) {
                // Використовуємо метод toString(), який ми визначили в Car
                System.out.println(car);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Автомобілів моделі '" + model + "' не знайдено.");
        }
    }


    public static void ModelList(Car[] cars) {
        for (Car car : cars) {
            System.out.print(car.getModel());
            System.out.println();
        }
    }


    public static void findModelAndYear(Car[] cars, String model, int yearsOld) {
        System.out.println("\n(b) Пошук моделі: " + model + ", що експлуатується більше " + yearsOld + " років");

        int currentYear = LocalDate.now().getYear();
        boolean found = false;

        for (Car car : cars) {
            int carAge = currentYear - car.getYear();
            // Перевіряємо обидві умови:
            // 1. Модель збігається (&& означає "І")
            // 2. Вік машини (>) 'yearsOld'
            if (car.getModel().equalsIgnoreCase(model) && carAge > yearsOld) {
                System.out.println(car.toString() + " (Вік: " + carAge + " )");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Автомобілів '" + model + "' старших за " + yearsOld + " неає.");
        }
    }

    /**
     список автомобілів заданого року випуску, ціна яких більше вказаної
     */
    public static void findYearAndPrice(Car[] cars, int year, int minPrice) {
        System.out.println("\nПошук за " + year + " роком, дорожчі за " + minPrice);
        boolean found = false;

        for (Car car : cars) {
            // Перевіряємо обидві умови:
            // 1. Рік випуску збігається (==)
            // 2. Ціна *більше* (>) 'minPrice'
            if (car.getYear() == year && car.getPrice() > minPrice) {
                System.out.println(car.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Автомобілів " + year + " р. дорожчих за " + minPrice + " не знайдено.");
        }
    }
}