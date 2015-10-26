/**
 * Implement the following operations of a stack using queues.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * Notes:
 * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 * Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 * You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 */

class MyStack {
  Queue<Integer> q1 = new LinkedList();
  Queue<Integer> q2 = new LinkedList();

  // Push element x onto stack.
  public void push(int x) {
    if (!q2.isEmpty()) q2.add(x);
    else q1.add(x);
  }

  // Removes the element on top of the stack.
  public void pop() {
    Queue<Integer> qfull = q1.isEmpty() ? q2 : q1;
    Queue<Integer> qempty = q1.isEmpty() ? q1 : q2;
    while (qfull.size() > 1) {
      qempty.add(qfull.poll());
    }
    qfull.poll();
  }

  // Get the top element.
  public int top() {
    Queue<Integer> qfull = q1.isEmpty() ? q2 : q1;
    Queue<Integer> qempty = q1.isEmpty() ? q1 : q2;
    while (qfull.size() > 1) {
      qempty.add(qfull.poll());
    }
    int top = qfull.poll();
    qempty.add(top);
    return top;
  }

  // Return whether the stack is empty.
  public boolean empty() {
    return q1.isEmpty() && q2.isEmpty();
  }
}
