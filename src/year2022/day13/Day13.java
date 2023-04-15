package year2022.day13;

import common.Day;
import year2022.day12.Day12;

import java.util.Arrays;
import java.util.List;

public class Day13 extends Day {

    private final List<String[]> map = Arrays.stream(exampleDayString().split("\r\n")).map(str -> str.split(",")).toList();

    public static void main(String[] args) {
        new Day13().printParts();
    }

    @Override
    protected Object part1() {
        int counter = 0;
        for (int id1 = 0; id1 < map.size(); id1 += 3) {
            int id2 = id1 + 1;
            boolean valid = true;
            if (map.get(id1).length != map.get(id2).length) {
                valid = false;
                continue;
            }
            String[] pair1 = map.get(id1);
            String[] pair2 = map.get(id2);

            if (Arrays.equals(pair1, pair2)) {
                counter++;
                continue;
            }

            for (int i = 0; i < pair1.length; i++) {
                String a = pair1[i];
                String b = pair2[i];
                a = a.replace("[", "").replace("]", "");
                b = b.replace("[", "").replace("]", "");
                if (a.length() > 0 && b.length() > 0) {
                    int numa = Integer.parseInt(a);
                    int numb = Integer.parseInt(b);
                    if (numa > numb) {
                        valid = false;
                        continue;
                    }
                }
            }
            if (valid) {
                counter += id1 / 3;
                System.out.println(Arrays.toString(pair1) + " " + id1);
            }

        }
        return null;
    }

    @Override
    protected Object part2() {
        return null;
    }
}
