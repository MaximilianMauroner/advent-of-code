package twentytwentytwo.day1;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        int max = 0;
        int curr = 0;
        List<Integer> list = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(A.class.getResource("input.txt").getPath()).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextInt()) {
            String s = scanner.nextLine();
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