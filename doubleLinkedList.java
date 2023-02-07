import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
public void printList();
/**
* Inserts a specified element at the specified position in the list.
* @param index
* @param element
*/
public void add(int index, Object element);
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
* Replaces the element at the specified position in this list with the
* specified element.
* @param index
* @param element
*/
public void set(int index, Object element);
/**
* Removes all of the elements from this list.
*/
public void clear();
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
* @param fromIndex
* @param toIndex
* @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
*/
public ILinkedList sublist(int fromIndex, int toIndex);
/**
* @param o
* @return true if this list contains an element with the same value as the specified element.
*/
public boolean contains(Object o);
}


public class DoubleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/
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
        Node current = head;
        if(current == null) {
            System.out.println("[]");
        }
        else {
            System.out.print("[" + current.item);
            current = current.next;
            while (current != null) {
                System.out.print(", " + current.item);
                current = current.next;
            }
            System.out.println("]");
        }
    }

    public void insertFirst(Object element) {
        Node newNode = new Node();
        newNode.item = element;
        if (length == 0) {
            newNode.next =newNode.prev= null;
            head = tail = newNode;
        }
        else {
            newNode.next = head;
            newNode.prev =null;
            head = newNode;
        }
        length++;
    }

    public void add(int index, Object element) {
        flag = true;
        if(index < 0 || index > length) {
            flag = false;
        }
        else {
            Node newNode = new Node();
            newNode.item = element;
            if(index == 0) {
                insertFirst(element);
            }
            else if (index == length) {
                add(element);
            }
            else {
                Node current = head;
                for(int i = 1; i < index; i++) {
                    current = current.next;
                }
                //warnnnnnnnnnn
                newNode.next = current.next;
                newNode.prev= current;
                current.next.prev =newNode;
                current.next = newNode;
                length++;
            }
        }
    }
    //insert last
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

    public void set(int index, Object element) {
        Node current = head;
        flag = true;
        if(index < 0 || index >= length) {
            flag = false;
        }
        else if(index == 0) {
            head.item = element;
        }
        else if(index == length - 1) {
            tail.item = element;
        }
        else {
            for(int i = 0; i < index; i++) {
                current = current.next;
            }
            current.item = element;
        }
    }

    public void clear() {
        head = tail = null;
        length = 0;
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

    public ILinkedList sublist(int fromIndex, int toIndex) {

        ILinkedList sub = new DoubleLinkedList();
        if(fromIndex < 0 || fromIndex >= length || toIndex < 0 || toIndex >= length || fromIndex > toIndex || isEmpty()){
            return null;
        }
        else {
            for (int i = fromIndex; i <= toIndex; i++) {
                sub.add(get(i));
            }
            return sub;
        }
    }

    public boolean contains(Object o) {
        Node current = head;
        while(current != null) {
            if(current.item == o) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        DoubleLinkedList list = new DoubleLinkedList();
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = st.split(", ");
        if (!(s.length == 1 && s[0].isEmpty())){
            for(int i=0; i < s.length; i++){
                Object o = Integer.parseInt(s[i]);
                list.add(o);
            }
        }
        String keyword = sc.nextLine();
        if(Objects.equals(keyword, "add")){
            int element = sc.nextInt();
            list.add(element);
            list.printList();
        }
        if(Objects.equals(keyword, "addToIndex")){
            int index = sc.nextInt();
            int element =sc.nextInt();
            list.add(index, element);
            if(flag == true) {
                list.printList();
            }
            else {
                System.out.println("Error");
            }
        }
        if(Objects.equals(keyword, "get")){
            int index = sc.nextInt();
            Object res = list.get(index);
            if((int)res == 0) {
                System.out.println("Error");
            }
            else {
                System.out.println(res);
            }
        }
        if(Objects.equals(keyword, "set")) {
            int index = sc.nextInt();
            int element =sc.nextInt();
            list.set(index, element);
            if(flag == false){
                System.out.println("Error");
            }
            else {
                list.printList();
            }
        }
        if(Objects.equals(keyword, "clear")) {
            list.clear();
            list.printList();
        }
        if(Objects.equals(keyword, "isEmpty")) {
            boolean res = list.isEmpty();
            if(res == true) {
                System.out.println("True");
            }
            else {
                System.out.println("False");
            }
        }
        if(Objects.equals(keyword, "remove")) {
            int index = sc.nextInt();
            list.remove(index);
            if(flag == false) {
                System.out.println("Error");
            }
            else {
                list.printList();
            }
        }
        if(Objects.equals(keyword, "size")){
            System.out.println(list.size());
        }
        if(Objects.equals(keyword, "sublist")) {
            int fromIndex = sc.nextInt();
            int toIndex = sc.nextInt();
            ILinkedList sub = list.sublist(fromIndex, toIndex);
            if(sub == null){
                System.out.println("Error");
            }
            else{
                sub.printList();
            }
        }
        if(Objects.equals(keyword, "contains")) {
            int element =sc.nextInt();
            boolean res = list.contains(element);
            if(res == true) {
                System.out.println("True");
            }
            else {
                System.out.println("False");
            }
        }
    }
}