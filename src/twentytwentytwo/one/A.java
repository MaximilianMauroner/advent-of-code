package twentytwentytwo.one;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        int max = 0;
        int curr = 0;
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(A.class.getResource("input.txt").getPath()).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

