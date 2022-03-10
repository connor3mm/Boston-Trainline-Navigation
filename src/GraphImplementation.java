import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphImplementation implements GraphADT<Station,EdgeColourLine> {

    private List<Station> stations;
    private List<Neighbour> edges;
    private Map<Station, List<Neighbour>> adjacentStations;

    public GraphImplementation() {
        stations = new ArrayList<>();
        edges = new ArrayList<>();
        adjacentStations = new HashMap<>();
    }

    @Override
    public void addStation(Station station) {
        stations.add(station);
        adjacentStations.putIfAbsent(station, new ArrayList<>());

    }

    @Override
    public void addEdge(Station station) {
       //we want to create our map
        //create the edge between two stations
        List<Neighbour> neighbours = station.getNeighbouringStations();
        for(Neighbour neighbour: neighbours) {
            adjacentStations.get(station).add(neighbour);
        }

        for (Map.Entry<Station, List<Neighbour>> entry : adjacentStations.entrySet()) {
            System.out.println("-----------------------------------");
            System.out.println(entry.getKey().getStationName());

            for(Neighbour n: entry.getValue()) {
                System.out.println(n.getLineColour());
                System.out.println(n.getPreviousStationID());
                System.out.println(n.getNextStationId());

            }
            System.out.println("-----------------------------------");
        }

    }

    @Override
    public ArrayList<Station> getNeighbouringNodes() {
        //specify a station
        //for the specified station we want to get the ones that are connected to it
        return null;
    }

    public List<Station> getStations() {
        return this.stations;
    }

    public List<Neighbour> getEdges() {
        return this.edges;
    }

}
