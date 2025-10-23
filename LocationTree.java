import java.util.*;

public class LocationTree {
    private Set<String> treeSet = new TreeSet<>();

    public void addLocation(String loc) { treeSet.add(loc); }
    public void removeLocation(String loc) { treeSet.remove(loc); }
    public List<String> getSortedLocations() { return new ArrayList<>(treeSet); }
}
