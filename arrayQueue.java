import java.util.Objects;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IQueue {
    /*** Inserts an item at the queue front. */
    public void enqueue(Object item);

    /*** Removes the object at the queue rear and returnsit. */
    public Object dequeue();

    /*** Tests if this queue is empty. */
    public boolean isEmpty();

    /*** Returns the number of elements in the queue */
    public int size();
}

public class ArrayQueue implements IQueue {

    public static int N = 100;
    public Object[] arr = new Object[N];
    public int r = N - 1, f = N - 1;

    public void enqueue(Object item) {
        arr[r] = item;
        if (r == 0) {
            r = N - 1;
        } else {
            r--;
        }
    }

    public Object dequeue() {
        Object front = arr[f];
        if (f == 0) {
            f = N - 1;
        } else {
            f--;
        }
        return front;
    }

    public boolean isEmpty() {
        return r == f;
    }

    public int size() {
        return (N - r + f) % N;
    }

    public void printQ() {
        if (r == f) {
            System.out.println("[]");
        } else {
            System.out.print("[" + arr[r + 1]);

            for (int i = r + 2; (N - r + f) % N != 1 && i != (f + 1) % N; i = (i + 1) % N) {
                System.out.print(", " + arr[i]);
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = st.split(", ");
        if (!(s.length == 1 && s[0].isEmpty())) {
            for (int i = s.length - 1; i >= 0; i--) {
                Object o = Integer.parseInt(s[i]);
                queue.enqueue(o);
            }
        }
        String keyword = sc.nextLine();
        if (Objects.equals(keyword, "enqueue")) {
            int element = sc.nextInt();
            if (queue.size() < N - 1) {
                queue.enqueue(element);
                queue.printQ();
            } else {
                System.out.println("Error");
            }
        } else if (Objects.equals(keyword, "dequeue")) {
            if (!queue.isEmpty()) {
                queue.dequeue();
                queue.printQ();
            } else {
                System.out.println("Error");
            }
        } else if (Objects.equals(keyword, "isEmpty")) {
            if (queue.isEmpty()) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        } else if (Objects.equals(keyword, "size")) {
            System.out.println(queue.size());
        } else {
            System.out.println("Error");
        }
    }
}