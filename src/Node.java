import java.util.ArrayList;
import java.util.List;

public class Node {
    protected String nodeName;
    protected List<Node> neighbourList = new ArrayList<Node>();

    public Node (String nodeName){
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public boolean addNeighbour(Node node){
        if(node!=null && !isNeighbour(node)) {
            neighbourList.add(node);
            node.addNeighbour(this);
            return true;
        }
        return false;
    }

    public boolean isNeighbour(Node node){
        for(Node n: neighbourList){
            if(n.getNodeName() == node.getNodeName())
                return true;
        }
        return false;
    }

    public List<Node> getNeighbourList() {
        return neighbourList;
    }
}
