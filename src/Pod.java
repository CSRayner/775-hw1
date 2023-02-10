import java.util.ArrayList;
import java.util.List;

public class Pod {
    private List<Agg> aggList = new ArrayList<Agg>();
    private List<Edge> eList = new ArrayList<Edge>();

    public boolean addAggregation(Agg aggregation){
        if(aggregation !=null) {
         aggList.add(aggregation);
            return true;
        }
        return false;
    }

    public boolean addEdge(Edge edge) {
        if(edge != null){
            eList.add(edge);
            return true;
        }
        return false;
    }

    public Agg getAggregation(int index) {
        if (aggList.size() <= index)
            return null;
        return aggList.get(index);
    }
    public Edge getEdge(int index) {
        if(eList.size() <= index)
            return null;
        return eList.get(index);
    }
    public void connectSwitches() {
        for(Agg a: aggList)
            for(Edge e: eList)
                a.addNeighbourTogether(e);
    }

    public List<Agg> getAggList() {return aggList;}
    public List<Edge> getEdgeList() {return eList;}
}
