package twentytwentytwo.day8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<List<Integer>> trees = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String a = scanner.nextLine();
            List<Integer> tree = new ArrayList<>();
            for (int i = 0; i < a.length(); i++) {
                tree.add(Integer.parseInt(a.substring(i, i + 1)));
            }
            trees.add(tree);
        }
        for (int i = 0; i < trees.size(); i++) {
            for (int j = 0; j < trees.get(i).size(); j++) {
                if (isVisible(trees, i, j, trees.get(i).get(j))) {
//                    System.out.println(i + " " + j + " " + trees.get(i).get(j) + " visible");
                    counter++;
                } else {
//                    System.out.println(i + " " + j + " " + trees.get(i).get(j) + " not visible");
                }
            }
        }
        System.out.println(counter);
//        System.out.println(Arrays.deepToString(trees.toArray()));
    }

    public static boolean isVisible(List<List<Integer>> forest, int y, int x, int h) {
        if (x == 0 || y == 0 || x == (forest.size() - 1) || y == 0 || y == (forest.get(x).size() - 1)) {
            return true;
        }
        if (isVisibleHori(forest, x, y, h) || isVisibleVerti(forest, x, y, h)) {
            return true;
        }
        return false;
    }

    public static boolean isVisibleHori(List<List<Integer>> forest, int x, int y, int h) {
        boolean left = true;
        boolean right = true;
        for (int i = 0; i < forest.get(y).size(); i++) {
            if (i < x && forest.get(y).get(i) >= h) {
                left = false;
            } else if (i > x && forest.get(y).get(i) >= h) {
                right = false;
            }
        }
        return left || right;
    }

    public static boolean isVisibleVerti(List<List<Integer>> forest, int x, int y, int h) {
        boolean left = true;
        boolean right = true;
        for (int i = 0; i < forest.size(); i++) {
            if (i < y && forest.get(i).get(x) >= h) {
                left = false;
            } else if (i > y && forest.get(i).get(x) >= h) {
                right = false;
            }
        }
        return left || right;
    }
}
