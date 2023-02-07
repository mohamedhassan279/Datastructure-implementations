import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IStack {
  
  /*** Removes the element at the top of stack and returnsthat element.
  * @return top of stack element, or through exception if empty
  */
  
  public Object pop();
  
  /*** Get the element at the top of stack without removing it from stack.
  * @return top of stack element, or through exception if empty
  */
  
  public Object peek();
  
  /*** Pushes an item onto the top of this stack.
  * @param object to insert*
  */
  
  public void push(Object element);
  
  /*** Tests if this stack is empty
  * @return true if stack empty
  */
  public boolean isEmpty();
  
   /**
    * @return the size of the stack
    */
  public int size();
   
    /**
    * print the stack in the form [1, 2, 3, .....]
    */
   public void printStack();
}

public class MyStack implements IStack {
    
    SingleLinkedList stack = new SingleLinkedList();
    
    public Object pop(){
        if(stack.isEmpty())
            return null;
        Object temp = stack.get(0);
        stack.remove(0);
        return temp;
    }
  
    public Object peek(){
        if(stack.isEmpty())
            return null;
        return stack.get(0);
    }
  
    public void push(Object element){
        stack.insertFirst(element);
    }
  
    public boolean isEmpty(){
        return stack.isEmpty();
    }
  
    public int size(){
        return stack.size();
    }
    
    public void printStack(){
        stack.printList();
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        MyStack stack = new MyStack();
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = st.split(", ");
        if (!(s.length == 1 && s[0].isEmpty())){
            for(int i = s.length - 1; i >= 0; i--){
                Object o = Integer.parseInt(s[i]);
                stack.push(o);
            }
        }
        String keyword = sc.nextLine();
        if(Objects.equals(keyword, "push")){
            int element = sc.nextInt();
            stack.push(element);
            stack.printStack();
        }
        if(Objects.equals(keyword, "pop")){
            if(stack.isEmpty()){
                System.out.println("Error");
            }
            else{
                stack.pop();
                stack.printStack();
            }
        }
        if(Objects.equals(keyword, "peek")){
            if(stack.isEmpty()){
                System.out.println("Error");
            }
            else{
                System.out.println(stack.peek());
            }
        }
        if(Objects.equals(keyword, "isEmpty")){
            if(stack.isEmpty())
                System.out.println("True");
            else
                System.out.println("False");
        }
        if(Objects.equals(keyword, "size")){
            System.out.println(stack.size());
        }
    }
}

interface ILinkedList {

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
    * print the list in the form [1, 2, 3, .....]
    */
    public void printList();
}

/**
* class which contains single list implementation
* @author Mohamed H.Sadek
*/
class SingleLinkedList implements ILinkedList {
    public int length = 0;
    public static boolean flag;

    public class Node {
        Object item;
        Node next;
    }

    public Node head = null;
    public Node tail = null;

    public void insertFirst(Object element) {
        Node newNode = new Node();
        newNode.item = element;
        if (length == 0) {
            newNode.next = null;
            head = tail = newNode;
        } 
        else {
            newNode.next = head;
            head = newNode;
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
            length--;
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

    public int size() {
        return length;
    }
    
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
}