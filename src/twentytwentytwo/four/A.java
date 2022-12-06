package twentytwentytwo.four;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        int counter = 0;
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(A.class.getResource("input.txt").getPath()).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String a = scanner.nextLine();
            String[] first = a.split(",")[0].split("-");
            String[] second = a.split(",")[1].split("-");
            if (Integer.parseInt(first[0]) <= Integer.parseInt(second[0]) && Integer.parseInt(first[1]) >= Integer.parseInt(second[1])) {
                counter++;
            } else if (Integer.parseInt(first[0]) >= Integer.parseInt(second[0]) && Integer.parseInt(first[1]) <= Integer.parseInt(second[1])) {
                counter++;
            }
            System.out.println(counter);
        }
        System.out.println(counter);
    }
}
