public class Edge extends Node{
    public Edge(String nodeName) {
        super(nodeName);
    }

    public void connectServer(Server server){
        addNeighbour(server);
    }
}
