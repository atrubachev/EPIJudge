package epi;

import epi.test_framework.*;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorWithParent {

    public static BinaryTree<Integer> LCA(BinaryTree<Integer> nodeA,
                                          BinaryTree<Integer> nodeB) {
        Set<BinaryTree> seenNodes = new HashSet<>();
        BinaryTree<Integer> node = nodeA;
        while (node != null) {
            seenNodes.add(node);
            node = node.parent;
        }
        node = nodeB;
        while (node != null && !seenNodes.contains(node)) {
            node = node.parent;
        }

        return node;
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                                 Integer key0, Integer key1) throws Exception {
        BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

        BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

        if (result == null) {
            throw new TestFailure("Result can not be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
