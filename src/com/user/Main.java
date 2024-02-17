package com.user;

import com.user0.Node;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Node n1 = new Node("Data 1");
        Node n2 = new Node("Data 2");
        Node n3 = new Node("Data 3");
        Node n4 = new Node("Data 4");
        Node n5 = new Node("Data 5");

        Node head = n1;
        head.next =n2;
        head.next.next=n3;
        head.next.next.next=n4;
        head.next.next.next.next=n5;
        head.next.next.next.next.next=n3;
        
        Node outer = head;
        //findCycleUsingBruteForce(head);
       // findCycleUsingHash(head);
        findCycleUsingFloydAlg(head);

    }
public static void printList(Node start){
        while (start != null){
            System.out.print(start.data + "->");
            start = start.next;
        }
}
    private static boolean findCycleUsingBruteForce(Node head) {
        Node outer = head;
        int numNodesTraversedByOuter =0;
        while(outer !=null){
            numNodesTraversedByOuter++;
            outer = outer.next;
            Node inner = head;
            int k = numNodesTraversedByOuter;

            while (k>0){
                if(inner == outer){
                    System.out.print("Cycle Found 0" + inner.data);
                    return true;
                }
                inner = inner.next;
                k--;
            }
        }
        System.out.print("No Cycle Found");
            return false;
    }
    private static boolean findCycleUsingHash(Node head){
        HashSet<Node> set = new HashSet<>();
        Node current = head;

        while (current!=null){
            if(set.contains(current)){
                System.out.print("Cycle Found 0" + current.data);
                return true;
            }
            set.add(current);
            current = current.next;
        }
        System.out.print("No Cycle Found");
        return false;
    }
    private static boolean findCycleUsingFloydAlg(Node head){
        Node tortoise = head;
        Node hare = head;
        while(hare != null && hare.next!= null){
            tortoise = tortoise.next;
            hare = hare.next.next;

            if(hare == tortoise){
                System.out.print("Cycle Found 0" + hare.data);
                return true;
            }
        }
        System.out.print("No Cycle Found");
        return false;
    }
}