public class Aggregation extends Node{
    public Aggregation(String nodeName) {
        super(nodeName);
    }

    public void connectEdge(Edge edge){
        addNeighbour(edge);
    }
}
