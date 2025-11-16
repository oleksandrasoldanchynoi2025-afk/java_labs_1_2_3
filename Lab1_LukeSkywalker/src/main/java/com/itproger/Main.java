package com.itproger; //по уроку з ютубу
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello There! \n\t\t\t 'ghghg'..General Kenobi!!");

        Scanner scan = new Scanner(System.in);
        System.out.print("Введіть кількісь чуттєвих до сили дітей для Люка Скайвокера (старий канон): ");
        int N = scan.nextInt(); // N - та бо в завданні так
        int sum = 0;
        System.out.println("\nRoger, Roger... "+N+ "\n");
        LukeSkywalker LukeNumbers = new LukeSkywalker();

        int failed_array = LukeNumbers.getLukeNumber(N);
        //int sum = LukeNumbers.getKvadratSumu(N);


        //варіант 1, хардкодинг??
        for (int i=0; i<N; i++){
            int curNUM = LukeNumbers.getLukeNumber(i);
            System.out.println("Зараз це число: "+curNUM);
            sum += curNUM*curNUM;
            System.out.println(sum);
        }
        System.out.println("Сума квадратів перших чисел кількості сили в юнглінгів Люка: "+sum);


    }
}