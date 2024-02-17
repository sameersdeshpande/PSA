package com.user;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T>{
        T data;
        Node<T> next;
        Node<T> prev;
        Node(T data){
            this.data = data;
            this.next = null;
            this.prev= null;

        }
    }

    public LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }
    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if(head == null){
            head = newNode;
            tail = newNode;

        } else {
            tail.next = newNode;
            tail= newNode;
        }
        size++;
    }
    public int find(T x){
        Node<T> current = head;
        int index = 0;

        while(current!= null){
            if(current.data.equals(x)){
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }
    //Modifying the method to the last occurrence of the item x
    public void remove(T x) {
        Node<T> current = head;
        Node<T> prev = null;

        while (current != null) {
            if (current.data.equals(x)) {
                if (prev == null) {
                    // If the element to be removed is at the head
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
            } else {
                // Move to the next node only if the current node is not removed
                prev = current;
            }
            current = current.next;
        }

        // Update tail if necessary
        if (prev != null && prev.next == null) {
            tail = prev;
        }
    }

    public boolean isEmpty(){
        return head == null;
    }
    public int size(){
        return size;
    }
    public void printList(){
        Node<T> current = head;

        while(current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create a LinkedList of integers
        LinkedList<Integer> list = new LinkedList<>();

        // Add elements to the list
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(20);
        list.add(60);

        // Print the elements of the list
        list.printList();

        list.remove(20);

        list.printList();
    }
}
