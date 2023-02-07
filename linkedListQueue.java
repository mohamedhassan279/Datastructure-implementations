import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IQueue {
  /*** Inserts an item at the queue front.*/
  public void enqueue(Object item);
  /*** Removes the object at the queue rear and returnsit.*/
  public Object dequeue();
  /*** Tests if this queue is empty.*/
  public boolean isEmpty();
  /*** Returns the number of elements in the queue*/
  public int size();
  /** print the queue*/
  public void printQueue();
}

public class LinkedListQueue implements IQueue {
    DoubleLinkedList Q = new DoubleLinkedList();
    
    public void enqueue(Object item){
        Q.add(item);
    }
    
    public Object dequeue(){
        Object temp = Q.get(0);
        Q.remove(0);
        return temp;
    }
    
    public boolean isEmpty(){
        return Q.isEmpty();
    }
    
    public int size(){
        return Q.size();
    }
    
    public void printQueue(){
        Q.printList();
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        LinkedListQueue Q = new LinkedListQueue();
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = st.split(", ");
        if (!(s.length == 1 && s[0].isEmpty())){
            for(int i = s.length - 1; i >= 0; i--){
                Object o = Integer.parseInt(s[i]);
                Q.enqueue(o);
            }
        }
        String keyword = sc.nextLine();
        if(Objects.equals(keyword, "enqueue")){
            int element = sc.nextInt();
            Q.enqueue(element);
            Q.printQueue();
        }
        if(Objects.equals(keyword, "dequeue")){
            if(Q.isEmpty()){
                System.out.println("Error");
            }
            else{
                Q.dequeue();
                Q.printQueue();
            }
        }
        if(Objects.equals(keyword, "isEmpty")){
            if(Q.isEmpty())
                System.out.println("True");
            else
                System.out.println("False");
        }
        if(Objects.equals(keyword, "size")){
            System.out.println(Q.size());
        }
    }
}

interface ILinkedList {
/**
* Inserts the specified element at the end of the list.
* @param element
*/
public void add(Object element);
/**
* @param index
* @return the element at the specified position in this list.
*/
public Object get(int index);

/**
* @return true if this list contains no elements.
*/
public boolean isEmpty();
/**
* Removes the element at the specified position in this list.
* @param index
*/
public void remove(int index);
/**
* @return the number of elements in this list.
*/
public int size();
/**
* print the list
*/
public void printList();
}


class DoubleLinkedList implements ILinkedList {
    public int length = 0;
    public static boolean flag;

    public class Node {
        Object item;
        Node next;
        Node prev;
    }

    public Node head = null;
    public Node tail = null;

    public void printList() {
        Node current = tail;
        if(head == null) {
            System.out.println("[]");
        }
        else {
            System.out.print("[" + current.item);
            current = current.prev;
            while (current != null) {
                System.out.print(", " + current.item);
                current = current.prev;
            }
            System.out.println("]");
        }
    }

    public void add(Object element) {
        Node newNode = new Node();
        newNode.item = element;
        if (length == 0) {
            newNode.next =newNode.prev= null;
            head = tail = newNode;
        }
        else {
            newNode.next = null;
            newNode.prev =tail;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public Object get(int index) {
        if(index < 0 || index >= length) {
            return 0;
        }
        else if(index == 0) {
            return head.item;
        }
        else if(index == length - 1) {
            return tail.item;
        }
        else {
            Node current = head;
            for(int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.item;
        }
    }

    public boolean isEmpty() {
        if (length == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void remove(int index) {
        flag = true;
        Node current = head;
        if(index < 0 || index >= length) {
            flag = false;
        }
        else if(length == 1) {
            head = tail = null;
            length--;
        }
        else if (index == 0) {
            head = head.next;
            head.prev=null;
            length--;
        }
        else if (index == length - 1) {
            for(int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = null;
            tail = current;
            length--;
        }
        else {
            for(int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            current.next.next.prev= current;
            length--;
        }
    }

    public int size() {
        return length;
    }
}