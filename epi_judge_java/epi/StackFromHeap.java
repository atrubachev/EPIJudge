package epi;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class StackFromHeap {

    static int priority = 0;
    static PriorityQueue<Element> maxHeap = new PriorityQueue<>((a, b) -> b.priority - a.priority);

    static class Element {
        private int value, priority;

        public Element(int value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    void push(int value) {
        maxHeap.add(new Element(value, priority));
        priority++;
    }

    int pop() {
        if (maxHeap.isEmpty()) {
            throw new NoSuchElementException();
        }
        priority--;
        return maxHeap.remove().value;
    }

    int peek() {
        if (maxHeap.isEmpty()) {
            throw new NoSuchElementException();
        }
        return maxHeap.peek().value;
    }

    public static void main(String[] args) {
        StackFromHeap stack = new StackFromHeap();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.peek());
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
