/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
* */

class MinStack {
    Stack<Integer> st = new Stack<Integer>();
    Stack<Integer> min = new Stack<Integer>();
    
    public void push(int x) {
        st.push(x);
        if (!min.isEmpty() && min.peek() < x) return;
        min.push(x);
    }

    public void pop() {
        int x = st.pop();
        if (x == min.peek()) min.pop();
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
