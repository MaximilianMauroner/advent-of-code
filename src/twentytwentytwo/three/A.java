package twentytwentytwo.three;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(twentytwentytwo.one.A.class.getResource("input.txt").getPath()).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        int sum = 0;
        while (scanner.hasNextLine()) {
            System.out.println();
            String s = scanner.nextLine();
            String left;
            String right;
            left = s.substring(0, s.length() / 2);
            right = s.substring(s.length() / 2);
            String checked = "";
            for (int i = 0; i < left.length(); i++) {
                if (right.contains(left.charAt(i) + "") && !checked.contains(left.charAt(i) + "")) {
                    int temp = 0;
                    checked += left.charAt(i) + "";
                    if (left.charAt(i) > 96) {
                        temp = (int) (left.charAt(i) - 96);
                    } else {
                        temp = (int) (left.charAt(i) - 65 + 27);
                    }
                    sum += temp;
                }
            }
            System.out.println(sum);
        }
    }
}
