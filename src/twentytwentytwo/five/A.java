package twentytwentytwo.five;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A {
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
        for (int i = 0; i < stacks.size(); i++) {
            if (stacks.get(i).size() > 0) {
                System.out.print(stacks.get(i).get(stacks.get(i).size() - 1).replaceAll("[^a-zA-Z]", ""));

            }
        }
    }

    public static void moveItems(List<List<String>> items, int item, int from, int to) {
        if (item == 0) {
            String a = items.get(from).get(items.get(from).size() - 1);
            items.get(to).add(a);
            items.get(from).remove(items.get(from).size() - 1);
        } else {
            String a = items.get(from).get(items.get(from).size() - 1);
            items.get(to).add(a);
            items.get(from).remove(items.get(from).size() - 1);
            item--;
            moveItems(items, item, from, to);
        }
    }
}
