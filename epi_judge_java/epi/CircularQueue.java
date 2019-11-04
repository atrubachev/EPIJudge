package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class CircularQueue {

    public static class Queue {
        private int head = 0, tail = 0, size = 0;
        private Integer[] elements;

        public Queue(int capacity) {
            this.elements = new Integer[capacity];
        }

        public void enqueue(Integer value) {
            if (size == elements.length) {
                Collections.rotate(Arrays.asList(elements), -head);
                head = 0;
                tail = size;
                elements = Arrays.copyOf(elements, elements.length * 2);
            }
            elements[tail] = value;
            tail = (tail + 1) % elements.length;
            size++;
        }

        public Integer dequeue() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            int value = elements[head];
            head = (head + 1) % elements.length;
            size--;
            return value;
        }

        public int size() {
            return size;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @EpiUserType(ctorParams = {String.class, int.class})
    public static class QueueOp {
        public String op;
        public int arg;

        public QueueOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }

        @Override
        public String toString() {
            return op;
        }
    }

    @EpiTest(testDataFile = "circular_queue.tsv")
    public static void queueTest(List<QueueOp> ops) throws TestFailure {
        Queue q = new Queue(1);
        int opIdx = 0;
        for (QueueOp op : ops) {
            switch (op.op) {
                case "Queue":
                    q = new Queue(op.arg);
                    break;
                case "enqueue":
                    q.enqueue(op.arg);
                    break;
                case "dequeue":
                    int result = q.dequeue();
                    if (result != op.arg) {
                        throw new TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, q)
                                .withProperty(TestFailure.PropertyName.COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, result);
                    }
                    break;
                case "size":
                    int s = q.size();
                    if (s != op.arg) {
                        throw new TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, q)
                                .withProperty(TestFailure.PropertyName.COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, s);
                    }
                    break;
            }
            opIdx++;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
