import java.util.ArrayList;
import java.util.List;

public class Node {
    private String node;
    private List<Node> nodeList = new ArrayList<Node>();

    public Node (String node){
        this.node = node;
    }

    public String getNode() {
        return node;
    }

        public boolean isNeighbour(Node node){
        for(Node n: nodeList){
            if(n.getNode() == node.getNode())
                return true;
        }
        return false;
    }

    public boolean addNeighbourTogether(Node node){
        if(node!=null || !isNeighbour(node)) {
            nodeList.add(node);
            node.addNeighbourTogether(this);
            return true;
        }

        return false;
    }



    public List<Node> getNodeList() {
        return nodeList;
    }
}
