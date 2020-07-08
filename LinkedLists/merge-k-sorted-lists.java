/*
https://leetcode.com/problems/merge-k-sorted-lists/

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/

// Using iterative merge sort.

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (n == 0) return null;
        // iterative merge sort, we start from bottom, so merge 1-sized first, then 2-sized, then 4-sized and so on 2 ^ n.
        for (int size = 1; size < n; size = size << 1) {
            // after merging 1-sized, we need to jump 2 steps. merging two 2-sized jump should be 4.
            // jumping should be obviously double than the size as we are merging 2 same size groups, so jump X 2.
            for (int j = 0; j + size < n; j = j + (size << 1)) {
                merge(lists, j, j + size);
            }
        }
        return lists[0];
    }
    
    void merge(ListNode[] lists, int i, int j) {
        ListNode head1 = lists[i];
        ListNode head2 = lists[j];
        ListNode dummy = new ListNode(0);
        ListNode merged = dummy;
        while (head1 != null && head2 != null) {
            int x = head1.val;
            int y = head2.val;
            if (x <= y) {
                merged.next = new ListNode(x);
                head1 = head1.next;
            } else {
                merged.next = new ListNode(y);
                head2 = head2.next;
            }
            merged = merged.next;
        }
        if (head1 != null) merged.next = head1;
        if (head2 != null) merged.next = head2;
        lists[i] = dummy.next;
    }    
}

// Using priority Queue...
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> PQ = new PriorityQueue<>((a, b) -> {
            if (a.val >= b.val) return 1;
            else return -1;
        });
        for (ListNode list : lists) {
            if (list != null) PQ.add(list);
        }
        ListNode dummy = new ListNode(0);
        ListNode merged = dummy;
        while (!PQ.isEmpty()) {
            merged.next = PQ.remove();
            merged = merged.next; // only the current smallest is picked from the top, the rest of the elements will get updated in next iteration.
            if (merged.next != null) PQ.add(merged.next);
        }
        return dummy.next;
    }
}
