
import java.util.*;

public class Graph {
    LinkedList<Node> graph_nodes= new LinkedList<>();
    LinkedList<Edges> graph_edges= new LinkedList<>();

    //Graph CTOR

    public Graph(int vertexSize) {

        Random rand = new Random();
        boolean flag = true;
        int raffle; //saves random number

        for (int i = 1; i <= vertexSize; i++) {
            this.addVertex(new Node(i));
        }
        Iterator<Node> itNodeCurrent = (Iterator<Node>) this.graph_nodes.iterator();
        Iterator<Node> itNode=this.graph_nodes.iterator();
        Node current=itNodeCurrent.next();
//        ListIterator<Node> Current_Node=this.graph_nodes.listIterator(0);
        do{
            Iterator<Node> itNodeBegin = this.graph_nodes.iterator();
            Node begin=itNodeBegin.next();
            do {
                if (flag &&  (begin.val != current.val)) {
                    this.addEdge(current, begin);
                }

                raffle = rand.nextInt(4);
                if (raffle == 0) //randomly choose the odds to create a new edge
                    flag = false;
                else
                    flag = true;
                if(begin.val==vertexSize)
                    break;
                begin=itNodeBegin.next();
//                itNode=itNodeCurrent;
            }while (itNodeBegin.hasNext() || begin.val == vertexSize);
            if(current.val==vertexSize)
                break;
            current=itNodeCurrent.next();
        }while (itNodeCurrent.hasNext() || current.val== vertexSize);
    }

    public int returnValue(Iterator<Node> it) {
        return it.next().val;

    }

    //Add new Vertex
    public void addVertex(Node newVertVal) {
        graph_nodes.add(newVertVal);
    }

    public boolean addEdge(Node source, Node destenation) {
        if (!graph_nodes.contains(source))
            addVertex(source);
        if (!graph_nodes.contains(destenation))
            addVertex(destenation);
        Random rand = new Random();
        int weight = rand.nextInt(30);
        if (!hasEdge(source, destenation)) {
            if(source.val>destenation.val)
                graph_edges.add(new Edges(destenation, source, weight));
            else
                graph_edges.add(new Edges(source,destenation, weight));
            source.neigh.add(destenation.val);
            destenation.neigh.add(source.val);
            return true;
        } else
            return false;
    }

    public boolean hasEdge(Node src, Node des) {
        if(src.neigh==null)
            return false;
        return(src.neigh.contains(des.val));
    }

    public void printGraph() {
        for (Node n : this.graph_nodes) {
            System.out.print("The edges of: "+n.val + "-> ");
            n.print_VertList();
            System.out.println("");
        }
        System.out.println("************************************************\n");
    }

    public void printVertex() {
        for(Node n: this.graph_nodes) {
            System.out.print(n.val+",");
        }
    }

    public void printEdges() {
        System.out.println("************************************************");
        for(Edges e : this.graph_edges) {
            System.out.println("("+e.from.val+","+e.to.val+")");
            System.out.println("The weight is:"+e.weight);
        }
        System.out.println("************************************************\n");
    }
    //bulding
    public int Num_Of_Vertex(){
        return this.graph_nodes.size();
    }

    //Output:return weight edges matrix
    public int[][] buildMatrix () {
        int size=this.Num_Of_Vertex()+1;
        int[][] arr = new int[size][size];

        for(Edges e : this.graph_edges){
            int from=e.from.val;
            int to=e.to.val;
            arr[from][to]=e.weight;
        }
        return arr;
    }

    public int Weight_Func(int [][]arrEdges,int from,int to) {
        if(from<to)
            return arrEdges[from][to];
        else
            return arrEdges[to][from];
    }

    public Vector<Edges> MST_PRIM() {

        Random rand=new Random();
        int current_vertexVal;
        int r=rand.nextInt(this.Num_Of_Vertex())+1;
        Heap heap_vertex=new Heap(this.Num_Of_Vertex());
        heap_vertex.heap.get(r-1).weight=0; //initalize randomly one of the vertex's heap-weight to 0
        heap_vertex.heap.get(r-1).weight=0;
        Heap heap_father=new Heap();
        //    heap_vertex.Heapify(r-1);
        int index=-1; //saves number of vertex we input the heap_father
        int arr_edges[][]=this.buildMatrix();
        if(r!=1)
            heap_vertex.SwitchFirst(r-1);
        while(heap_vertex.heap!=null && heap_vertex.heap.size()!=0) {
            heap_father.heap.add(heap_vertex.Extract_Min());
            index++;
            current_vertexVal = heap_father.heap.get(index).vertex; //get the value the of the current vertex in the heap father
            Node current_vertex=this.graph_nodes.get(current_vertexVal-1); //gets the current vertex
            for(int i=0;i<current_vertex.neigh.size();i++) { //going through all neigh of the current vertex
                int son=current_vertex.neigh.get(i);
                if(heap_vertex.hasVertex(son) &&
                        this.Weight_Func(arr_edges,son,current_vertexVal) < heap_vertex.Vertex_Weight(son))
                //Line 9 PRIM algo
                {
                    heap_vertex.setFather(son,current_vertexVal);
                    heap_vertex.setWeight(son,this.Weight_Func(arr_edges,son,current_vertexVal));

                }
            }
        }
        Vector<Edges> mst=new Vector<>();
        for(int i=1;i<heap_father.heap.size();i++) {
            int son=heap_father.heap.get(i).vertex;
            int father=heap_father.heap.get(i).father;
            int weight=heap_father.heap.get(i).weight;
            mst.add(new Edges(this.graph_nodes.get(father-1),this.graph_nodes.get(son-1),weight));
        }
        return mst;
    }

    public Vector<Edges> NEW_MST_PRIM(Vector<Edges> prim, Edges edge){
        int from = edge.from.val;
        int to = edge.to.val;
        Edges biggest = new Edges();
        for(Edges e : prim){
            if(e.from.val == from || e.to.val == from ||
                    e.to.val == to || e.from.val == to){ // to find our vertex
                if(e.weight > edge.weight){
                    biggest = e;
                }
            }
        }
        if(biggest.weight!=0) {
            prim.remove(biggest);
            prim.add(edge);}

        return prim;
    }
}


