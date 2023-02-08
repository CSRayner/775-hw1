import java.util.ArrayList;
import java.util.List;

public class CoreGroup {
    private List<Core> coreList = new ArrayList<Core>();

    public CoreGroup() {}
    public void addCore(Core core) {
        if(core != null)
            coreList.add(core);
    }
    public void connectPod(Pod pod, int index) {
        for(Core c: coreList) {
            c.connectAggregation(pod.getAggregation(index));
        }
    }
    public List<Core> getCoreList() {return coreList;}
}
