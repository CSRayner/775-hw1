import java.util.ArrayList;
import java.util.List;

public class Pod {
    private List<Aggregation> aggregationList = new ArrayList<Aggregation>();
    private List<Edge> edgeList = new ArrayList<Edge>();

    public Pod(){}
    public boolean addAggregation(Aggregation aggregation){
        if(aggregation !=null) {
            aggregationList.add(aggregation);
            return true;
        }
        return false;
    }

    public boolean addEdge(Edge edge) {
        if(edge != null){
            edgeList.add(edge);
            return true;
        }
        return false;
    }

    public Aggregation getAggregation(int index) {
        if(aggregationList.size() <= index)
            return null;
        return aggregationList.get(index);
    }
    public Edge getEdge(int index) {
        if(edgeList.size() <= index)
            return null;
        return edgeList.get(index);
    }
    public void connectSwitches() {
        for(Aggregation a: aggregationList)
            for(Edge e: edgeList)
                a.addNeighbour(e);
    }

    public List<Aggregation> getAggregationList() {return aggregationList;}
    public List<Edge> getEdgeList() {return edgeList;}
}
