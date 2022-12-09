package year2022.day09;

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
        String[][] field = new String[5][6];
        String[][] visited = new String[5][6];
        fillField(field);
        while (scanner.hasNextLine()) {
            String a = scanner.nextLine();
            String[] b = a.split(" ");
            makeMove(field, b[0], b[1], visited);
        }
//        print2DArray(field);
        System.out.println(countVisited(visited));
//        print2DArray(visited);
        scanner.close();

    }

    public static void fillField(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = "";
                if (i == field.length - 1 && j == 0) {
                    field[i][j] = "H";
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
                if (field[i][j].equals("H")) {
                    head[0] = i;
                    head[1] = j;
                }
            }
        }
        return head;
    }

    public static int[] findTail(String[][] field) {
        int[] tail = new int[2];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].equals("T")) {
                    tail[0] = i;
                    tail[1] = j;
                }
            }
        }
        if (tail[0] == 0 && tail[1] == 0) {
            return findHead(field);
        }
        return tail;
    }

    public static void moveHead(String[][] field, String direction) {
        int[] head = findHead(field);
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
            field[head[0]][head[1]] = ".";
        } else {
            field[tail[0]][tail[1]] = ".";
            field[head[0]][head[1]] = "T";
        }
        field[y][x] = "H";
    }

    public static void moveTail(String[][] field) {
        int[] head = findHead(field);
        int[] tail = findTail(field);
        int[] newTail = new int[]{tail[0], tail[1]};
        int[][] directions = new int[][]{
                {0, 0}, {0, 1}, {0, -1},
                {1, 0}, {1, 1}, {1, -1},
                {-1, 0}, {-1, 1}, {-1, -1}
        };
        if (!isTailPositionAcceptable(head, tail)) {
            int x, y;
            for (int i = 0; i < 9; i++) {
                y = directions[i][0];
                x = directions[i][1];
                if (tail[0] + y < field.length) {
                    newTail[0] = tail[0] + y;
                } else {
                    newTail[0] = 0;
                }
                if (tail[1] + x < field[tail[0]].length) {
                    newTail[1] = tail[1] + x;
                } else {
                    newTail[1] = 0;
                }
                if (isTailPositionAcceptable(head, newTail)) {
                    field[tail[0]][tail[1]] = ".";
                    field[newTail[0]][newTail[1]] = "T";
                    break;
                }
            }
        }

    }

    public static boolean isTailPositionAcceptable(int[] head, int[] tail) {
        if (Math.abs(head[0] - tail[0]) > 1 || Math.abs(head[1] - tail[1]) > 1) {
            return false;
        }
        return true;
    }

    public static void makeMove(String[][] field, String direction, String val, String[][] visited) {
        System.out.println("Direction: " + direction + " Value: " + val);
        for (int i = 0; i < Integer.parseInt(val); i++) {
            moveHead(field, direction);
            visited(field, visited);
        }
        print2DArray(field);
    }

    public static void visited(String[][] field, String[][] visited) {
        int[] tail = findTail(field);
        visited[tail[0]][tail[1]] = "#";
    }

    public static int countVisited(String[][] visited) {
        int count = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j] != null && visited[i][j].equals("#")) {
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