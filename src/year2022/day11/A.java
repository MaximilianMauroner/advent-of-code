package year2022.day11;

import javax.naming.PartialResultException;
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
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        List<List<String>> monkeyConditions = new ArrayList<>();
        List<List<Integer>> monkeyTracker = new ArrayList<>();
        List<Integer> counter = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String monkeyLine = scanner.nextLine();
            readMonkeys(scanner, monkeyConditions, monkeyLine, counter);
        }
        for (int i = 0; i < monkeyConditions.size(); i++) {
            List<String> monkey = monkeyConditions.get(i);
            prepareRound(i, monkey, monkeyTracker, counter);
        }
        for (int round = 0; round < 20; round++) {
            for (int monkeyCount = 0; monkeyCount < monkeyConditions.size(); monkeyCount++) {
                List<String> cond = monkeyConditions.get(monkeyCount);
                List<Integer> tracker = monkeyTracker.get(monkeyCount);
                for (int k : tracker) {
                    executeCommand(monkeyCount, cond, monkeyTracker, counter, k);
                }
                while (monkeyTracker.get(monkeyCount).size() > 0) {
                    monkeyTracker.get(monkeyCount).remove(0);
                }
            }
        }
        System.out.println(Arrays.deepToString(monkeyTracker.toArray()));
        System.out.println(Arrays.deepToString(counter.toArray()));
        Object[] ca = counter.toArray();
        Arrays.sort(ca);
        System.out.println(Arrays.toString(ca));
        int len = ca.length - 1;
        int t = (int) ca[len - 1] * (int) ca[len];
        System.out.println(t);
    }


    private static void readMonkeys(Scanner scanner, List<List<String>> monkeys, String s, List<Integer> counter) {
        if (s.matches("(Monkey) [0-9]\\:")) {
            List<String> temp = new ArrayList<>();
            temp.add(scanner.nextLine().split("Starting items:")[1].trim());
            temp.add(scanner.nextLine().split("Operation: new =")[1].trim());
            temp.add(scanner.nextLine().split("Test: divisible by")[1].trim());
            temp.add(scanner.nextLine().split("If true: throw to monkey")[1].trim());
            temp.add(scanner.nextLine().split("If false: throw to monkey")[1].trim());
            monkeys.add(temp);
        }
    }

    private static void prepareRound(int monkeyNumber, List<String> condition, List<List<Integer>> monkeyTracker, List<Integer> counter) {
        String[] startingItems = condition.get(0).replace(",", "").split(" ");
        int[] startingItemsInt = new int[startingItems.length];
        for (int i = 0; i < startingItems.length; i++) {
            if (startingItems[i].matches("[0-9]+")) {
                startingItemsInt[i] = Integer.parseInt(startingItems[i]);
                handleList(new int[]{monkeyNumber}, monkeyTracker, counter);
                monkeyTracker.get(monkeyNumber).add(startingItemsInt[i]);
            }
        }
    }

    private static void executeCommand(int monkeyNumber, List<String> condition, List<List<Integer>> monkeyTracker, List<Integer> counter, int value) {
        String operation = condition.get(1);
        int init = value;
        int divisible = Integer.parseInt(condition.get(2));
        int success = Integer.parseInt(condition.get(3));
        int failure = Integer.parseInt(condition.get(4));
        if (operation.contains("*")) {
            String s = operation.split("\\*")[1];
            if (s.trim().equals("old")) {
                value *= value;
            } else {
                value *= Integer.parseInt(s.trim());
            }
        } else if (operation.contains("+")) {
            String s = operation.split("\\+")[1];
            if (s.trim().equals("old")) {
                value += value;
            } else {
                value += Integer.parseInt(s.trim());
            }
        }
        value /= 3;
        if (value % divisible == 0) {
            monkeyTracker.get(success).add(value);
        } else {
            monkeyTracker.get(failure).add(value);
        }
        counter.set(monkeyNumber, counter.get(monkeyNumber) + 1);
    }

    private static void handleList(int[] idxs, List<List<Integer>> monkeyTracker, List<Integer> counter) {
        for (int i = 0; i < idxs.length; i++) {
            if (idxs[i] >= monkeyTracker.size()) {
                while (idxs[i] >= monkeyTracker.size()) {
                    monkeyTracker.add(new ArrayList<>());
                    counter.add(0);
                }
            }
        }
    }
}