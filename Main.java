
//chen sabban - 205983836
//dan ben natan - 313196966
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String [] args) {
        Scanner in=new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter num of vertex:");
        int num_of_vertex=in.nextInt();
        Graph graph=new Graph(num_of_vertex);
        graph.printGraph();
        graph.printEdges();
        Vector<Edges> prim=new Vector<>();
        prim=graph.MST_PRIM();
        System.out.println("************************************************");
        System.out.println("Minimum spanning tree : \n");
        for(Edges e : prim) {
            e.printEdge();
        }


        int newV1;
        int newV2;
        do {
            newV1 = rand.nextInt(graph.Num_Of_Vertex());
            newV2 = rand.nextInt(graph.Num_Of_Vertex());
            while (newV1 == newV2) {
                newV1 = rand.nextInt(graph.Num_Of_Vertex());
            }
        } while (graph.hasEdge(graph.graph_nodes.get(newV1), graph.graph_nodes.get(newV2)));

        //remain the same MST
        Edges edge1 = new Edges(graph.graph_nodes.get(newV1), graph.graph_nodes.get(newV2), 40);
        graph.graph_edges.add(edge1);
        graph.graph_nodes.get(newV1).neigh.add(graph.graph_nodes.get(newV2).val);
        graph.graph_nodes.get(newV2).neigh.add(graph.graph_nodes.get(newV1).val);

        prim=graph.NEW_MST_PRIM(prim,edge1);
        System.out.println("************************************************");
        System.out.println("Our new edge : \n");
        edge1.printEdge();
        System.out.println("Minimum spanning tree after adding first edge : \n");
        for(Edges e : prim) {
            e.printEdge();
        }

        do {
            newV1 = rand.nextInt(graph.Num_Of_Vertex());
            newV2 = rand.nextInt(graph.Num_Of_Vertex());
            while (newV1 == newV2) {
                newV1 = rand.nextInt(graph.Num_Of_Vertex());
            }
        } while (graph.hasEdge(graph.graph_nodes.get(newV1), graph.graph_nodes.get(newV2)));
        // new MST

        Edges edge2 = new Edges(graph.graph_nodes.get(newV1), graph.graph_nodes.get(newV2), 1);
        graph.graph_edges.add(edge2);
        graph.graph_nodes.get(newV1).neigh.add(graph.graph_nodes.get(newV2).val);
        graph.graph_nodes.get(newV2).neigh.add(graph.graph_nodes.get(newV1).val);

        prim=graph.NEW_MST_PRIM(prim,edge2);
        System.out.println("************************************************");
        System.out.println("Our new edge : \n");
        edge2.printEdge();
        System.out.println("Minimum spanning tree after adding second edge : \n");
        for(Edges e : prim) {
            e.printEdge();
        }

    }

}



