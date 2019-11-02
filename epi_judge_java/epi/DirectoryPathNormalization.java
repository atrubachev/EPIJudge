package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class DirectoryPathNormalization {
    @EpiTest(testDataFile = "directory_path_normalization.tsv")

    public static String shortestEquivalentPath(String path) {
        Deque<String> elements = new LinkedList<>();

        for (String element : path.split("/")) {
            if (element.equals("..")) {
                if (elements.isEmpty() || elements.peek().equals("..")) {
                    elements.push(element);
                } else {
                    elements.pop();
                }
            } else if (!element.equals(".") && !element.isEmpty()) {
                elements.push(element);
            }
        }

        StringBuilder normalizedPath = new StringBuilder();
        Iterator<String> iter = elements.descendingIterator();
        while (iter.hasNext()) {
            normalizedPath.append(iter.next());
            if (iter.hasNext()) {
                normalizedPath.append("/");
            }
        }
        if (path.startsWith("/")) {
            normalizedPath.insert(0, "/");
        }

        return normalizedPath.toString();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
