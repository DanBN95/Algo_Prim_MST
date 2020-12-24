
import java.util.Vector;

public class Heap {

    Vector<heapOBJ> heap=new Vector<>();

    public class heapOBJ{

        public int vertex;
        public int weight;
        public int father;

        public heapOBJ(int vertex){
            this.vertex=vertex;
            this.weight=100; //initialize as "infinity"
            this.father=0;
        }

        public heapOBJ(heapOBJ obj) {
            this.vertex=obj.vertex;
            this.weight=obj.weight;
            this.father=obj.father;
        }
    }

    //Defaultive CTOR
    public Heap() {}

    public Heap(int num_of_vertex){
        for(int j=1;j<=num_of_vertex;j++)
            this.heap.add(new heapOBJ(j));
    }

    public void Heapify(int index) {

        int left_index = 2 * index + 1;
        int right_index = 2 * index + 2;
        int smallest;
        if (left_index < this.heap.size() &&
                this.heap.get(left_index).weight < this.heap.get(index).weight) {
            smallest = left_index;
        } else
            smallest = index;

        if (right_index < this.heap.size() &&
                this.heap.get(right_index).weight < this.heap.get(smallest).weight) {
            smallest = right_index;
        }

        if (smallest != index) {
            heapOBJ temp = this.heap.get(index);
            this.heap.set(index, this.heap.get(smallest));
            this.heap.set(smallest, temp);
            Heapify(smallest);
        }
    }

    public void SwitchFirst (int index){
        heapOBJ min_obj=new heapOBJ(this.heap.get(index));
        this.heap.set(index,this.heap.get(0));
        this.heap.set(0,min_obj);
    }

    public heapOBJ Extract_Min() {
        Heapify(0);
        heapOBJ min_obj=new heapOBJ(this.heap.get(0));
        this.heap.set(0,this.heap.get(this.heap.size()-1));
        this.heap.remove(this.heap.size()-1);
        Heapify(0);
        return min_obj;
    }

    public boolean hasVertex(int vertValue) {
        for(heapOBJ obj : this.heap){
            if(obj.vertex==vertValue)
                return true;
        }
        return false;
    }

    //output: return the weight of the vertex
    public int Vertex_Weight(int vertValue) {
        for (heapOBJ obj : this.heap) {
            if (obj.vertex == vertValue)
                return obj.weight;
        }
        return -1;
    }

    public void setFather(int son,int father) {
        for (heapOBJ obj : this.heap) {
            if (obj.vertex == son)
                obj.father = father;
        }
    }

    public void setWeight(int vert,int weight) {
        //set the weight of the vertex
        for (heapOBJ obj : this.heap) {
            if (obj.vertex == vert)
                obj.weight = weight;
        }
    }
}

