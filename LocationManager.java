import java.util.*;

public class LocationManager {
    private Graph graph;
    public LocationManager(Graph g) { this.graph = g; }

    public boolean createLocation(String loc) { return graph.addLocation(loc); }
    public boolean deleteLocation(String loc) { return graph.removeLocation(loc); }
    public boolean connectLocations(String a, String b) { return graph.addRoad(a, b); }
    public boolean disconnectLocations(String a, String b) { return graph.removeRoad(a, b); }
    public List<String> listConnections(String loc) { return graph.getConnections(loc); }
    public Set<String> listLocations() { return graph.getAllLocations(); }
}
