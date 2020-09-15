/*
https://leetcode.com/problems/min-stack/

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2

Constraints:
Methods pop, top and getMin operations will always be called on non-empty stacks.

*/

class MinStack {

    Stack<Integer> stack;
    int min;
    
    public MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        if (stack.isEmpty()) return;
        int top = stack.pop();
        if (top == min) min = stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
 
 /*
 https://leetcode.com/problems/max-stack/
 
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
 */
 
 class Node {
    int val;
    Node prev, next;
    Node(int val) {
        this.val = val;
    }
}

class DLL {
    
    Node head, tail;
    
    DLL() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }
    
    Node add(int x) {
        Node node = new Node(x);
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
        return node;
    }
    
    void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    Node top() {
        return tail.prev;
    }
    
}

class MaxStack {
    
    TreeMap<Integer, List<Node>> tMap;
    DLL dll;
    
    public MaxStack() {
        tMap = new TreeMap<>();
        dll = new DLL();
    }
    
    public void push(int x) {
        Node node = dll.add(x);
        tMap.computeIfAbsent(x, y -> new ArrayList<>()).add(node);
    }
    
    public int pop() {
        Node last = dll.top();
        dll.remove(last);
        removeFromMap(last.val);
        return last.val;
    }
    
    public int top() {
        return dll.top().val;
    }
    
    public int peekMax() {
        return tMap.lastKey();
    }
    
    public int popMax() {
        int ans = peekMax();
        Node node = removeFromMap(ans);
        dll.remove(node);
        return node.val;
    }
    
    Node removeFromMap(int val) {
        List<Node> list = tMap.get(val);
        Node node = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        if (list.isEmpty()) tMap.remove(val);
        return node;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
