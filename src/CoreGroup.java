import java.util.ArrayList;
import java.util.List;

public class CoreGroup {
    private List<Core> cList = new ArrayList<Core>();

    public CoreGroup() {}
    public void addCore(Core core) {
        if(core != null)
            cList.add(core);
    }
    public void connectPod(Pod pod, int index) {
        for(Core c: cList) {
            c.connectAggregation(pod.getAggregation(index));
        }
    }
    public List<Core> getCoreList() {return cList;}
}
