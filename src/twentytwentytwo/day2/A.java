package twentytwentytwo.day2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(twentytwentytwo.day1.A.class.getResource("input.txt").getPath()).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        int counter = 0;
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            if (s.contains("END")) {
                break;
            }
            String[] t = s.split(" ");
            String oponent = getType(t[0]);
            String me = getType(t[1]);
            if (me.equals(oponent)) {
                counter += 3;
            } else if (
                    me.equals("R") && oponent.equals("S") ||
                            me.equals("S") && oponent.equals("P") ||
                            me.equals("P") && oponent.equals("R")

            ) {
                counter += 6;
            }

            if (me.equals("R")) {
                counter += 1;
            } else if (me.equals("P")) {
                counter += 2;
            } else if (me.equals("S")) {
                counter += 3;
            }


            System.out.println(counter);
        }
    }

    public static String getType(String a) {
        switch (a) {
            case "Y", "B":
                return "P";
            case "X", "A":
                return "R";
            case "Z", "C":
                return "S";
            default:
                return "";
        }
    }
}