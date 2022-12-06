package twentytwentytwo.two;

import java.util.Scanner;



public class B {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        int counter = 0;
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            if (s.contains("END")) {
                break;
            }
            String[] t = s.split(" ");
            String oponent = getType(t[0]);
            String me = findMe(oponent, t[1]);
            System.out.println(t[1] + " " + me);

            if (me.equals(oponent)) {
                counter += 3;
            } else if (
                    me.equals("R") && oponent.equals("S") ||
                            me.equals("S") && oponent.equals("P") ||
                            me.equals("P") && oponent.equals("R")

            ) {
                counter += 6;
            }

            switch (me) {
                case "R" -> counter += 1;
                case "P" -> counter += 2;
                case "S" -> counter += 3;
            }
            System.out.println(counter);
        }
    }


    public static String findMe(String oponent, String b) {
        String t = "";
        switch (b) {
            case "X" -> t = "L";
            case "Y" -> t = "D";
            case "Z" -> t = "W";
        }
        if (t.equals("D")) {
            return oponent;
        } else if (t.equals("L")) {
            if (oponent.equals("R")) {
                return "S";
            } else if (oponent.equals("P")) {
                return "R";
            } else {
                return "P";
            }
        } else {
            if (oponent.equals("R")) {
                return "P";
            } else if (oponent.equals("P")) {
                return "S";
            } else {
                return "R";
            }
        }
    }

    public static String getType(String a) {
        switch (a) {
            case "B":
                return "P";
            case "A":
                return "R";
            case "C":
                return "S";
            default:
                return "";
        }
    }

}