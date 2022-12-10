package year2022.day10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(A.class.getResource("example.txt").getPath()).getPath()));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        String[] crt = new String[18000];
        Arrays.fill(crt, ".");

        int x = 1;
        int cycle = 1;
        int next = 40;
        int current = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("addx")) {
                x += Integer.parseInt(line.split(" ")[1]);
                cycle += 2;
                current += 2;
            } else {
                cycle++;
                current++;
            }
            if (cycle >= next) {
                System.out.println(Arrays.toString(crt));
                next += 40;
                current = current + x;
                crt[cycle] = "#";
            } else {
                current = current + x;
                crt[cycle] = "#";
            }
        }
        scanner.close();
        System.out.println(Arrays.toString(crt));

    }

}
