
import java.util.LinkedList;
import java.util.Scanner;

public class Node{
    public int val;
    LinkedList<Integer> neigh= new LinkedList<>();
    Node(int v) {
        this.val= v;
    }
    Node(){
        val=0;
    }

    public void print_VertList(){
        Scanner in=new Scanner(System.in);
        for(int v:neigh)
            System.out.print(v+", ");
    }
}