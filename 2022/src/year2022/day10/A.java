package year2022.day10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class A {

    public static void main(String[] args) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(A.class.getResource("input.txt").getPath()).getPath()));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        int x = 1;
        int counter = 1;
        int totalSum = 0;
        int next = 20;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("addx")) {
                x += Integer.parseInt(line.split(" ")[1]);
                counter += 2;
            } else {
                counter++;
            }
            if (counter == next) {
                totalSum += x * next;
                next += 40;
            } else if (counter > next) {
                totalSum += next * (x - Integer.parseInt(line.split(" ")[1]));
                next += 40;
            }
        }
        System.out.println(totalSum);
        scanner.close();
    }
}
