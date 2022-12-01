package twentytwentytwo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OneA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int max = 0;
        int curr = 0;
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            if (s.equals("END")) {
                break;
            }
            System.out.println(s);
            if (s.isEmpty()) {
                if (max < curr) {
                    max = curr;
                }
                curr = 0;
            } else {
                curr += Integer.parseInt(s);
            }
        }
        scanner.close();
        System.out.println("number " + max);
    }
}

class OneB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int max = 0;
        int curr = 0;
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            String s = scanner.nextLine();
            if (s.equals("END")) {
                break;
            }
            System.out.println(s);
            if (s.isEmpty()) {
                list.add(curr);
                curr = 0;
            } else {
                curr += Integer.parseInt(s);
            }
        }
        list.add(curr);
        Collections.sort(list);
        for (int i = list.size() - 3; i < list.size(); i++) {
            max += list.get(i);
            System.out.println(list.get(i));
        }
        scanner.close();
        System.out.println("number " + max);
    }
}