import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// This is a test of git push
public class KaryFatTree {
    private int k=0;
    private int coreGroupCount = 0;
    private int coreCount = 0;
    private int podCount = 0;
    private int aggregationCount = 0;
    private int edgeCount = 0;
    private int serverCount = 0;
    private List<Node> nodeList = new ArrayList<>();
    private List<Server> serverList = new ArrayList<>();
    private List<Edge> edgeList = new ArrayList<>();
    private List<Aggregation> aggregationList = new ArrayList<>();
    private List<Core> coreList = new ArrayList<>();
    private List<CoreGroup> coreGroupList = new ArrayList<>();
    private List<Pod> podList = new ArrayList<>();

    public KaryFatTree (int k) {
        this.k = k;
        initialData();
        initialNode(serverList, nodeList, "Server", serverCount);
        initialNode(edgeList, nodeList, "Edge", edgeCount);
        initialNode(aggregationList, nodeList, "Aggregation", aggregationCount);
        initialNode(coreList, nodeList, "Core", coreCount);
        coreGroupList = new ArrayList<>();
        podList = new ArrayList<>();

        connectEdgeToServer();
        createPods();
        createCoreGroups();
        connectCoreGroupToPod();
    }

    public void initialData() {
        coreGroupCount = (k/2);
        coreCount = (k/2)*(k/2);
        podCount = k;
        aggregationCount = k/2*k;
        edgeCount = k/2*k;
        serverCount = (k/2)*(k/2)*k;
    }

    public void initialNode(List list, List nodeList, String type, int size){
        for(int i=0; i!=size; ++i){
            Node node;
            String nodeName = "N"+nodeList.size();
            switch(type){
                case "Server":
                    node = new Server(nodeName);
                    break;
                case "Edge":
                    node = new Edge(nodeName);
                    break;
                case "Aggregation":
                    node = new Aggregation(nodeName);
                    break;
                case "Core":
                    node = new Core(nodeName);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
            list.add(node);
            nodeList.add(node);
        }
    }

    private void createCoreGroups() {
        for(int i=0; i!=coreGroupCount; ++i){
            CoreGroup coreGroup = new CoreGroup();
            for(int j=0; j!=k/2; ++j){
                coreGroup.addCore(coreList.get(0));
                coreList.remove(0);
            }
            coreGroupList.add(coreGroup);
        }
    }

    private void createPods(){
        for(int i=0; i!=podCount; ++i){
            Pod pod = new Pod();
            for(int j=0; j!=k/2; ++j){
                pod.addAggregation(aggregationList.get(0));
                pod.addEdge(edgeList.get(0));
                aggregationList.remove(0);
                edgeList.remove(0);
            }
            pod.connectSwitches();
            podList.add(pod);
        }
    }

    private void connectEdgeToServer(){
        for(Edge e: edgeList)
            for(int i=0; i!=k/2; ++i){
                e.addNeighbour(serverList.get(0));
                serverList.remove(0);
            }
    }

    private void connectCoreGroupToPod(){
        for(int i=0; i!=coreGroupList.size(); ++i)
            for(int j=0; j!=podCount; ++j){
                int index = (i+j)%(k/2);
                coreGroupList.get(i).connectPod(podList.get(j),index);
            }
    }

    public void display() {
        try {
            File file = new File("./output.txt");
            if(!file.exists())
                file.createNewFile();
            FileWriter writer = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(writer);

            for(Node n: nodeList)
                for(Node nei: nodeList){
                    if(!nei.getNodeName().equals(n.getNodeName())){
                        out.write(n.getNodeName()+" "+nei.getNodeName()+" ");
                        out.write(n.isNeighbour(nei) ? "1" : "999");
                        out.newLine();
                    }
                }
            out.flush();
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        if(args.length < 1){
            System.out.println("k value required");
            return;
        }

        KaryFatTree karyFatTree = new KaryFatTree(Integer.parseInt(args[0]));
        karyFatTree.display();
    }

}