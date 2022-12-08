package twentytwentytwo.day4;


import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        int counter = 0;
        while (scanner.hasNextLine()) {
            String a = scanner.nextLine();
            String[] first = a.split(",")[0].split("-");
            String[] second = a.split(",")[1].split("-");
            if (Integer.parseInt(first[0]) >= Integer.parseInt(second[0]) && Integer.parseInt(first[0]) <= Integer.parseInt(second[1])) {
                counter++;
            } else if (Integer.parseInt(second[0]) >= Integer.parseInt(first[0]) && Integer.parseInt(second[0]) <= Integer.parseInt(first[1])) {
                counter++;
            }
            System.out.println(counter);
        }
        System.out.println(counter);
    }
}