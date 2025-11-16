package com.itproger;

public class LukeSkywalker {

    public int getLukeNumber(int n) {
        int first = 2;
        int second = 1;
        int next = 0;
        if (n == 0){
            return 2;
        } else if (n == 1){
            return 1;
        }

        for (int i=2; i<=n; i++) {
            next = second + first;
            first = second;
            second = next;
        }
        return next;
    }
/* то шо нижче - не робоче,
ну, воно працює, але не так як треба*/


//    public int getKvadratSumu (int N){
//        int sum = 0;
//        for (int i= 0; i<N;i++){
//            int LukeAgain = getLukeNumber(i);
//            System.out.println("_____ " + LukeAgain);
//            sum += LukeAgain*LukeAgain;
//            System.out.println("sum_____ "+ sum);
//        }
//        System.out.println("Сума квадратів сил юнглінгів Люка дорівнює "+ sum);
//    return sum;
//    }
}
