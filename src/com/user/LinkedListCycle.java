package com.user;
import java.util.HashSet;
import java.util.Set;
class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
        next = null;
    }
}
public class LinkedListCycle{

    public boolean hasCycleWithExtraSpace(ListNode head){
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;

        while( current != null){
            if(visited.contains(current)){
                return true;
            }
            visited.add(current);
            current = current.next;

        }
        return false;
    }

    public boolean hasCycleWithoutExtraSpace(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null){
            if(slow == fast){
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // Example usage:
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next; // Create a cycle

        System.out.println("Part (a) Result: " + solution.hasCycleWithExtraSpace(head));
        System.out.println("Part (b) Result: " + solution.hasCycleWithoutExtraSpace(head));
    }
}
