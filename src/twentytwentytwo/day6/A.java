package twentytwentytwo.day6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(A.class.getResource("input.txt").getPath()).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int counter = 0;
        while (scanner.hasNextLine()) {
            String a = scanner.nextLine();
            List<Character> chars = new ArrayList<>();
            for (int i = 0; i < a.length(); i++) {
                if (chars.size() == 4) {
                    if (isFullyUnique(chars)) {
                        counter = i;
                        System.out.println(counter);
                        i = a.length();
                        continue;
                    }
                    chars.remove(0);
                    chars.add(a.charAt(i));
                } else {
                    chars.add(a.charAt(i));
                }

            }
        }
    }

    public static boolean isFullyUnique(List<Character> chars) {
        List<Character> temp = new ArrayList<>();
        for (int i = 0; i < chars.size(); i++) {
            if (temp.contains(chars.get(i))) {
                return false;
            }
            temp.add(chars.get(i));
        }
        return true;
    }
}
