package twentytwentytwo.one;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class A {
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

