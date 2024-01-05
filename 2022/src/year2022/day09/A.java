package year2022.day09;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class A {

    static int[] gtail = new int[]{0, 0};
    static int[] ghead = new int[]{0, 0};

    public static void main(String[] args) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(A.class.getResource("input.txt").getPath()).getPath()));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        int len = 1000;
        String[][] field = new String[len][len];
        String[][] visited = new String[len][len];
        gtail[0] = field.length - 1;
        ghead[0] = field.length - 1;
        fillField(field);
        for (String[] strings : visited) {
            Arrays.fill(strings, "");
        }
        int c = 0;
        while (scanner.hasNextLine()) {
            String a = scanner.nextLine();
            String[] b = a.split(" ");
            makeMove(field, b[0], b[1], visited);
            System.out.println(c + " " + a);
            c++;
        }
        System.out.println(countVisited(visited));
        scanner.close();
    }

    public static void fillField(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = "";
                if (i == field.length - 1 && j == 0) {
                    field[i][j] = "TH";
                } else {
                    field[i][j] = ".";
                }
            }
        }
    }

    public static int[] findHead(String[][] field) {
        int[] head = new int[2];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].contains("H")) {
                    head[0] = i;
                    head[1] = j;
                    break;
                }
            }
        }
        return head;
    }

    public static int[] findTail(String[][] field) {
        int[] tail = new int[]{-1, -1};
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].contains("T")) {
                    tail[0] = i;
                    tail[1] = j;
                    break;
                }
            }
        }
        return tail;
    }

    public static void moveHead(String[][] field, String direction, String[][] visited) {
        int[] head = ghead;
        int y = head[0];
        int x = head[1];
        if (direction.equals("R")) {
            if (x + 1 < field[y].length) {
                x++;
            } else {
                x = 0;
            }
        } else if (direction.equals("L")) {
            if (x - 1 >= 0) {
                x--;
            } else {
                x = field[y].length - 1;
            }
        } else if (direction.equals("U")) {
            if (y - 1 >= 0) {
                y--;
            } else {
                y = field.length - 1;
            }
        } else if (direction.equals("D")) {
            if (y + 1 < field.length) {
                y++;
            } else {
                y = 0;
            }
        }
        int[] tail = findTail(field);
        if (isTailPositionAcceptable(new int[]{y, x}, tail)) {
            if (!field[head[0]][head[1]].contains("T")) {
                field[head[0]][head[1]] = ".";
            }
            if (y != tail[0] || x != tail[1]) {
                field[tail[0]][tail[1]] = "T";
                visited[tail[0]][tail[1]] += "#";
            }
        } else {
            field[tail[0]][tail[1]] = ".";
            field[head[0]][head[1]] = "T";
            visited[head[0]][head[1]] += "#";
        }
        ghead[0] = y;
        ghead[1] = x;
        if (field[y][x].contains("T")) {
            field[y][x] = "TH";
        } else {
            field[y][x] = "H";
        }
    }

    public static boolean isTailPositionAcceptable(int[] head, int[] tail) {
        if (Math.abs(head[0] - tail[0]) > 1 || Math.abs(head[1] - tail[1]) > 1) {
            return false;
        }
        return true;
    }

    public static void makeMove(String[][] field, String direction, String val, String[][] visited) {
        for (int i = 0; i < Integer.parseInt(val); i++) {
            moveHead(field, direction, visited);
        }
    }

    public static int countVisited(String[][] visited) {
        int count = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j] != null && visited[i][j].contains("#")) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void print2DArray(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == null) {
                    System.out.print(".");
                } else {
                    System.out.print(field[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}