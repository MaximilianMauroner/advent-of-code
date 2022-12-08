package twentytwentytwo.day7;

import java.io.*;

class fileobject {
    boolean filetype; //true file false dir
    long size;
    String name;
    int parent;

    public fileobject(String name, String size, int parent) {
        try {
            this.size = (long) Integer.parseInt(size);
        } catch (Exception e) {
            this.size = 0;
        }
        filetype = true;
        this.name = name;
        this.parent = parent;
    }

    public fileobject(String name, int parent) {
        filetype = false;
        size = 0;
        this.name = name;
        this.parent = parent;
    }
}

public class A {
    public static fileobject[] filelist = new fileobject[1];
    public static int filelistcurrentdir = 0;

    public static void main(String[] args) {
        int filesizelimit = 100000;
        long counter = 0;
        String filename = "C:\\Users\\maxim\\Desktop\\Projects\\SpeedCoding\\AdventOfCode\\src\\twentytwentytwo\\seven\\input.txt";
        filelist[0] = new fileobject("/", -1);
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            while (line != null) {
                if (isCommand(line))
                    processCommand(line);
                else
                    addFileObject(line);
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        for (int i = 0; i < filelist.length; i++) {
            if (!filelist[i].filetype)
                if (getDirSize(i) <= filesizelimit)
                    counter += getDirSize(i);
        }
        System.out.println("\nPART 1: " + counter);
        System.out.println("PART 2: " + findDir());
    }

    public static void addFileObject(String line) {
        String lineParts[] = line.split(" ");
        if (lineParts.length >= 2)
            if (lineParts[0].contains("dir"))
                filelist = addElement(new fileobject(lineParts[1], filelistcurrentdir), filelist);
            else
                filelist = addElement(new fileobject(lineParts[1], lineParts[0], filelistcurrentdir), filelist);
    }

    public static boolean isCommand(String line) {
        if (line.length() > 0)
            if (line.charAt(0) == '$')
                return true;
        return false;
    }

    public static boolean processCommand(String line) {
        String[] commands = line.split(" ");
        if (commands[1].contains("cd")) {
            filelistcurrentdir = getDirID(commands[2]);
            return (false);
        }
        if (commands[1].contains("ls"))
            return (true);
        return (false);
    }

    public static int getDirID(String name) {
        int t_int = filelistcurrentdir;
        if (name.contains("/") || name.contains("\\"))
            return (0);
        if (name.contains("..")) {
            t_int = filelist[filelistcurrentdir].parent; //get parent
            if (t_int < 0) t_int = 0;
            return t_int;
        }
        for (int i = 0; i < filelist.length; i++)
            if (filelist[i].name.compareTo(name) == 0)
                if (filelist[i].parent == filelistcurrentdir)
                    return (i);
        return (t_int);
    }

    public static long findDir() {
        long fsSize = 70000000;
        long fsNeeded = 30000000;
        int currentChamp = 0;
        for (int i = 1; i < filelist.length; i++)
            if (!filelist[i].filetype) {
                long t_long = fsSize - getDirSize(0) + getDirSize(i);
                if (t_long >= fsNeeded)
                    if (getDirSize(i) < getDirSize(currentChamp))
                        currentChamp = i;
            }
        return getDirSize(currentChamp);
    }

    public static long getDirSize(int id) {
        long size = 0;
        int children[] = findChildren(id);
        if (children != null) {
            for (int i = 0; i < children.length; i++)

                if (filelist[children[i]].filetype)
                    size += filelist[children[i]].size;
                else
                    size += getDirSize(children[i]);
        }
        return size;
    }

    public static int findDistanceFromStart(int id) {
        int distance = 0;
        int parent = filelist[id].parent;
        while (parent >= 0) {
            distance++;
            parent = filelist[parent].parent;
        }
        return distance;
    }

    public static int[] findChildren(int id) {
        int[] children = null;
        for (int i = 0; i < filelist.length; i++)
            if (filelist[i].parent == id)
                children = addElement2(i, children);
        return children;
    }

    public static fileobject[] addElement(fileobject newObject, fileobject[] oldArray) {
        int newArrayLength = 1;
        if (oldArray != null)
            newArrayLength = oldArray.length + 1;
        fileobject[] t_array = new fileobject[newArrayLength];
        for (int i = 0; i < oldArray.length; i++)
            t_array[i] = oldArray[i];
        t_array[t_array.length - 1] = newObject;
        return t_array;
    }

    public static int[] addElement2(int newObject, int[] oldArray) {
        int newArrayLength = 1;
        if (oldArray != null)
            newArrayLength = oldArray.length + 1;
        int[] t_array = new int[newArrayLength];
        if (oldArray != null)
            for (int i = 0; i < oldArray.length; i++)
                t_array[i] = oldArray[i];
        t_array[t_array.length - 1] = newObject;
        return t_array;
    }
}