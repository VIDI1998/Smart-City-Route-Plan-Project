import java.util.*;

public class Graph {
    private Map<String, List<String>> adjList = new HashMap<>();

    public boolean addLocation(String loc) {
        if (loc == null || loc.isEmpty() || adjList.containsKey(loc)) return false;
        adjList.put(loc, new ArrayList<>());
        return true;
    }

    public boolean removeLocation(String loc) {
        if (!adjList.containsKey(loc)) return false;
        adjList.remove(loc);
        adjList.values().forEach(list -> list.remove(loc));
        return true;
    }

    public boolean addRoad(String a, String b) {
        if (!adjList.containsKey(a) || !adjList.containsKey(b) || a.equals(b)) return false;
        if (!adjList.get(a).contains(b)) adjList.get(a).add(b);
        if (!adjList.get(b).contains(a)) adjList.get(b).add(a);
        return true;
    }

    public boolean removeRoad(String a, String b) {
        boolean removed = false;
        if (adjList.containsKey(a)) removed |= adjList.get(a).remove(b);
        if (adjList.containsKey(b)) removed |= adjList.get(b).remove(a);
        return removed;
    }

    public List<String> getConnections(String loc) {
        return adjList.getOrDefault(loc, new ArrayList<>());
    }

    public Set<String> getAllLocations() {
        return new TreeSet<>(adjList.keySet());
    }

    public void displayBFS() {
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        for (String loc : adjList.keySet()) {
            if (!visited.contains(loc)) {
                q.add(loc);
                visited.add(loc);
                while (!q.isEmpty()) {
                    String cur = q.poll();
                    System.out.println("Location: " + cur);
                    List<String> connections = adjList.get(cur);
                    if (connections.isEmpty()) {
                        System.out.println("  Connected to: None");
                    } else {
                        System.out.println("  Connected to:");
                        for (String n : connections) {
                            System.out.println("    - " + n);
                            if (!visited.contains(n)) {
                                q.add(n);
                                visited.add(n);
                            }
                        }
                    }
                }
            }
        }
    }
}

