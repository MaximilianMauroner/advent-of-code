package year2022.day08;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B {
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
                int temp = getScore(trees, i, j, trees.get(i).get(j));
                if (temp > counter) {
                    counter = temp;
                }
            }
        }
        System.out.println(counter);
    }

    public static int getScore(List<List<Integer>> forest, int y, int x, int h) {
        int[] score = new int[4];
        for (int i = 0; i < forest.size(); i++) {
            if (i < x) {
                if (forest.get(y).get((x - 1) - i) < h) {
                    score[0]++;
                } else {
                    score[0]++;
                    i = x;
                }
            } else if (i > x) {
                if (forest.get(y).get(i) < h) {
                    score[1]++;
                } else {
                    score[1]++;
                    break;
                }
            }
        }
        for (int i = 0; i < forest.get(y).size(); i++) {
            if (i < y) {
                if (forest.get(y - 1 - i).get(x) < h) {
                    score[2]++;
                } else {
                    score[2]++;
                    i = y;
                }
            } else if (i > y) {
                if (forest.get(i).get(x) < h) {
                    score[3]++;
                } else {
                    score[3]++;
                    break;
                }
            }
        }
        return score[0] * score[1] * score[2] * score[3];
    }
}
