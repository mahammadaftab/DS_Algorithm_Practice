import java.util.Stack;

class MyQueue {

    private Stack<Integer> enqueueStack;
    private Stack<Integer> dequeueStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        enqueueStack = new Stack<>();
        dequeueStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        enqueueStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (dequeueStack.isEmpty()) {
            // Transfer elements from enqueueStack to dequeueStack
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
        if (dequeueStack.isEmpty()) {
            return -1; // Or throw an exception if appropriate for your use case
        }
        return dequeueStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (dequeueStack.isEmpty()) {
            // Transfer elements from enqueueStack to dequeueStack
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
        if (dequeueStack.isEmpty()) {
            return -1; // Or throw an exception if appropriate for your use case
        }
        return dequeueStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());  // returns 1
        System.out.println(queue.pop());   // returns 1
        System.out.println(queue.empty()); // returns false
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 
 */