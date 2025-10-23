import java.util.*;

public class SmartCityApp {
    private Graph graph = new Graph();
    private LocationManager manager = new LocationManager(graph);
    private LocationTree tree = new LocationTree();
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) { new SmartCityApp().run(); }

    private void run() {
        loadSample();
        while (true) {
            System.out.println("\n=== SMART CITY ROUTE PLANNER ===");
            System.out.println("1. Add Location");
            System.out.println("2. Remove Location");
            System.out.println("3. Add Road");
            System.out.println("4. Remove Road");
            System.out.println("5. Display All Connections (BFS)");
            System.out.println("6. Display All Locations");
            System.out.println("7. Display Location Connections");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            System.out.flush();

            String ch = sc.nextLine().trim();
            switch(ch) {
                case "1": addLocation(); break;
                case "2": removeLocation(); break;
                case "3": addRoad(); break;
                case "4": removeRoad(); break;
                case "5": graph.displayBFS(); break;
                case "6": displayLocations(); break;
                case "7": locationConnections(); break;
                case "8": System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice, try again."); break;
            }
        }
    }

    private void loadSample() {
        String[] locs = {"Central Station", "City Mall", "Tech Park", "Green Park"};
        for (String l : locs) { graph.addLocation(l); tree.addLocation(l); }
        manager.connectLocations("Central Station","City Mall");
        manager.connectLocations("City Mall","Tech Park");
        manager.connectLocations("Tech Park","Green Park");
        manager.connectLocations("Green Park","Central Station");
    }

    private void addLocation() {
        System.out.print("Enter new location: ");
        String loc = sc.nextLine().trim();
        if(manager.createLocation(loc)) { tree.addLocation(loc); System.out.println("Added: " + loc); }
        else System.out.println("Location exists or invalid.");
    }

    private void removeLocation() {
        System.out.print("Enter location to remove: ");
        String loc = sc.nextLine().trim();
        if(manager.deleteLocation(loc)) { tree.removeLocation(loc); System.out.println("Removed: " + loc); }
        else System.out.println("Location not found.");
    }

    private void addRoad() {
        System.out.print("Enter first location: "); String a = sc.nextLine().trim();
        System.out.print("Enter second location: "); String b = sc.nextLine().trim();
        System.out.println(manager.connectLocations(a,b) ? "Road added." : "Failed to add road.");
    }

    private void removeRoad() {
        System.out.print("Enter first location: "); String a = sc.nextLine().trim();
        System.out.print("Enter second location: "); String b = sc.nextLine().trim();
        System.out.println(manager.disconnectLocations(a,b) ? "Road removed." : "No such road.");
    }

    private void displayLocations() {
        System.out.println("All Locations:");
        tree.getSortedLocations().forEach(loc -> System.out.println(" - " + loc));
    }

    private void locationConnections() {
        System.out.print("Enter location: "); String loc = sc.nextLine().trim();
        List<String> conns = manager.listConnections(loc);
        System.out.println("Connections from " + loc + ":");
        if(conns.isEmpty()) System.out.println("  None");
        else conns.forEach(c -> System.out.println("  - " + c));
    }
}
