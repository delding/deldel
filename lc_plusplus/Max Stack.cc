// Design a max stack that supports push, pop, top, peekMax and popMax.
//
// push(x) -- Push element x onto stack.
// pop() -- Remove the element on top of the stack and return it.
// top() -- Get the element on the top.
// peekMax() -- Retrieve the maximum element in the stack.
// popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
// Example 1:
// MaxStack stack = new MaxStack();
// stack.push(5);
// stack.push(1);
// stack.push(5);
// stack.top(); -> 5
// stack.popMax(); -> 5
// stack.top(); -> 1
// stack.peekMax(); -> 5
// stack.pop(); -> 1
// stack.top(); -> 5


class MaxStack {
    stack<int> st;
    stack<int> maxSt;
public:
    /** initialize your data structure here. */
    MaxStack() {

    }

    void push(int x) {
        st.push(x);
        if (maxSt.empty() || maxSt.top() <= x) maxSt.push(x);
    }

    int pop() {
        auto x = st.top(); st.pop();
        if (x == maxSt.top()) maxSt.pop();
        return x;
    }

    int top() {
        return st.top();
    }

    int peekMax() {
        return maxSt.top();
    }

    int popMax() {
        auto m = maxSt.top(); maxSt.pop();
        stack<int> tmp;
        while (st.top() != m) {
            tmp.push(st.top()); st.pop();
        }
        st.pop();
        while (!tmp.empty()) {
            auto t = tmp.top();
            st.push(t);
            if (maxSt.empty() || maxSt.top() <= t) maxSt.push(t);
            tmp.pop();
        }
        return m;
    }
};

template<typename V> requires Sortable<V>()
class MaxStackT {
public:
  MaxStackT() {}

  void push(V val) {
    st.push(val);
    if (maxSt.empty() || val >= maxSt.top()) maxSt.push(val);
  }

  void pop() {
    auto v = st.top(); st.pop();
    if (v == maxSt.top()) maxSt.pop();
  }

  V top() {
    return st.top();
  }

  V getMax() {
    return maxSt.top();
  }

private:
  stack<V> maxSt;
  stack<V> st;
};


/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
