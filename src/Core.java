public class Core extends Node{
    public Core(String nodeName) {
        super(nodeName);
    }

    public void connectAggregation(Aggregation aggregation){
        addNeighbour(aggregation);
    }
}
