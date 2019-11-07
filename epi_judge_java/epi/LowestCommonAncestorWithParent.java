package epi;

import epi.test_framework.*;

public class LowestCommonAncestorWithParent {

    public static BinaryTree<Integer> LCA(BinaryTree<Integer> nodeA,
                                          BinaryTree<Integer> nodeB) {
        int depthA = depth(nodeA);
        int depthB = depth(nodeB);
        if (depthA > depthB) {
            nodeA = rewindUp(nodeA, depthA - depthB);
        }
        if (depthB > depthA) {
            nodeB = rewindUp(nodeB, depthB - depthA);
        }
        while (!nodeA.equals(nodeB)) {
            nodeA = nodeA.parent;
            nodeB = nodeB.parent;
        }

        return nodeA;
    }

    private static BinaryTree<Integer> rewindUp(BinaryTree<Integer> node, int steps) {
        while (node != null && steps > 0) {
            node = node.parent;
            steps--;
        }
        return node;
    }

    private static int depth(BinaryTree<Integer> node) {
        int d = 0;
        while (node != null) {
            node = node.parent;
            d++;
        }
        return d;
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
