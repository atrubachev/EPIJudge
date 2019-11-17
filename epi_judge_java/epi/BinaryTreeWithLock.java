package epi;

public class BinaryTreeWithLock {
    public static class BinaryTree {
        private BinaryTree left, right, parent;
        private boolean locked;
        private int numLockedDescendants;

        public boolean isLocked() {
            return locked;
        }

        public boolean lock() {
            if (locked || numLockedDescendants > 0) {
                return false;
            }
            for (BinaryTree iter = parent; iter != null; iter = iter.parent) {
                if (iter.locked) {
                    return false;
                }
            }
            locked = true;
            updateParentsNumLockedDescendants(1);
            return true;
        }

        public void unlock() {
            if (locked) {
                locked = false;
                updateParentsNumLockedDescendants(-1);
            }
        }

        private void updateParentsNumLockedDescendants(int delta) {
            for (BinaryTree iter = parent; iter != null; iter = iter.parent) {
                iter.numLockedDescendants += delta;
            }
        }
    }
}
