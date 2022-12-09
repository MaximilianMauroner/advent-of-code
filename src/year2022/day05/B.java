package year2022.day05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        String a = "";
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(A.class.getResource("input.txt").getPath()).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            a += line + "\n";
            System.out.println(line);
        }
        scanner.close();

        String[] div = a.split("move ");
        List<List<String>> stacks = new ArrayList<>();
        String[] rows = div[0].split("\n");
        for (int j = 0; j < (rows[0].length() + 1) / 4; j++) {
            stacks.add(new ArrayList<>());
        }
        for (int i = rows.length - 2; i >= 0; i--) {
            int start = 0;
            for (int j = 0; j < (rows[i].length() + 1) / 4; j++) {
                String item = rows[i].substring(start, start + 3);
                start += 4;
                if (!item.contains(" ")) stacks.get(j).add(item);
            }
        }
        for (int i = 1; i < div.length; i++) {
            String[] numbs = div[i].split("[a-zA-Z]+");
            for (int j = 0; j < numbs.length; j++) {
                numbs[j] = numbs[j].replaceAll("[^0-9]", "");
            }
            int start = Integer.parseInt(numbs[0]);
            int from = Integer.parseInt(numbs[1]);
            int to = Integer.parseInt(numbs[2]);
            moveItems(stacks, start - 1, from - 1, to - 1);
        }
        System.out.println(Arrays.deepToString(stacks.toArray()));

        for (int i = 0; i < stacks.size(); i++) {
            if (stacks.get(i).size() > 0) {
                System.out.print(stacks.get(i).get(stacks.get(i).size() - 1).replaceAll("[^a-zA-Z]", ""));

            }
        }
    }

    public static void moveItems(List<List<String>> items, int item, int from, int to) {
        int size = items.get(from).size() - 1;

        if (item == 0) {
            String temp = items.get(from).get(size);
            items.get(from).remove(size);
            items.get(to).add(temp);
        } else {
            int t = size - item;
            for (int i = 0; i <= item; i++) {
                String temp = items.get(from).get(t);
                items.get(from).remove(t);
                items.get(to).add(temp);
            }
        }
    }
}
