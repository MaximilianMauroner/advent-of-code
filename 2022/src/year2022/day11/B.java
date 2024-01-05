package year2022.day11;

import javax.naming.PartialResultException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class B {

    public static int bigPrime = 1;

    public static void main(String[] args) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(A.class.getResource("input.txt").getPath()).getPath()));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        List<List<String>> monkeyConditions = new ArrayList<>();
        List<List<BigInteger>> monkeyTracker = new ArrayList<>();
        List<Long> counter = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String monkeyLine = scanner.nextLine();
            readMonkeys(scanner, monkeyConditions, monkeyLine);
        }
        for (int i = 0; i < monkeyConditions.size(); i++) {
            List<String> monkey = monkeyConditions.get(i);
            prepareRound(i, monkey, monkeyTracker, counter);
        }
        bigPrime = productItems(monkeyConditions);
        int maxRounds = 10000;

        long tot = System.nanoTime();
        for (int round = 0; round < maxRounds; round++) {
            for (int monkeyCount = 0; monkeyCount < monkeyConditions.size(); monkeyCount++) {
                List<String> cond = monkeyConditions.get(monkeyCount);
                List<BigInteger> tracker = monkeyTracker.get(monkeyCount);
                for (int i = 0; i < tracker.size(); i++) {
                    executeCommand(monkeyCount, cond, monkeyTracker, counter, tracker.get(i));
                }
                while (monkeyTracker.get(monkeyCount).size() > 0) {
                    monkeyTracker.get(monkeyCount).remove(0);
                }
            }
        }

        System.out.println("Expected Results:");
        System.out.println("Done in:6984 ms\n" + "[162, 3837, 4149, 4159]\n" + "17255691");
        System.out.println("----------------------------------------");
        System.out.println("Actual Results:");
        System.out.println("Done in:" + ((System.nanoTime() - tot) / 1000000) + " ms");
        counter.sort(Long::compareTo);
        System.out.println(Arrays.toString(counter.toArray()));
        int len = counter.size() - 1;
        long t = counter.get(len - 1) * counter.get(len);
        System.out.println(t);
    }


    private static void readMonkeys(Scanner scanner, List<List<String>> monkeys, String s) {
        if (s.matches("(Monkey) [0-9]:")) {
            List<String> temp = new ArrayList<>();
            temp.add(scanner.nextLine().split("Starting items:")[1].trim());
            temp.add(scanner.nextLine().split("Operation: new =")[1].trim());
            temp.add(scanner.nextLine().split("Test: divisible by")[1].trim());
            temp.add(scanner.nextLine().split("If true: throw to monkey")[1].trim());
            temp.add(scanner.nextLine().split("If false: throw to monkey")[1].trim());
            monkeys.add(temp);
        }
    }

    private static void prepareRound(int monkeyNumber, List<String> condition, List<List<BigInteger>> monkeyTracker, List<Long> counter) {
        String[] startingItems = condition.get(0).replace(",", "").split(" ");
        BigInteger[] startingItemsInt = new BigInteger[startingItems.length];
        for (int i = 0; i < startingItems.length; i++) {
            if (startingItems[i].matches("[0-9]+")) {
                startingItemsInt[i] = new BigInteger(startingItems[i]);
                handleList(new int[]{monkeyNumber}, monkeyTracker, counter);
                monkeyTracker.get(monkeyNumber).add(startingItemsInt[i]);
            }
        }
    }

    private static void executeCommand(int monkeyNumber, List<String> condition, List<List<BigInteger>> monkeyTracker, List<Long> counter, BigInteger value) {
        String operation = condition.get(1);
        int success = Integer.parseInt(condition.get(3));
        int failure = Integer.parseInt(condition.get(4));
        if (operation.contains("*")) {
            String s = operation.split("\\*")[1].trim();
            if (s.equals("old")) {
                value = value.multiply(value);
            } else {
                value = value.multiply(new BigInteger(s));
            }
        } else if (operation.contains("+")) {
            String s = operation.split("\\+")[1].trim();
            if (s.equals("old")) {
                value = value.add(value);
            } else {
                value = value.add(new BigInteger(s));
            }
        }
        if (value.mod(new BigInteger(condition.get(2))).compareTo(BigInteger.ZERO) == 0) {
            value = value.mod(new BigInteger(bigPrime + ""));
            monkeyTracker.get(success).add(value);
        } else {
            monkeyTracker.get(failure).add(value);
        }
        counter.set(monkeyNumber, counter.get(monkeyNumber) + 1);
    }

    private static void handleList(int[] idxs, List<List<BigInteger>> monkeyTracker, List<Long> counter) {
        for (int idx : idxs) {
            if (idx >= monkeyTracker.size()) {
                while (idx >= monkeyTracker.size()) {
                    monkeyTracker.add(new ArrayList<>());
                    counter.add(0L);
                }
            }
        }
    }

    private static int productItems(List<List<String>> conditions) {
        int t = 1;
        for (List<String> condition : conditions) {
            String operation = condition.get(2);
            t *= Integer.parseInt(operation.trim());
        }
        return t;
    }
}