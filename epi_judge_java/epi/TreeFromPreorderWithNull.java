package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeFromPreorderWithNull {
    private static Integer subtreeIdx;

    private static BinaryTreeNode<Integer> reconstructPreorder(List<Integer> preorder) {
        subtreeIdx = 0;
        return reconstructHelper(preorder);
    }

    private static BinaryTreeNode<Integer> reconstructHelper(List<Integer> preorder) {
        Integer data = preorder.get(subtreeIdx++);
        if (Objects.isNull(data)) {
            return null;
        }

        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(data);
        node.left = reconstructHelper(preorder);
        node.right = reconstructHelper(preorder);

        return node;
    }

    @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
    public static BinaryTreeNode<Integer> reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
            throws Exception {
        List<Integer> ints = new ArrayList<>();
        for (String s : strings) {
            if (s.equals("null")) {
                ints.add(null);
            } else {
                ints.add(Integer.parseInt(s));
            }
        }

        return executor.run(() -> reconstructPreorder(ints));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
