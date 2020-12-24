
public class Edges {
    Node to;
    Node from;
    int weight;
    Edges(Node f, Node t, int weight) {
        this.to=t;
        this.from=f;
        this.weight=weight;
    }

    Edges(){this.weight=0;}

    public void printEdge() {
        System.out.print("The weight of the edge ("+this.from.val+","+this.to.val+")" );
        System.out.println(" is:"+this.weight+"\n");
    }

}