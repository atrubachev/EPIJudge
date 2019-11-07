package epi;

import epi.test_framework.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LowestCommonAncestor {
    public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                              BinaryTreeNode<Integer> nodeA,
                                              BinaryTreeNode<Integer> nodeB) {
        List<BinaryTreeNode<Integer>> pathA = findPath(tree, nodeA);
        List<BinaryTreeNode<Integer>> pathB = findPath(tree, nodeB);

        int lcaIndex;
        for (lcaIndex = 0; lcaIndex < Math.min(pathA.size(), pathB.size()); lcaIndex++) {
            if (!pathA.get(lcaIndex).equals(pathB.get(lcaIndex))) {
                break;
            }
        }
        return lcaIndex == 0 ? tree : pathA.get(lcaIndex - 1);
    }

    private static List<BinaryTreeNode<Integer>> findPath(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> node) {
        List<BinaryTreeNode<Integer>> nodes = new ArrayList<>();
        if (tree == null || node == null) {
            return nodes;
        }

        if (tree.equals(node)) {
            nodes.add(tree);
            return nodes;
        }

        for (BinaryTreeNode<Integer> n : Arrays.asList(tree.left, tree.right)) {
            if (n != null) {
                List<BinaryTreeNode<Integer>> subtreeNodes = findPath(n, node);
                if (!subtreeNodes.isEmpty()) {
                    nodes.add(n);
                    nodes.addAll(subtreeNodes);
                    return nodes;
                }
            }
        }

        return nodes;
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TimedExecutor executor,
                                 BinaryTreeNode<Integer> tree, Integer key0,
                                 Integer key1) throws Exception {
        BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

        BinaryTreeNode<Integer> result =
                executor.run(() -> LCA(tree, node0, node1));

        if (result == null) {
            throw new TestFailure("Result can not be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
