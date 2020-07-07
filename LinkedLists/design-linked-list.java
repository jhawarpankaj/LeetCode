/*
https://leetcode.com/problems/design-linked-list/

Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list 
should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, 
you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:

get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
addAtTail(val) : Append a node of value val to the last element of the linked list.
addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be 
appended to the end of linked list. If index is greater than the length, the node will not be inserted.
deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.

Example:

Input: 
["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
[[],[1],[3],[1,2],[1],[1],[1]]
Output:  
[null,null,null,null,2,null,3]

Explanation:
MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3
 
Constraints:

0 <= index,val <= 1000
Please do not use the built-in LinkedList library.
At most 2000 calls will be made to get, addAtHead, addAtTail,  addAtIndex and deleteAtIndex.
*/
// A singly linked list implementation.

class Node {
    int val;
    Node next;
    Node(int x) {
        this.val = x;
    }
}

class MyLinkedList {
    
    Node head;
    int size;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = new Node(0); // a sentinel node.
        size = 0;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        Node p = head;
        for (int i = 0; i <= index; i++, p = p.next);
        return p.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;
        Node p = head;
        Node toAdd = new Node(val);
        for (int i = 0; i < index; i++, p = p.next);
        toAdd.next = p.next;
        p.next = toAdd;
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        Node p = head;
        for (int i = 0; i < index; i++, p = p.next);
        p.next = p.next.next;
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
 
 // A Doubly Linked List Implementation.
 
 class Node {
    int val;
    Node next, prev;
    Node(int val) {
        this.val = val;
    }
}

class MyLinkedList {
    
    Node head, tail;
    int size;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    
    public void display() {
        Node p = head.next;
        while (p != tail) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        int half = size >> 1;
        Node p;
        if (index + 1 <= half) {
            p = head;
            for (int i = 0; i <= index; i++, p = p.next);
        } else {
            p = tail;
            for (int i = size; i > index; i--, p = p.prev);
        }
        return p.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;
        int half = (size - 1) >> 1;
        Node p;
        if (index <= half) {
            p = head;
            for (int i = 0; i <= index - 1; i++, p = p.next);
        } else {
            p = tail;
            for (int i = size; i >= index; i--, p = p.prev);
        }
        Node toAdd = new Node(val);
        toAdd.next = p.next;
        p.next.prev = toAdd;
        p.next = toAdd;
        toAdd.prev = p;
        size++;
        // display();
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        int half = (size - 1) >> 1;
        Node p;
        if (index <= half) {
            p = head;
            for (int i = 0; i < index; i++, p = p.next);
        } else {
            p = tail;
            for (int i = size; i >= index; i--, p = p.prev);
        }
        p.next = p.next.next;
        p.next.prev = p;
        size--;
        // display();
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
