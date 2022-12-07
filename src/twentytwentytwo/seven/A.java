package twentytwentytwo.seven;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.*;

class ListNode {
    List<Map<String, Integer>> files = new ArrayList<>();
    String dir;
    String parent;
    List<ListNode> children = new ArrayList<>();

    ListNode(List<Map<String, Integer>> files) {
        this.files = files;
    }

    ListNode(List<Map<String, Integer>> files, List<ListNode> next) {
        this.files = files;
        this.children = next;
    }

    ListNode(String dir, String parent) {
        this.dir = dir;
        this.parent = parent;
    }
}

public class A {

    public static int debthCounter = 0;

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(A.class.getResource("input.txt").getPath()).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ListNode node = new ListNode("/", "");
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            identifyFiles(scanner, s, node);
        }
        printNode(node, 0);
    }

    public static void identifyFiles(Scanner scanner, String s, ListNode node) {
        if (s.contains("$")) {
            if (s.contains("cd /")) {
                debthCounter = 0;
            } else if (s.contains("cd ..")) {
                debthCounter--;
            } else if (s.contains("cd")) {
                debthCounter++;
                addFolders(node, s.split("cd")[1], debthCounter);
            } else if (s.contains("ls")) {
                boolean isLs = true;
                while (isLs && scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    if (nextLine.contains("$")) {
                        isLs = false;
                        identifyFiles(scanner, nextLine, node);
                    } else {
                        if (nextLine.contains("dir")) {
                            addFolders(node, nextLine.split("dir")[1], debthCounter + 1, "");
                        }
                    }
                }
            }
        }
    }

    public static void addFiles(ListNode listNode, String fileName, int filesize, int level, String parentName) {
    }

    public static void addFolders(ListNode listNode, String name, int level, String parentName) {
        System.out.println("level: " + level + " name: " + name + " parent: " + parentName);
        if (level == 0 && (parentName.equals(listNode.dir) || parentName.equals(""))) {
            if (!listNode.children.contains(new ListNode(name, parentName))) {
                System.out.println("new node" + name);
                listNode.children.add(new ListNode(name, parentName));
            }
        } else {
            for (ListNode child : listNode.children) {
                addFolders(child, name, level - 1, parentName);
            }
        }

    }

    //    printer all the ListNodes in the tree
    public static void printNode(ListNode listNode, int level) {
        System.out.println(listNode.dir + listNode.children.size() + " " + level);
        if (listNode.children != null) {
            for (ListNode node : listNode.children) {
                printNode(node, level + 1);
//                for (int i = 0; i < level; i++) {
//                    System.out.print("\t");
//                }
//                System.out.println(node.dir);
            }
        }
    }
}
